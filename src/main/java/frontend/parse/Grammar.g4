parser grammar Grammar;
options {
tokenVocab = GrammarLexer;
}
program:	 identificationdivision datadivision? proceduredivision? EOF ;


identificationdivision: 'IDENTIFICATION DIVISION' '.'?  (identificationname '.' identificationvalue)* '.'?;
identificationname: 'PROGRAM-ID'|'AUTHOR'|'INSTALLATION'|'DATE-WRITTEN'|'DATE-COMPILED'|'SECURITY';
identificationvalue: IDENTIFIER;



datadivision: 'Data division' '.'  dataDescriptionEntry+;
dataDescriptionEntry: level IDENTIFIER('LIKE' IDENTIFIER | 'PICTURE IS' REPRESENTATION) '.';
level: INT;



proceduredivision: 'PROCEDURE DIVISION''.' using? sentence* paragraph+;

using: 'USING' ('BY' (('REFERENCE' IDENTIFIER)|('CONTENT' ATOMIC)|('VALUE' ATOMIC)))+;
sentence: statement '.'?;
statement: accept| add | alter | call | copy | display | divide | evaluate | goto | if | loop |
            move | multiply | nextsentence | perform | signal | stop | subtract;
paragraph: IDENTIFIER '.' sentence+;


//
//(LEVEL IDENTIFIER (('LIKE' IDENTIFIER)|('PICTURE IS' REPRESENTATION))? ('OCCURS' INT 'TIMES')? )+;
//STATEMENTS:



accept: 'ACCEPT' IDENTIFIER+;


add: 'ADD' ATOMIC 'TO' ATOMIC ('GIVING' IDENTIFIER)*;


alter: 'ALTER' PROCEDURENAME 'TO PROCEED TO' PROCEDURENAME;


call: 'CALL' (callfile|callprogram);
    callfile: FILENAME ('USING' (byreference|bycontent|byvalue)+)?;
        byreference: 'BY REFERENCE' IDENTIFIER;
        bycontent: 'BY CONTENT' ATOMIC;
        byvalue: 'BY VALUE' ATOMIC;

    callprogram: (FUNCTIONNAME 'OF')? PROGRAMNAME callprogramusing? callprogramreturning?;
        callprogramusing: 'USING' (('BY' 'REFERENCE'|'BY''CONTENT'|'BY''VALUE')* ATOMIC ('AS''PRIMITIVE'|'AS''STRUCT')*)+;
        callprogramreturning: 'RETURNING' ('BY''REFERENCE'|'BY''CONTENT'|'BY''VALUE')* IDENTIFIER ('AS PRIMITIVE'|'AS STRUCT')*;


copy: 'COPY' FILENAME ('REPLACING' (LITERAL 'BY' LITERAL)+)?;


display: 'DISPLAY' (ATOMIC ('DELIMITED BY' ('SIZE'|'SPACE'|LITERAL))?)+ 'WITH NO ADVANCING'?;


divide: 'DIVIDE' ATOMIC 'INTO' ATOMIC+ ('GIVING' IDENTIFIER+ ('REMAINDER' IDENTIFIER)?)?;


evaluate: 'EVALUATE' anyexpression ('ALSO' anyexpression)* (whenclause statement+)+;
    anyexpression:;
    whenclause: 'WHEN' 'OTHER'|(ATOMIC ('THROUGH' ATOMIC)? ('ALSO' (ATOMIC ('THROUGH' ATOMIC)?)*));


goto: 'GOTO' PROCEDURENAME;


if: 'IF' boolexpr 'THEN' statement+ ('ELSE' statement+)? 'END'?;


loop: 'LOOP' (loopvarying| loopwhile| loopuntil| statement)*;
    loopvarying: 'VARYING' IDENTIFIER? ('FROM' ATOMIC)?('TO' ATOMIC)?('BY' ATOMIC)?;
    loopwhile: 'WHILE' boolexpr;
    loopuntil: 'UNTIL' boolexpr;


move: 'MOVE' (ATOMIC|'HIGH-VALUES'|'LOW-VALUES'|'SPACES') 'TO' IDENTIFIER+;


multiply: 'MULTIPLY' ATOMIC 'BY' ATOMIC+ ('GIVING' IDENTIFIER)?;


nextsentence: 'NEXT_SENTENCE';


perform: 'PERFORM' PROCEDURENAME ('THROUGH' PROCEDURENAME)? (ATOMIC 'TIMES')?;


signal: 'SIGNAL' PROCEDURENAME|'OFF' 'ON ERROR';


stop: 'STOP' ;


subtract: 'SUBTRACT' ATOMIC 'FROM' ATOMIC ('GIVING' IDENTIFIER)*;



boolexpr: 'TRUE';

expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
NEWLINE : [\r\n]+ -> skip;
INT     : [0-9]+ ;
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]+;