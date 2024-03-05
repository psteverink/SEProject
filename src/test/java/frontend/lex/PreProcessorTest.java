package frontend.lex;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PreProcessorTest {

    Method getPrivateMethod(String methodName) {
        Method[] methods = PreProcessor.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    Field getPrivateField(String fieldName) {
        Field[] fields = PreProcessor.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }


    @Test
    void testTrimLines() throws IllegalAccessException, InvocationTargetException {
        Method trimLinesMethod = getPrivateMethod("trimLines");
        Field fullTextField = getPrivateField("fullText");

        String testText = "\n" +
                "    \n" +
                "ABCDE\n" +
                "  01 \n" +
                "\t\t \n";
        List<String> testTextLines = Arrays.asList(testText.split("\n"));
        PreProcessor preProcessor = new PreProcessor(testTextLines);

        // Before trimming
        @SuppressWarnings("unchecked")
        List<String> fullTextContent = (List<String>) fullTextField.get(preProcessor);
        assertEquals(5, fullTextContent.size());

        // After trimming
        trimLinesMethod.invoke(preProcessor);
        fullTextContent = (List<String>) fullTextField.get(preProcessor);
        assertEquals(2, fullTextContent.size());
    }

    @Test
    void testProcessLineNo() throws InvocationTargetException, IllegalAccessException {
        Method processLineNoMethod = getPrivateMethod("processLineNo");
        Field lineIssuesField = getPrivateField("lineIssues");
        Line mockLine = mock(Line.class);
        ArrayList<String> testText = new ArrayList<>();
        testText.add("test");
        PreProcessor preProcessor = new PreProcessor(testText);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // With empty col nums - should not set line number
        String testString1 = "       SOME CODE";
        processLineNoMethod.invoke(preProcessor, testString1, mockLine);
        verify(mockLine, times(0)).setGivenLineNo(anyString());
        Mockito.reset(mockLine);

        // With valid line number
        String testString2 = "0001   SOME CODE";
        processLineNoMethod.invoke(preProcessor, testString2, mockLine);
        verify(mockLine, times(1)).setGivenLineNo(stringArgumentCaptor.capture());
        assertEquals("0001", stringArgumentCaptor.getValue());
        @SuppressWarnings("unchecked")
        HashMap<Integer, List<LineIssue>> lineIssues =
                (HashMap<Integer, List<LineIssue>>) lineIssuesField.get(preProcessor);
        assertEquals(0, lineIssues.size());
        Mockito.reset(mockLine);

        // With invalid line number
        String testString3 = "0A01   SOME CODE";
        processLineNoMethod.invoke(preProcessor, testString3, mockLine);
        verify(mockLine, times(0)).setGivenLineNo(anyString());
        lineIssues = (HashMap<Integer, List<LineIssue>>) lineIssuesField.get(preProcessor);
        assertEquals(1, lineIssues.size());
    }

    @Test
    void testCheckStatus() throws InvocationTargetException, IllegalAccessException {
        Method checkStatusMethod = getPrivateMethod("checkStatus");
        Field lineIssuesField = getPrivateField("lineIssues");
        Line mockLine = mock(Line.class);
        ArrayList<String> testText = new ArrayList<>();
        testText.add("test");
        PreProcessor preProcessor = new PreProcessor(testText);

        // With empty status
        String testString1 = "000001 SOME CODE";
        checkStatusMethod.invoke(preProcessor, testString1, mockLine);
        verify(mockLine, times(0)).setComment(anyBoolean());
        verify(mockLine, times(0)).setContinuation(anyBoolean());
        @SuppressWarnings("unchecked")
        HashMap<Integer, List<LineIssue>> lineIssues1 =
                (HashMap<Integer, List<LineIssue>>) lineIssuesField.get(preProcessor);
        assertEquals(0, lineIssues1.size());
        Mockito.reset(mockLine);

        // With comment status
        String testString2 = "000001*SOME CODE";
        checkStatusMethod.invoke(preProcessor, testString2, mockLine);
        verify(mockLine, times(1)).setComment(true);
        verify(mockLine, times(0)).setContinuation(anyBoolean());
        @SuppressWarnings("unchecked")
        HashMap<Integer, List<LineIssue>> lineIssues2 =
                (HashMap<Integer, List<LineIssue>>) lineIssuesField.get(preProcessor);
        assertEquals(0, lineIssues2.size());
        Mockito.reset(mockLine);


        // With continuation status
        String testString3 = "000001-SOME CODE";
        checkStatusMethod.invoke(preProcessor, testString3, mockLine);
        verify(mockLine, times(0)).setComment(anyBoolean());
        verify(mockLine, times(1)).setContinuation(true);
        @SuppressWarnings("unchecked")
        HashMap<Integer, List<LineIssue>> lineIssues3 =
                (HashMap<Integer, List<LineIssue>>) lineIssuesField.get(preProcessor);
        assertEquals(0, lineIssues3.size());
        Mockito.reset(mockLine);

        // With invalid status
        String testString4 = "000001@SOME CODE";
        checkStatusMethod.invoke(preProcessor, testString4, mockLine);
        verify(mockLine, times(0)).setComment(anyBoolean());
        verify(mockLine, times(0)).setContinuation(anyBoolean());
        @SuppressWarnings("unchecked")
        HashMap<Integer, List<LineIssue>> lineIssues4 =
                (HashMap<Integer, List<LineIssue>>) lineIssuesField.get(preProcessor);
        assertEquals(1, lineIssues4.size());
    }

    @Test
    void testExtractCode() throws InvocationTargetException, IllegalAccessException {
        Method extractCodeMethod = getPrivateMethod("extractCode");
        Line mockLine = mock(Line.class);
        ArrayList<String> testText = new ArrayList<>();
        testText.add("test");
        PreProcessor preProcessor = new PreProcessor(testText);

        // With empty code
        String testString1 = "000001" + " ".repeat(74);
        extractCodeMethod.invoke(preProcessor, testString1, mockLine);
        verify(mockLine, times(0)).setCodeStart(anyInt());
        verify(mockLine, times(0)).setCodeLine(anyString());
        Mockito.reset(mockLine);

        // If code starts after area B end
        String testString2 = "000001" + " ".repeat(PreProcessor.B_AREA[1] - 6) + "ABCDE";
        extractCodeMethod.invoke(preProcessor, testString2, mockLine);
        verify(mockLine, times(0)).setCodeStart(anyInt());
        verify(mockLine, times(0)).setCodeLine(anyString());
        Mockito.reset(mockLine);

        // With code
        String testString3 = "000001" + " " + " ".repeat(3) + "SOME CODE" + " ".repeat(61);
        extractCodeMethod.invoke(preProcessor, testString3, mockLine);
        verify(mockLine, times(1)).setCodeStart(PreProcessor.A_AREA[0] + 3);
        verify(mockLine, times(1)).setCodeLine("SOME CODE");
        Mockito.reset(mockLine);
    }

}