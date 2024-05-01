lexer grammar BabyCobolLexer;





COMMANDCOLUMNS: '       ';
COMMENTCOLUMNS :  '      *' ~[\r\n]* [\r\n]?-> skip ;
CONTINUATION: NEWLINE '      -' -> skip;
IDENTIFIER : (STRING|INT|MINUS|LPAREN|RPAREN)* STRING (STRING|INT|MINUS|LPAREN|RPAREN)*;
STRING: [a-zA-Z]+;
INT     : [0-9]+ ;
STRINGLITERAL: '"' (~["])*'"';
REPRESENTATION: S?(('9'|A|X|Z)('('INT')')?)+V? (('9'|A|X|Z)('('INT')')?)*;
WHITESPACE: [ \t]+ -> skip;
NEWLINE: [\r\n];


IDENTIFICATIONDIVISION: COMMANDCOLUMNS I D E N T I F I C A T I O N ' ' D I V I S I O N DOT NEWLINE COMMANDCOLUMNS-> mode(IDENTIFICATION_MODE);
LIKE: L I K E ;
PICTUREIS: P I C T U R E ' ' I S ;
OCCURS: O C C U R S;




mode IDENTIFICATION_MODE;
        IDWS: WHITESPACE->skip;
        DATADIVISION: NEWLINE COMMANDCOLUMNS D A T A ' ' D I V I S I O N DOT NEWLINE COMMANDCOLUMNS-> mode(DATA_MODE);
        IDPROCEDUREDIVISION: NEWLINE COMMANDCOLUMNS P R O C E D U R E ' ' D I V I S I O N DOT NEWLINE-> mode(PROCEDURE_MODE);
        IDDOT: DOT;
        IDCOLUMNS: COMMANDCOLUMNS;
        IDCOMMENT: COMMENTCOLUMNS -> skip;
        IDNEWLINE: NEWLINE;
        NAME: ~('.')+;

mode DATA_MODE;
        DATACOLUMNS: COMMANDCOLUMNS;
        DATANEWLINE: NEWLINE;
        DATALEVEL: [0-9][0-9];
        DATALIKE: LIKE;
        DATAPICTUREIS: PICTUREIS;
        DATAOCCURS: OCCURS;
        DATATIMES: TIMES;
        DATAREPRESENTATION:REPRESENTATION;
        DATAPROCEDUREDIVISION: NEWLINE COMMANDCOLUMNS P R O C E D U R E ' ' D I V I S I O N DOT NEWLINE-> mode(PROCEDURE_MODE);
        DATADOT : DOT;
        DATAWS: WHITESPACE-> skip;
        DATACOMMENT: COMMENTCOLUMNS -> skip;
        DATAINT: INT;
        DATAID: IDENTIFIER;




mode PROCEDURE_MODE;
    PRODCOMMENTS: COMMENTCOLUMNS -> skip;
    PROCEDURECOLUMNS: '           ' [ ]*;
    PARAGRAPHCOLUMNS: COMMANDCOLUMNS;
    PRODNEWLINE: NEWLINE;
    PRODWS: WHITESPACE-> skip;
    PROCSTRINGLITERAL: STRINGLITERAL;


    USING: U S I N G ;
    BY: B Y ;
    REFERENCE: R E F E R E N C E ;
    CONTENT: C O N T E N T ;
    VALUE: V A L U E ;
    ACCEPT: A C C E P T ;
    ADD: A D D ;
    TO: T O ;
    OF: O F ;
    RETURNING: R E T U R N I N G ;
    GIVING: G I V I N G ;
    ALTER: A L T E R ;
    PROCEED: P R O C E E D ;
    CALL: C A L L ;
    PRIMITIVE: P R I M I T I V E ;
    STRUCT: S T R U C T ;
    AS: A S ;
    COPY: C O P Y ;
    REPLACING: R E P L A C I N G ;
    DISPLAY: D I S P L A Y ;
    DELIMITED: D E L I M I T E D ;
    SIZE: S I Z E ;
    SPACE: S P A C E ;
    WITH: W I T H ;
    NO: N O ;
    ADVANCING: A D V A N C I N G ;
    DIVIDE: D I V I D E ;
    INTO: I N T O ;
    REMAINDER: R E M A I N D E R ;
    EVALUATE: E V A L U A T E ;
    ALSO: A L S O ;
    WHEN: W H E N ;
    OTHER: O T H E R ;
    THROUGH: T H R O U G H ;
    GOTO: G O ' ' T O ;
    IF: I F ;
    THEN: T H E N ;
    ELSE: E L S E ;
    END: E N D ;
    LOOP: L O O P ;
    VARYING: V A R Y I N G ;
    FROM: F R O M ;
    WHILE: W H I L E ;
    UNTIL: U N T I L ;
    MOVE: M O V E ;
    HIGHVALUES: H I G H MINUS V A L U E S ;
    LOWVALUES: L O W MINUS V A L U E S ;
    SPACES: S P A C E S ;
    MULTIPLY: M U L T I P L Y ;
    NEXTSENTENCE: N E X T ' ' S E N T E N C E ;
    PERFORM: P E R F O R M ;
    TIMES: T I M E S ;
    SIGNAL: S I G N A L ;
    OFF: O F F ;
    ON: O N ;
    ERROR: E R R O R ;
    STOP: S T O P ;
    SUBTRACT: S U B T R A C T ;
    TRUE: T R U E ;
    FALSE: F A L S E ;
    PROCID: IDENTIFIER;
    PROCINT: INT;
    EQ : '=' ;
    COMMA : ',' ;
    SEMI : ';' ;
    LPAREN : '(' ;
    RPAREN : ')' ;
    LCURLY : '{' ;
    GREATER: '>';
    SMALLER: '<';
    QUOTES: '"';
    NOT: '!';
    STAR: '*';
    SLASH: '/';
    PLUS: '+';
    MINUS: '-';
    RCURLY : '}' ;
    DOT:'.';






fragment A:[aA];
fragment B:[bB];
fragment C:[cC];
fragment D:[dD];
fragment E:[eE];
fragment F:[fF];
fragment G:[gG];
fragment H:[hH];
fragment I:[iI];
fragment J:[jJ];
fragment K:[kK];
fragment L:[lL];
fragment M:[mM];
fragment N:[nN];
fragment O:[oO];
fragment P:[pP];
fragment Q:[qQ];
fragment R:[rR];
fragment S:[sS];
fragment T:[tT];
fragment U:[uU];
fragment V:[vV];
fragment W:[wW];
fragment X:[xX];
fragment Y:[yY];
fragment Z:[zZ];
