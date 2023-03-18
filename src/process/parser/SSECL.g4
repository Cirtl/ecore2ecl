grammar SSECL;
import LexerRulers;

description :
    config=configuration
    entity=entityState
    (def+=entityDef)*
;

configuration:
    'config' '{'
        'starter:' starter=ID SEQ
        'mono:' mono=ID SEQ
        'color:' color=ID SEQ?
    '}'
;

entityState:
    'entities'
    '('(entities+=ID(SEQ entities+=ID)*)?')'
;

entityDef:
    name=ID ('extends' extend=ID)? ('acts' acts+=ID(SEQ acts+=ID)*)? '{'
        attr=attributeDef
        (connections+=connectionDef)+
        function=functionDef
        vis=visualizationDef
    '}'
;

attributeDef:
    'attributes' '{'
        (
        names+=ID ':' types+=ID  (SEQ
        names+=ID ':' types+=ID)* SEQ?
        )?
    '}'
;

connectionDef:
    'connections' '{'
        (
        connections+=connection  (SEQ
        connections+=connection)* SEQ?
        )?
    '}'
;

connection:
    type=('to'|'from') lower=INT (range='..' (upper=INT)?)? '(' targets+=ID(SEQ targets+=ID)*? ')'
;

functionDef:
    'functions' content=BLOCK

//    'functions' '{'
////        (lines+=LINE)*
//    '}'
;

visualizationDef:
    'visualization' content=BLOCK
//    'visualization' '{'
////        (lines+=LINE)*
//    '}'
;


//LINE : ~[ \t\n\r]+;
BLOCK: '[' .*? ']';

TO: 'to';
FROM: 'from';