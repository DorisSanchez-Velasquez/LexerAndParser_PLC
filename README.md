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
| BOOL_LITERAL | Token that represents boolean values | (True|False) | 12 |
| CHAR_LITER | Token that represents character values | [A-Za-z0-9] | 13 |
| STRING_LITERAL | Token that represents string values | [A-Za-z0-9]+ | 14 |
| IF_KEY | Keyword to declare a conditional/selection statement | assume | 15 |
| LOOP_KEY | Keyword to declare a loop statement | reiterate | 16 |
| BEGIN_KEY | Keyword to begin a program | launch | 17 |
| END_KEY | Keyword to end a program | terminate | 18 |
| DECLARE_KEY | Keyword to declare a variable assignment | 19 |
| STR_DT_KEY | Keyword to declare a string datatyper | WORD | 20 |
| NATURAL_DT_KEY | Keyword to declare a natural number datatype | NUM | 21 |
| CHAR_DT_KEY | Keyword to declare a character datatype | ENTITY | 22 |
| REAL_DT_KEY | Keyword to declare a real number datatype | HALF_NUM | 23 |
| BOOL_DT_KEY | Keyword to declare a boolean datatype | BOOL | 24


