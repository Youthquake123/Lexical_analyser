# Lexical_analyser
The simple implementation of lexical analyser

## Grammar rule

1) Identifiers:
<identifier> → <letter> ︱<identifier><letter> ︱<identifier><digit>
<letter> → [a-z] [A-Z]
<digit> → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

3) Constant:
<num> → <INT> | <DOUBLE>
<INT> → <digit> <digit>*
<DOUBLE>→ <digit> <digit>*.<digit><digit>*
<digit> → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

5) Keywords:
"abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "continue", "default", "do", "double", "else", "extends", "final", "finally", "float", "for", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "while","strictfp","enum"

7) Operators:
Type 1: '+', '-', '*', '/', '=', '>', '<', '&', '|', '!'
Type 2: "!=","<=",">=","==","++","--","+=","-=","*=","/="
operators → + | - | * |…| *= | -= |…| !

9) Separators:
',', ';', '{', '}', '(', ')', '[', ']', '_',':', '.', '"','\\'
separators → , | ; |…

## Implementation 

The implementation is in Lex.java.General idea: Scan input.txt file, processing Spaces, tabs, comments and other conditions.Start with the first meaningful character, if it is a letter, continue to read the next character, the pointer is moved down until the pointer is pointing to the next is not a letter or a number, these characters into a string, if the string is a keyword, this is marked as a keyword, if not a keyword, it is marked as an identifier.If the character to which the pointer points is a number, continue reading the next character until it is not a number. If the number is read immediately after a "." ", indicating that it is a decimal, then continue reading the following decimal part.If the character the pointer points to is a separator, it is directly marked as a separatorIf the character to which the pointer points is an operator, the case of "/" is handled first, and the comment is removed. After processing the comment, according to the operator read, decide the next judgment, see DFA for details.




