package frontend.lex;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatefulLexerTest {

    Field getPrivateField(String fieldName) {
        Field[] fields = StatefulLexer.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    Method getPrivateMethod(String methodName) {
        Method[] methods = StatefulLexer.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    @Test
    void testAdvance() throws IllegalAccessException, InvocationTargetException {
        Field preProcessorField = getPrivateField("preProcessor");
        Field curCharField = getPrivateField("curChar");
        Field currentLineField = getPrivateField("currentLine");
        Field endPosInLineField = getPrivateField("endPosInLine");
        Field startPosInLineField = getPrivateField("startPosInLine");
        Method advanceMethod = getPrivateMethod("advance");

        PreProcessor preProcessor = mock(PreProcessor.class);
        StatefulLexer statefulLexer = new StatefulLexer();
        preProcessorField.set(statefulLexer, preProcessor);

        // A - Base case, advance 1 char
        Line line0 = mock(Line.class);
        when(line0.getCodeLine()).thenReturn("SOME CODE");
        when(line0.isBlank()).thenReturn(false);
        when(line0.isComment()).thenReturn(false);
        ArrayList<Line> lineList = new ArrayList<>();
        lineList.add(line0);
        when(preProcessor.getLine(0)).thenReturn(line0);
        when(preProcessor.getCodeLine(0)).thenReturn("SOME CODE");
        when(preProcessor.getPreProcessedLines()).thenReturn(lineList);
        curCharField.set(statefulLexer, 'S');
        currentLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        advanceMethod.invoke(statefulLexer);
        assertEquals(2, endPosInLineField.get(statefulLexer));
        assertEquals(0, currentLineField.get(statefulLexer));
        assertEquals(0, startPosInLineField.get(statefulLexer));
        assertEquals('O', curCharField.get(statefulLexer));

        // B - End of line, no more lines
        curCharField.set(statefulLexer, 'E');
        currentLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 10);
        startPosInLineField.set(statefulLexer, 0);
        advanceMethod.invoke(statefulLexer);
        assertEquals(0, endPosInLineField.get(statefulLexer));
        assertEquals(-1, currentLineField.get(statefulLexer));
        assertEquals(0, startPosInLineField.get(statefulLexer));
        assertEquals('E', curCharField.get(statefulLexer));

        // C - End of line, more lines, some blank
        Line line1 = mock(Line.class);
        when(line1.getCodeLine()).thenReturn(null);
        when(line1.isBlank()).thenReturn(true);
        when(line1.isComment()).thenReturn(false);
        Line line2 = mock(Line.class);
        when(line2.getCodeLine()).thenReturn("OTHER CODE");
        when(line2.isBlank()).thenReturn(false);
        when(line2.isComment()).thenReturn(false);
        Line line3 = mock(Line.class);
        when(line3.getCodeLine()).thenReturn(null);
        when(line3.isBlank()).thenReturn(false);
        when(line3.isComment()).thenReturn(true);
        lineList.add(line1);
        lineList.add(line2);
        lineList.add(line3);
        when(preProcessor.getLine(1)).thenReturn(line1);
        when(preProcessor.getLine(2)).thenReturn(line2);
        when(preProcessor.getLine(3)).thenReturn(line3);
        when(preProcessor.getCodeLine(2)).thenReturn("OTHER CODE");
        when(preProcessor.getCodeLine(1)).thenReturn(null);
        when(preProcessor.getCodeLine(3)).thenReturn(null);

        curCharField.set(statefulLexer, 'E');
        currentLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 10);
        startPosInLineField.set(statefulLexer, 0);
        advanceMethod.invoke(statefulLexer);
        assertEquals(1, endPosInLineField.get(statefulLexer));
        assertEquals(2, currentLineField.get(statefulLexer));
        assertEquals(0, startPosInLineField.get(statefulLexer));
        assertEquals('O', curCharField.get(statefulLexer));
        for (int i = 0; i < 11; i++) {
            advanceMethod.invoke(statefulLexer);
        }
        assertEquals(0, endPosInLineField.get(statefulLexer));
        assertEquals(-1, currentLineField.get(statefulLexer));
        assertEquals(0, startPosInLineField.get(statefulLexer));
        assertEquals('E', curCharField.get(statefulLexer));
    }

    @Test
    void testGlobalIdentifier() throws IllegalAccessException, InvocationTargetException {
        Field preProcessorField = getPrivateField("preProcessor");
        Field curCharField = getPrivateField("curChar");
        Field currentLineField = getPrivateField("currentLine");
        Field endPosInLineField = getPrivateField("endPosInLine");
        Field startPosInLineField = getPrivateField("startPosInLine");
        Method identifierGlobalMethod = getPrivateMethod("identifierGlobal");

        PreProcessor preProcessor = mock(PreProcessor.class);
        StatefulLexer statefulLexer = new StatefulLexer();
        preProcessorField.set(statefulLexer, preProcessor);

        // Test 1: end of division with dot
        String codeLine0 = "IDENTIFICATION DIVISION.";
        Line line0 = mock(Line.class);
        ArrayList<Line> lineList = new ArrayList<>();
        lineList.add(line0);
        when(preProcessor.getCodeLine(0)).thenReturn(codeLine0);
        when(preProcessor.getPreProcessedLines()).thenReturn(lineList);
        when(line0.getCodeLine()).thenReturn(codeLine0);
        when(line0.isBlank()).thenReturn(false);
        when(line0.isComment()).thenReturn(false);
        curCharField.set(statefulLexer, 'I');
        currentLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        Token produced = (Token) identifierGlobalMethod.invoke(statefulLexer);
        assertEquals(TokenType.IDENTIFICATION_DIVISION, produced.getType());
        assertEquals("IDENTIFICATION DIVISION", produced.getLexeme());
        assertEquals(23, endPosInLineField.get(statefulLexer));
        assertEquals(23, startPosInLineField.get(statefulLexer));
        assertEquals('N', curCharField.get(statefulLexer));

        // Test 2: normal identifier with dot, single line
        String codeLine1 = "P-ID. MY-ID.";
        Line line1 = mock(Line.class);
        when(line1.getCodeLine()).thenReturn(codeLine0);
        when(line1.isBlank()).thenReturn(false);
        when(line1.isComment()).thenReturn(false);
        when(preProcessor.getCodeLine(1)).thenReturn(codeLine1);
        lineList.add(line1);
        curCharField.set(statefulLexer, 'P');
        currentLineField.set(statefulLexer, 1);
        endPosInLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        produced = (Token) identifierGlobalMethod.invoke(statefulLexer);
        assertEquals(TokenType.IDENTIFIER, produced.getType());
        assertEquals("P-ID", produced.getLexeme());
        assertEquals(5, endPosInLineField.get(statefulLexer));
        assertEquals(4, startPosInLineField.get(statefulLexer));
        assertEquals('.', curCharField.get(statefulLexer));

        // Test 3: other keyword
        String codeLine2 = "STOP.";
        Line line2 = mock(Line.class);
        when(line2.getCodeLine()).thenReturn(codeLine2);
        when(line2.isBlank()).thenReturn(false);
        when(line2.isComment()).thenReturn(false);
        when(preProcessor.getCodeLine(2)).thenReturn(codeLine2);
        lineList.add(line2);
        curCharField.set(statefulLexer, 'S');
        currentLineField.set(statefulLexer, 2);
        endPosInLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        produced = (Token) identifierGlobalMethod.invoke(statefulLexer);
        assertEquals(TokenType.KEYWORD, produced.getType());
        assertEquals("STOP", produced.getLexeme());
        assertEquals(4, endPosInLineField.get(statefulLexer));
        assertEquals(4, startPosInLineField.get(statefulLexer));
        assertEquals('P', curCharField.get(statefulLexer));

        // Test 4: check different forms of division names are recognized
        String codeLine3 = "DATA DIVISION.";
        String codeLine4 = "DATADIVISION.";
        Line line3 = mock(Line.class);
        Line line4 = mock(Line.class);
        when(line3.getCodeLine()).thenReturn(codeLine3);
        when(line3.isBlank()).thenReturn(false);
        when(line3.isComment()).thenReturn(false);
        when(line4.getCodeLine()).thenReturn(codeLine4);
        when(line4.isBlank()).thenReturn(false);
        when(line4.isComment()).thenReturn(false);
        when(preProcessor.getCodeLine(3)).thenReturn(codeLine3);
        when(preProcessor.getCodeLine(4)).thenReturn(codeLine4);
        lineList.add(line3);
        lineList.add(line4);
        curCharField.set(statefulLexer, 'D');
        currentLineField.set(statefulLexer, 3);
        endPosInLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        produced = (Token) identifierGlobalMethod.invoke(statefulLexer);
        assertEquals(TokenType.DATA_DIVISION, produced.getType());
        assertEquals("DATA DIVISION", produced.getLexeme());
        assertEquals(13, endPosInLineField.get(statefulLexer));
        assertEquals(13, startPosInLineField.get(statefulLexer));
        assertEquals('N', curCharField.get(statefulLexer));

        curCharField.set(statefulLexer, 'D');
        currentLineField.set(statefulLexer, 4);
        endPosInLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        produced = (Token) identifierGlobalMethod.invoke(statefulLexer);
        assertEquals(TokenType.DATA_DIVISION, produced.getType());
        assertEquals("DATADIVISION", produced.getLexeme());
        assertEquals(12, endPosInLineField.get(statefulLexer));
        assertEquals(12, startPosInLineField.get(statefulLexer));
        assertEquals('N', curCharField.get(statefulLexer));

    }

    @Test
    void testNextToken_whenStateGlobalOrId() {
        List<String> fullText = new ArrayList<>();
        fullText.add(" ".repeat(6) + " " + "IDENTIFICATION DIVISION.");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "MY-ID. MY-VALUE.");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "ANOTHER-ID. ANOTHER-VALUE.");

        StatefulLexer statefulLexer = new StatefulLexer(fullText);
        List<Token> producedTokens = new ArrayList<>();
        for (int i=0; i < 11; i++) {
            producedTokens.add(statefulLexer.nextToken());
        }
        assertEquals(11, producedTokens.size());
        assertEquals(TokenType.IDENTIFICATION_DIVISION, producedTokens.get(0).getType());
        assertEquals(TokenType.DOT, producedTokens.get(1).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens.get(2).getType());
        assertEquals(TokenType.DOT, producedTokens.get(3).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens.get(4).getType());
        assertEquals(TokenType.DOT, producedTokens.get(5).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens.get(6).getType());
        assertEquals(TokenType.DOT, producedTokens.get(7).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens.get(8).getType());
        assertEquals(TokenType.DOT, producedTokens.get(9).getType());
        assertEquals(TokenType.EOF, producedTokens.get(10).getType());

        List<String> fullText2 = new ArrayList<>();
        fullText2.add(" ".repeat(6) + " " + "IDENTIFICATION DIVISION.");
        fullText2.add(" ".repeat(6) + "*" + "This is just a comment.");
        fullText2.add(" ".repeat(6) + " " + " ".repeat(4) + "MY-ID. MY-VALUE.");
        fullText2.add(" ".repeat(6) + " " + " ".repeat(4) + " ".repeat(30)); // blank line
        fullText2.add(" ".repeat(6) + " " + " ".repeat(4) + "ANOTHER-ID. ANOTHER-VALUE.");
        StatefulLexer statefulLexer2 = new StatefulLexer(fullText2);
        List<Token> producedTokens2 = new ArrayList<>();
        for (int i=0; i < 11; i++) {
            producedTokens2.add(statefulLexer2.nextToken());
        }
        assertEquals(11, producedTokens2.size());
        assertEquals(TokenType.IDENTIFICATION_DIVISION, producedTokens2.get(0).getType());
        assertEquals(TokenType.DOT, producedTokens2.get(1).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens2.get(2).getType());
        assertEquals(TokenType.DOT, producedTokens2.get(3).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens2.get(4).getType());
        assertEquals(TokenType.DOT, producedTokens2.get(5).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens2.get(6).getType());
        assertEquals(TokenType.DOT, producedTokens2.get(7).getType());
        assertEquals(TokenType.IDENTIFIER, producedTokens2.get(8).getType());
        assertEquals(TokenType.DOT, producedTokens2.get(9).getType());
        assertEquals(TokenType.EOF, producedTokens2.get(10).getType());
    }

    @Test
    void testLiteral() throws IllegalAccessException, InvocationTargetException {
        Field preProcessorField = getPrivateField("preProcessor");
        Field curCharField = getPrivateField("curChar");
        Field currentLineField = getPrivateField("currentLine");
        Field endPosInLineField = getPrivateField("endPosInLine");
        Field startPosInLineField = getPrivateField("startPosInLine");
        Method literalMethod = getPrivateMethod("literal");

        PreProcessor preProcessor = mock(PreProcessor.class);
        StatefulLexer statefulLexer = new StatefulLexer();
        preProcessorField.set(statefulLexer, preProcessor);

        // Test 1: Complete literal
        String codeLine0 = "DISPLAY \"Enter the limit:\".";
        Line line0 = mock(Line.class);
        ArrayList<Line> lineList = new ArrayList<>();
        lineList.add(line0);
        when(preProcessor.getCodeLine(0)).thenReturn(codeLine0);
        when(preProcessor.getPreProcessedLines()).thenReturn(lineList);
        when(line0.getCodeLine()).thenReturn(codeLine0);
        when(line0.isBlank()).thenReturn(false);
        when(line0.isComment()).thenReturn(false);
        curCharField.set(statefulLexer, '"');
        currentLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 9);
        startPosInLineField.set(statefulLexer, 8);
        Token produced = (Token) literalMethod.invoke(statefulLexer);
        assertEquals(TokenType.STRING_LITERAL, produced.getType());
        assertEquals("\"Enter the limit:\"", produced.getLexeme());
        assertEquals(26, endPosInLineField.get(statefulLexer));
        assertEquals(26, startPosInLineField.get(statefulLexer));
        assertEquals('"', curCharField.get(statefulLexer));

        // Test 2: Incomplete literal
        String codeLine1 = "DISPLAY \"Enter the limit:";
        Line line1 = mock(Line.class);
        when(line1.getCodeLine()).thenReturn(codeLine1);
        when(line1.isBlank()).thenReturn(false);
        when(line1.isComment()).thenReturn(false);
        when(preProcessor.getCodeLine(1)).thenReturn(codeLine1);
        when(preProcessor.getPreProcessedLines()).thenReturn(lineList);
        lineList.add(line1);
        curCharField.set(statefulLexer, '"');
        currentLineField.set(statefulLexer, 1);
        endPosInLineField.set(statefulLexer, 9);
        startPosInLineField.set(statefulLexer, 8);
        produced = (Token) literalMethod.invoke(statefulLexer);
        assertEquals(TokenType.STRING_LITERAL, produced.getType());
        assertEquals("\"Enter the limit:", produced.getLexeme());
        assertEquals(25, endPosInLineField.get(statefulLexer));
        assertEquals(25, startPosInLineField.get(statefulLexer));
        assertEquals(':', curCharField.get(statefulLexer));
    }

    @Test
    void testNumber() throws IllegalAccessException, InvocationTargetException {
        Field preProcessorField = getPrivateField("preProcessor");
        Field curCharField = getPrivateField("curChar");
        Field currentLineField = getPrivateField("currentLine");
        Field endPosInLineField = getPrivateField("endPosInLine");
        Field startPosInLineField = getPrivateField("startPosInLine");
        Method numberMethod = getPrivateMethod("number");

        PreProcessor preProcessor = mock(PreProcessor.class);
        StatefulLexer statefulLexer = new StatefulLexer();
        preProcessorField.set(statefulLexer, preProcessor);
        ArrayList<Line> lineList = new ArrayList<>();
        when(preProcessor.getPreProcessedLines()).thenReturn(lineList);

        // Test 1: single number
        String codeLine0 = "ADD 123 TO X.";
        Line line0 = mock(Line.class);
        lineList.add(line0);
        when(preProcessor.getCodeLine(0)).thenReturn(codeLine0);
        when(line0.getCodeLine()).thenReturn(codeLine0);
        when(line0.isBlank()).thenReturn(false);
        when(line0.isComment()).thenReturn(false);
        curCharField.set(statefulLexer, '1');
        currentLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 4);
        Token produced = (Token) numberMethod.invoke(statefulLexer);
        assertEquals(TokenType.NUMBER_LITERAL, produced.getType());
        assertEquals("123", produced.getLexeme());
        assertEquals(8, endPosInLineField.get(statefulLexer));
        assertEquals(7, startPosInLineField.get(statefulLexer));
        assertEquals(' ', curCharField.get(statefulLexer));

        // Test 2: multiple numbers
        String codeLine1 = "ADD 1 2 3 TO X.";
        Line line1 = mock(Line.class);
        lineList.add(line1);
        when(preProcessor.getCodeLine(1)).thenReturn(codeLine1);
        when(line1.getCodeLine()).thenReturn(codeLine1);
        when(line1.isBlank()).thenReturn(false);
        when(line1.isComment()).thenReturn(false);
        curCharField.set(statefulLexer, '1');
        currentLineField.set(statefulLexer, 1);
        endPosInLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 4);
        produced = (Token) numberMethod.invoke(statefulLexer);
        assertEquals(TokenType.NUMBER_LITERAL, produced.getType());
        assertEquals("1", produced.getLexeme());
        assertEquals(6, endPosInLineField.get(statefulLexer));
        assertEquals(5, startPosInLineField.get(statefulLexer));
        assertEquals(' ', curCharField.get(statefulLexer));

        // Test 3: single number with decimal
        String codeLine2 = "ADD 123.456 TO X.";
        Line line2 = mock(Line.class);
        lineList.add(line2);
        when(preProcessor.getCodeLine(2)).thenReturn(codeLine2);
        when(line2.getCodeLine()).thenReturn(codeLine2);
        when(line2.isBlank()).thenReturn(false);
        when(line2.isComment()).thenReturn(false);
        curCharField.set(statefulLexer, '1');
        currentLineField.set(statefulLexer, 2);
        endPosInLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 4);
        produced = (Token) numberMethod.invoke(statefulLexer);
        assertEquals(TokenType.NUMBER_LITERAL, produced.getType());
        assertEquals("123.456", produced.getLexeme());
        assertEquals(12, endPosInLineField.get(statefulLexer));
        assertEquals(11, startPosInLineField.get(statefulLexer));
        assertEquals(' ', curCharField.get(statefulLexer));

        // Test 4: multiple numbers with decimal
        String codeLine3 = "ADD 1.2 3.4 5.6 TO X.";
        Line line3 = mock(Line.class);
        lineList.add(line3);
        when(preProcessor.getCodeLine(3)).thenReturn(codeLine3);
        when(line3.getCodeLine()).thenReturn(codeLine3);
        when(line3.isBlank()).thenReturn(false);
        when(line3.isComment()).thenReturn(false);
        curCharField.set(statefulLexer, '1');
        currentLineField.set(statefulLexer, 3);
        endPosInLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 4);
        produced = (Token) numberMethod.invoke(statefulLexer);
        assertEquals(TokenType.NUMBER_LITERAL, produced.getType());
        assertEquals("1.2", produced.getLexeme());
        assertEquals(8, endPosInLineField.get(statefulLexer));
        assertEquals(7, startPosInLineField.get(statefulLexer));
        assertEquals(' ', curCharField.get(statefulLexer));

        // Test 5: number with separator and whitespace should be tokenized separately
        String codeLine4 = "ADD 1,   000 TO X.";
        String codeLine5 = "ADD 1 .2 TO X.";
        Line line4 = mock(Line.class);
        Line line5 = mock(Line.class);
        lineList.add(line4);
        lineList.add(line5);
        when(preProcessor.getCodeLine(4)).thenReturn(codeLine4);
        when(preProcessor.getCodeLine(5)).thenReturn(codeLine5);
        when(line4.getCodeLine()).thenReturn(codeLine4);
        when(line5.getCodeLine()).thenReturn(codeLine5);
        when(line4.isBlank()).thenReturn(false);
        when(line5.isBlank()).thenReturn(false);
        when(line4.isComment()).thenReturn(false);
        when(line5.isComment()).thenReturn(false);

        curCharField.set(statefulLexer, '1');
        currentLineField.set(statefulLexer, 4);
        endPosInLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 4);
        produced = (Token) numberMethod.invoke(statefulLexer);
        assertEquals(TokenType.NUMBER_LITERAL, produced.getType());
        assertEquals("1", produced.getLexeme());
        assertEquals(6, endPosInLineField.get(statefulLexer));
        assertEquals(5, startPosInLineField.get(statefulLexer));
        assertEquals(',', curCharField.get(statefulLexer));

        curCharField.set(statefulLexer, '1');
        currentLineField.set(statefulLexer, 5);
        endPosInLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 4);
        produced = (Token) numberMethod.invoke(statefulLexer);
        assertEquals(TokenType.NUMBER_LITERAL, produced.getType());
        assertEquals("1", produced.getLexeme());
        assertEquals(6, endPosInLineField.get(statefulLexer));
        assertEquals(5, startPosInLineField.get(statefulLexer));
        assertEquals(' ', curCharField.get(statefulLexer));
    }

    @Test
    void testNextToken_whenStateProc_numberLiterals() throws IllegalAccessException {
        Field currentLineField = getPrivateField("currentLine");
        Field endPosInLineField = getPrivateField("endPosInLine");
        Field startPosInLineField = getPrivateField("startPosInLine");

        List<String> fullText = new ArrayList<>();
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "123");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "1 2 3 ");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "123.456");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "1.2 3.4 5.6");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "1,   000");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "1 .2 ");
        fullText.add(" ".repeat(6) + " " + " ".repeat(4) + "1 .2 6 ");

        StatefulLexer statefulLexer = new StatefulLexer(fullText);
        statefulLexer.setLexerState(LexerState.PROCEDURE);
        currentLineField.set(statefulLexer, 0);

        List<Token> producedTokens0 = new ArrayList<>();
        producedTokens0.add(statefulLexer.nextToken());
        assertEquals("123", producedTokens0.get(0).getLexeme());

        currentLineField.set(statefulLexer, 1);
        startPosInLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 0);
        List<Token> producedTokens1 = new ArrayList<>();
        for (int i=0; i < 3; i++) {
            producedTokens1.add(statefulLexer.nextToken());
        }
        assertEquals("1", producedTokens1.get(0).getLexeme());
        assertEquals("2", producedTokens1.get(1).getLexeme());
        assertEquals("3", producedTokens1.get(2).getLexeme());

        currentLineField.set(statefulLexer, 2);
        startPosInLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 0);
        List<Token> producedTokens2 = new ArrayList<>();
        producedTokens2.add(statefulLexer.nextToken());
        assertEquals("123.456", producedTokens2.get(0).getLexeme());

        currentLineField.set(statefulLexer, 3);
        startPosInLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 0);
        List<Token> producedTokens3 = new ArrayList<>();
        for (int i=0; i < 3; i++) {
            producedTokens3.add(statefulLexer.nextToken());
        }
        assertEquals("1.2", producedTokens3.get(0).getLexeme());
        assertEquals("3.4", producedTokens3.get(1).getLexeme());
        assertEquals("5.6", producedTokens3.get(2).getLexeme());

        currentLineField.set(statefulLexer, 4);
        startPosInLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 0);
        List<Token> producedTokens4 = new ArrayList<>();
        for (int i=0; i < 3; i++) {
            producedTokens4.add(statefulLexer.nextToken());
        }
        assertEquals("1", producedTokens4.get(0).getLexeme());
        assertEquals(",", producedTokens4.get(1).getLexeme());
        assertEquals(TokenType.COMMA, producedTokens4.get(1).getType());
        assertEquals("000", producedTokens4.get(2).getLexeme());

        currentLineField.set(statefulLexer, 5);
        startPosInLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 0);
        List<Token> producedTokens5 = new ArrayList<>();
        for (int i=0; i < 3; i++) {
            producedTokens5.add(statefulLexer.nextToken());
        }
        assertEquals("1", producedTokens5.get(0).getLexeme());
        assertEquals(".", producedTokens5.get(1).getLexeme());
        assertEquals(TokenType.DOT, producedTokens5.get(1).getType());
        assertEquals("2", producedTokens5.get(2).getLexeme());

        currentLineField.set(statefulLexer, 6);
        startPosInLineField.set(statefulLexer, 0);
        endPosInLineField.set(statefulLexer, 0);
        List<Token> producedTokens6 = new ArrayList<>();
        for (int i=0; i < 4; i++) {
            producedTokens6.add(statefulLexer.nextToken());
        }
        assertEquals("1", producedTokens6.get(0).getLexeme());
        assertEquals(".", producedTokens6.get(1).getLexeme());
        assertEquals(TokenType.DOT, producedTokens6.get(1).getType());
        assertEquals("2", producedTokens6.get(2).getLexeme());
        assertEquals("6", producedTokens6.get(3).getLexeme());
    }
}