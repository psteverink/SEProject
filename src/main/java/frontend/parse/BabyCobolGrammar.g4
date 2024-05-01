parser grammar BabyCobolGrammar;
options{
tokenVocab = BabyCobolLexer;
}


program:	identificationdivision datadivision? proceduredivision? EOF ;


identificationdivision:IDENTIFICATIONDIVISION (identificationname IDDOT identificationvalue IDDOT (IDNEWLINE IDCOLUMNS)?)+ ;
identificationname: NAME;
identificationvalue: NAME;



datadivision:DATADIVISION (dataDescriptionEntry (DATANEWLINE DATACOLUMNS)?)+;
dataDescriptionEntry: level identifier(DATALIKE identifier | DATAPICTUREIS DATAREPRESENTATION)? (DATAOCCURS int DATATIMES)? DATADOT;
level: DATALEVEL;





proceduredivision:
IDPROCEDUREDIVISION (using PRODNEWLINE)? (PROCEDURECOLUMNS sentence)* ( PARAGRAPHCOLUMNS paragraph)*
|DATAPROCEDUREDIVISION (using PRODNEWLINE)? (PROCEDURECOLUMNS sentence)* ( PARAGRAPHCOLUMNS paragraph)*
;

using: COMMANDCOLUMNS USING (BY ((REFERENCE identifier)|(CONTENT atomic)|(VALUE atomic)))+;



sentence: statement+ DOT? PRODNEWLINE?;
statement: accept| add | alter | call | copy | display | divide | evaluate | goto | if | loop |
            move | multiply | nextsentence | perform | signal | stop | subtract;
paragraph: identifier DOT PRODNEWLINE (PROCEDURECOLUMNS sentence)+ PRODNEWLINE?;





accept: ACCEPT identifier+;


add: ADD atomic+ TO atomic (GIVING identifier)*;


alter: ALTER procedurename TO PROCEED TO procedurename;
    procedurename: identifier;

call: CALL (callfile|callprogram);
    callfile: filename (USING (byreference|bycontent|byvalue)+)?;
        byreference: BY REFERENCE identifier;
        bycontent: BY CONTENT atomic;
        byvalue: BY VALUE atomic;
        filename: identifier;

    callprogram: (functionname OF)? programname callprogramusing? callprogramreturning?;
        callprogramusing: USING (((BY REFERENCE)|(BY CONTENT)|(BY VALUE))* atomic ((AS PRIMITIVE)|(AS STRUCT))*)+;
        callprogramreturning: RETURNING ((BY REFERENCE)|(BY CONTENT)|(BY VALUE))* identifier ((AS PRIMITIVE)|(AS STRUCT))*;
        functionname: identifier;
        programname: identifier;

copy: COPY filename (REPLACING (stringliteral BY stringliteral)+)?;

display: DISPLAY (atomic)+ (WITH NO ADVANCING)?;


divide: DIVIDE atomic INTO atomic+ (GIVING identifier+ (REMAINDER identifier)?)?;


evaluate: EVALUATE boolexpr (ALSO boolexpr)* (whenclause statement+)+;
    whenclause: WHEN OTHER|(atomic (THROUGH atomic)? (ALSO (atomic (THROUGH atomic)?)*));


goto: GOTO procedurename;


perform: PERFORM procedurename (THROUGH procedurename)? (atomic TIMES)?;


if
:IF boolexpr PRODNEWLINE PROCEDURECOLUMNS THEN (statement PRODNEWLINE PROCEDURECOLUMNS)+ (ELSE (statement PRODNEWLINE PROCEDURECOLUMNS)+)? END
|IF boolexpr PRODNEWLINE PROCEDURECOLUMNS THEN statement (PRODNEWLINE PROCEDURECOLUMNS ELSE statement)? DOT?
;


loop: LOOP (loopvarying| loopwhile| loopuntil| PROCEDURECOLUMNS statement PRODNEWLINE)* PROCEDURECOLUMNS END;
    loopvarying: VARYING identifier? (FROM atomic)?(TO atomic)?(BY atomic)? PRODNEWLINE?;
    loopwhile: WHILE boolexpr PRODNEWLINE;
    loopuntil: UNTIL boolexpr PRODNEWLINE;


move: MOVE (atomic|HIGHVALUES|LOWVALUES|SPACES) TO identifier+;


multiply: MULTIPLY atomic BY atomic+ (GIVING identifier)?;


nextsentence: NEXTSENTENCE;



signal: SIGNAL procedurename|OFF ON ERROR;


stop: STOP ;


subtract: SUBTRACT atomic FROM atomic (GIVING identifier)*;


atomic: identifier
| stringliteral
| int
;
    stringliteral: STRINGLITERAL|PROCSTRINGLITERAL;

boolexpr: equality;
equality: comparison (('=' |'!''=') comparison)*;
comparison: term (('>'|'<') term)*;
term: factor (('-'|'+') factor)*;
factor : unary (('*'|'/') unary)*;
unary: (('!'|'-') unary) | primary;
primary: atomic | TRUE| FALSE | '(' boolexpr ')';

//KEYWORDS:
keywords:IDENTIFICATIONDIVISION|OCCURS|DATADIVISION|LIKE|PICTUREIS|IDPROCEDUREDIVISION|USING|BY|REFERENCE|CONTENT|VALUE|ACCEPT|ADD|TO|OF|RETURNING|
GIVING|ALTER|PROCEED |CALL|PRIMITIVE|STRUCT|AS|COPY|REPLACING|DISPLAY|DELIMITED|SIZE|SPACE|WITH|NO|ADVANCING|
DIVIDE|INTO|REMAINDER|EVALUATE|ALSO|WHEN|OTHER|THROUGH|GOTO|IF|THEN|ELSE|END|LOOP|VARYING|FROM|WHILE|UNTIL|
MOVE|HIGHVALUES|LOWVALUES|SPACES|MULTIPLY|NEXTSENTENCE|PERFORM|TIMES|SIGNAL|OFF|ON|ERROR|STOP|SUBTRACT|TRUE|FALSE;
identifier:
DATAID (index)?
|PROCID (index)?
//| keywords
;
index: PROCID|DATAID;
int: (DATAINT|PROCINT);

