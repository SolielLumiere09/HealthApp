//No terminales
grammar labelGrammar;

init : statements* ;

statements : tamanoPorcion_statement
           | porcionesEmpaque_statement
           | caloriasStatement
           | grasaTotal_statement
           | grasaSaturada_statement
           | grasaTrans_statement
           | carbs_statement
           | azucar_statement
           | colesterol_statement
           | sodio_statement
           | proteina_statement
           ;

tamanoPorcion_statement : TAMANODEPORCION NUMERO? OZ? NUMERO? G? ;

porcionesEmpaque_statement : EMPAQUE NUMERO
                           ;

caloriasStatement : CALORIAS NUMERO
                  ;

grasaTotal_statement : GRASA TOTAL NUMERO G?
                     ;

grasaSaturada_statement : GRASA SATURADAS NUMERO G?
                  ;

grasaTrans_statement : GRASA TRANS NUMERO? G?
                     ;

carbs_statement : CARBOHIDRATOS TOTAL? NUMERO G?
                ;

azucar_statement : AZUCARES NUMERO G?
                 ;

colesterol_statement : COLESTEROL NUMERO G?
                     ;

sodio_statement : SODIO NUMERO G?
                ;

proteina_statement : PROTEINAS NUMERO G?
                ;

NUMERO :  [0-9]+;

VOWEL :  [a|e] | [i|o] | 'u' | [A|E] | [I|O] | 'U' ;

TAMANODEPORCION : [T|t] VOWEL 'm' VOWEL 'n' VOWEL 'd' VOWEL 'l' VOWEL [P|p] VOWEL 'rc' VOWEL VOWEL 'n' ;

OZ : [oz] ;

EMPAQUE : [E|e]'mp' VOWEL 'q' VOWEL VOWEL ;

CALORIAS : [C|c] VOWEL [L|l] VOWEL [R|r] VOWEL VOWEL [S|s] ;

GRASA : [G|g] 'r' VOWEL 's' VOWEL 's'? ;

TOTAL : [T|t] VOWEL 't' VOWEL 'l' VOWEL?'s'? ;

SATURADAS : [S|s]? VOWEL 't' VOWEL 'r' VOWEL 'd' VOWEL 's'? ;

TRANS : [T|t] 'r' VOWEL 'ns' ;

CARBOHIDRATOS : [C|c] VOWEL 'rb' (VOWEL 'h' VOWEL 'dr' VOWEL 't' VOWEL 's')? ;

AZUCARES : [A|a][z|n] VOWEL 'c' VOWEL 'r' VOWEL 's' ;

COLESTEROL: [C|c] VOWEL 'l' VOWEL 'st' VOWEL 'r' VOWEL 'l' ;

SODIO : [S|s] VOWEL 'd' VOWEL VOWEL;

PROTEINAS : [P|p]'r' VOWEL 't' VOWEL VOWEL 'n' VOWEL ;

G : ([m]?)'g' ;
WS : (' ' | '\r' | '\n' | '\t' | ':' | ';' | '%' | '-') -> skip;
ANY : .+? -> skip;
