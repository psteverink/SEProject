grammar Grammar;

program:	 identificationdivision datadivision? proceduredivision? EOF ;


identificationdivision: 'IDENTIFICATION DIVISION' (identificationname identificationvalue)* '.'?;
identificationname: IDENTIFIER;
identificationvalue: IDENTIFIER;
proceduredivision: 'PROCEDURE 'paragraph+;
paragraph: sentence+;
sentence: expr;

datadivision: ' ';

expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
NEWLINE : [\r\n]+ -> skip;
INT     : [0-9]+ ;
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]+;