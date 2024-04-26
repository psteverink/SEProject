parser grammar BabyCobolGrammar;
options{
tokenVocab = BabyCobolLexer;
}

program:	 identificationdivision datadivision? proceduredivision? EOF ;


identificationdivision: 'IDENTIFICATION DIVISION' '.'?  (identificationname '.' identificationvalue)* '.'?;
identificationname: 'PROGRAM-ID'|'AUTHOR'|'INSTALLATION'|'DATE-WRITTEN'|'DATE-COMPILED'|'SECURITY';
identificationvalue: IDENTIFIER;



datadivision: 'DATA DIVISION' '.'  dataDescriptionEntry+;
dataDescriptionEntry: level IDENTIFIER('LIKE' IDENTIFIER | 'PICTURE IS' REPRESENTATION) '.';
level: INT;



proceduredivision: 'PROCEDURE DIVISION''.' using? sentence* paragraph*;

using: 'USING' ('BY' (('REFERENCE' IDENTIFIER)|('CONTENT' atomic)|('VALUE' atomic)))+;
sentence: statement '.'?;
statement: accept| add | alter | call | copy | display | divide | evaluate | goto | if | loop |
            move | multiply | nextsentence | perform | signal | stop | subtract;
paragraph: IDENTIFIER '.' sentence+;


//
//(LEVEL IDENTIFIER (('LIKE' IDENTIFIER)|('PICTURE IS' REPRESENTATION))? ('OCCURS' INT 'TIMES')? )+;
//STATEMENTS:



accept: 'ACCEPT' IDENTIFIER+;


add: 'ADD' atomic 'TO' atomic ('GIVING' IDENTIFIER)*;


alter: 'ALTER' procedurename 'TO''PROCEED''TO' procedurename;
    procedurename: IDENTIFIER;

call: 'CALL' (callfile|callprogram);
    callfile: filename ('USING' (byreference|bycontent|byvalue)+)?;
        byreference: 'BY''REFERENCE' IDENTIFIER;
        bycontent: 'BY''CONTENT' atomic;
        byvalue: 'BY''VALUE' atomic;
        filename: IDENTIFIER;

    callprogram: (functionname 'OF')? programname callprogramusing? callprogramreturning?;
        callprogramusing: 'USING' ((('BY' 'REFERENCE')|('BY''CONTENT')|('BY''VALUE'))* atomic (('AS''PRIMITIVE')|('AS''STRUCT'))*)+;
        callprogramreturning: 'RETURNING' (('BY''REFERENCE')|('BY''CONTENT')|('BY''VALUE'))* IDENTIFIER (('AS''PRIMITIVE')|('AS''STRUCT'))*;
        functionname: IDENTIFIER;
        programname: IDENTIFIER;

copy: 'COPY' filename ('REPLACING' (stringliteral 'BY' stringliteral)+)?;

display: 'DISPLAY' ( atomic   )+ ('WITH''NO''ADVANCING')?;


divide: 'DIVIDE' atomic 'INTO' atomic+ ('GIVING' IDENTIFIER+ ('REMAINDER' IDENTIFIER)?)?;


evaluate: 'EVALUATE' anyexpression ('ALSO' anyexpression)* (whenclause statement+)+;
    anyexpression:;
    whenclause: 'WHEN' 'OTHER'|(atomic ('THROUGH' atomic)? ('ALSO' (atomic ('THROUGH' atomic)?)*));


goto: 'GOTO' procedurename;


if: 'IF' boolexpr 'THEN' ((statement+ ('ELSE' statement+)? 'END')|(statement+ ('ELSE' statement+)?));


loop: 'LOOP' (loopvarying| loopwhile| loopuntil| statement)* 'END';
    loopvarying: 'VARYING' IDENTIFIER? ('FROM' atomic)?('TO' atomic)?('BY' atomic)?;
    loopwhile: 'WHILE' boolexpr;
    loopuntil: 'UNTIL' boolexpr;


move: 'MOVE' (atomic|'HIGH-VALUES'|'LOW-VALUES'|'SPACES') 'TO' IDENTIFIER+;


multiply: 'MULTIPLY' atomic 'BY' atomic+ ('GIVING' IDENTIFIER)?;


nextsentence: 'NEXT_SENTENCE';


perform: 'PERFORM' procedurename ('THROUGH' procedurename)? (atomic 'TIMES')?;


signal: 'SIGNAL' procedurename|'OFF' 'ON''ERROR';


stop: 'STOP' ;


subtract: 'SUBTRACT' atomic 'FROM' atomic ('GIVING' IDENTIFIER)*;


atomic: IDENTIFIER| stringliteral| INT;
    stringliteral: '"' IDENTIFIER? '"';
boolexpr: equality;
equality: comparison (('='|'!''=') comparison)*;
comparison: term (('>'|'<') term)*;
term: '!' term | primary;
primary: atomic | 'TRUE'| 'FALSE' | '(' boolexpr ')';

//TOKENS:


//These are not tokens, they are expressions



//LEXER RULES:
