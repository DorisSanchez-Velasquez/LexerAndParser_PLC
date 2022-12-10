# Programming Language Concepts - Lexer And Parser
Creating the Lexer, Parser, and Compiler Classes for a programming language.

## Assignment Checklist
- [ ] Develop a class/struct for Token (contains string for lexeme representation and int for token code)
- [ ] Develop a Compiler class with a method that takes a file and converts it into one input str
- [ ] Develop a Lexer Class that contains and recognizes certain tokens for literals, keywords, special symbols, and variables
- [ ] Develop a Parser Class that creates a parse tree of statements (code blocks, selection, loop, assignment, and declaration)
- [ ] Use Denotational semantics to define your selection statement
- [ ] Use Denotational Semantics to define your loop statement
- [ ] Use Denotational Semantics to define your Expr Statement
- [ ] Use Denotation Semantics to redefine your Expr Statement so it can return a Boolean Solution
- [ ] Define the attribute grammar for your assignment statment
- [ ] Choose 3 syntactically valid assignment statements to show rules failing or passing semantic rules
- [ ] Find the weakest precondition in Axiomatic Semantics

## Token Code Rules And Descriptions
These are the rules for recognizing all lexemes as their proper token and defines all the integer token codes for each token required in this language

| Token | Description | Regular Expression | Token Code |
|   --- |     ---     |       ---          |     ---    |
| REAL_LITERAL | Token that represents fractional numbers | [0-9]+.[0-9]+ | 10 |
| NATURAL_LITERAL | Token that represents whole numbers and 0 | [0-9]+ | 11 |
| BOOL_LITERAL | Token that represents boolean values | "(True|False)" | 12 |
| CHAR_LITER | Token that represents character values | [A-Za-z0-9] | 13 |
| STRING_LITERAL | Token that represents string values | [A-Za-z0-9]+ | 14 |
| IF_KEY | Keyword to declare a conditional/selection statement | assume | 15 |
| LOOP_KEY | Keyword to declare a loop statement | reiterate | 16 |
| BEGIN_KEY | Keyword to begin a program | launch | 17 |
| END_KEY | Keyword to end a program | terminate | 18 |
| DECLARE_KEY | Keyword to declare a variable assignment | declare | 19 |
| STR_DT_KEY | Keyword to declare a string datatyper | WORD | 20 |
| NATURAL_DT_KEY | Keyword to declare a natural number datatype | NUM | 21 |
| CHAR_DT_KEY | Keyword to declare a character datatype | ENTITY | 22 |
| REAL_DT_KEY | Keyword to declare a real number datatype | HALF_NUM | 23 |
| BOOL_DT_KEY | Keyword to declare a boolean datatype | BOOL | 24 |
| EQUAL_ASSIGN | Token to identify the assignment equal sign | = | 29 |
| ADD_OP | Token to identify addition operator | + | 30 |
| SUB_OP | Token to identify the subtraction operator | - | 31 |
| MULT_OP | Token to identify the multiplication operator | * | 32 |
| DIV_OP | Token to identify the division operator | / | 33 |
| EXP_OP | Token to identify the exponent operator | ** | 34 |
| LEFT_PAREN | Token to identify the left parenthesis | ( | 35 |
| RIGHT_PAREN | Token to identify the right parenthesis | ) | 36 | 
| GREAT_OP | Token to identify the greater than operator | > | 37 |
| LESS_OP | Token to identify the less than operator | < | 38 |
| GREATER_EQ_OP | Token to identify the greater than, equal to operator | >= | 39 | 
| LESS_EQ_OP | Token to identify the less than, equal to operator | <= | 40 |
| EQUAL_OP | Token to identify the equal to operator | == | 41 |
| NOT_EQUAL_OP | Token to identify the not equal to operator | != | 42 |
| UNARY_NEG_OP | Token to identify the unary negation operator | ! | 43 |
| LOGICAL_NOT | Token to identify the logial not operator | !! | 44 |
| LOGICAL_AND | Token to identify the logical and operator | && | 45 | 
| LOGICAL_OR | Token to identify the logical or operator | || | 46 | 
| LEFT_BRACKET | Token to identify the left curly bracket to group code blocks | { | 47 |
| RIGHT_BRACKET | Token to identify the right curly bracket | } | 48 |
| PARAM_SEP | Token to identify the parameter separator and end of line character | ; | 49 |
| VAR_ID| Token ot identify the variable identifier | "@[A-Za-z_]+" | 50 |
| FUNC_ID | Token to identify the function identifier | "#[A-Za-z]+" | 51 |

## Denotational Semantics and Production Rules
- Use Denotation Semantics to define your selection statement
- Use Denotational Semantics to define your loop statement
- Use Denotation Semantics to define your Expr Statement
- Use Denotational Semantics to redefine your Expr statement so it can return a Boolean solution

## Attribute Grammar And Production Rules
Define the attribute grammar for your assignment statement, make sure it follow the following rules
- String + String does concatenation
- String * Natural repeats the Natural
- Assign bool to natural is allowed
- Assign natural to bool is allowed
- Assign char to natural is allowed
- Assign natural to char is allowed
- Assign natural to real is allowed 
- No other types are allowed to be assigned to other outside of their own
- Dividing by zero is an error
- Modulo operating by zero is an error

## Valid Assignment Statements
Choose 3 syntactically valid assignment statements with at least 7 tokens to show these rules failing or passing semantic rules

- declare STRING @exprA = @exprB + @exprC;
- declare HALF_NUM @num = 98.5 + 5;
- declare ENTITY @char = "45 + 54";

## Axiomatic Semantics
Find weakest preconditions: 
- a. Weakest Precondition( {b > 3/2 } )
- b. Weakest Precondition ( 3 * x < 0 )
- c. Weakest Precondition ( a * 2 * (b-1) – 1 < 0 )
- d. Weakest Precondition ( a > 5 )


