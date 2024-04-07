grammar Grammar;

program:	 identificationdivision datadivision? proceduredivision? EOF ;


identificationdivision: 'IDENTIFICATION DIVISION' (identificationname identificationvalue)* '.'?;
identificationname: IDENTIFIER;
identificationvalue: IDENTIFIER;


datadivision: 'DATA DIVISION.' 'WORKING-STORAGE SECTION.' dataDescriptionEntry+;
dataDescriptionEntry: LEVEL IDENTIFIER('LIKE' IDENTIFIER | 'PICTURE IS' REPRESENTATION) '.';


proceduredivision: 'PROCEDURE DIVISION.' using? paragraph+;
using: 'USING' ('BY' (('REFERENCE' IDENTIFIER)|('CONTENT' ATOMIC)|('VALUE' ATOMIC)))+;
paragraph: IDENTIFIER '.' sentence+;
sentence: expr '.';

//
//(LEVEL IDENTIFIER (('LIKE' IDENTIFIER)|('PICTURE IS' REPRESENTATION))? ('OCCURS' INT 'TIMES')? )+;

expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
NEWLINE : [\r\n]+ -> skip;
INT     : [0-9]+ ;
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]+;