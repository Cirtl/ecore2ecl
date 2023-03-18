lexer grammar LexerRulers;

ID: [a-zA-Z_][a-zA-Z_0-9]*;
INT: [0-9]+;
SEQ: ',';

WS : [ \t\r\n]+ -> skip;
LINE_COMMENT : '//' .*? '\n' -> skip;
MULT_COMMENT : '/*' .*? '*/' -> skip;