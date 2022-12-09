import java.util.*;
import java.io.*;

public class Lexer {
    /* GLOBAL DECLARATIONS */
    //Character Classes
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int UNKNOWN = 99;
    static final int EOF = -1;

    //Variables
    List<Token> tokens;
    String fileContent;
    Character currentChar;
    int charIdx = 0;
    int charClass;
    String lexeme = "";
    int currentToken;

/* ******************************************************************************************************* */
    /* LEXER CONSTRUCTOR  */
    Lexer(String fileContent){
        new Tokens();
        this.fileContent = fileContent;
        this.tokens = null;
        getTokens();
    }

    public List<Token> tokenize()
    {
        return tokens;
    }

/* ******************************************************************************************************* */
    /* getTokens: This is a function to get the next character from the input file and find out the character class */
    public void getTokens()
    {
          while(charIdx < fileContent.length())
          {
                currentChar = fileContent.charAt(charIdx);
                charIdx++;
                if(Character.isLetter(currentChar)){
                    charClass = LETTER;
                }
                else if(Character.isDigit(currentChar)){
                    charClass = DIGIT;
                }
                else{
                    charClass = EOF;
                }

                lexeme = "";
                lexerAnalyzer();
          }
    }

/* ******************************************************************************************************* */
    /* addChar: This is the function that adds a new character to the existing lexeme */
    public void addChar()
    {
        lexeme += currentChar;
    }

/* ******************************************************************************************************* */
    /* getNonComments: This is a function to keep calling for a new character until it is a non-whitespace character and not a comment */
    public void getNonComments()
    {
        //Ignore WhiteSpace characters
        while(Character.isWhitespace(currentChar))
        {
            getTokens();
        }
        //Ignore Block Comments
        if(currentChar == '/' && fileContent.charAt(charIdx++) == '*')
        {
            charIdx += 2;
            while(currentChar != '*' && fileContent.charAt(charIdx++) != '/')
            {
                charIdx++;
                currentChar = fileContent.charAt(charIdx);
            }
            charIdx += 1;
            getTokens();
        }
    }

/* ******************************************************************************************************* */
    /* identifyKeyword: This function is to check if the current lexeme is equal to any keyword in the language and match the token code  */
    public void identifyKeyword(){
         switch(lexeme){
            case "assume":
                 currentToken = Tokens.IF_KEY.tokenCode;
                 break;
            case "reiterate":
                currentToken = Tokens.LOOP_KEY.tokenCode;
                break;
            case "WORD":
                currentToken = Tokens.STR_DT_KEY.tokenCode;
                break;
            case "ENTITY":
                currentToken = Tokens.CHAR_DT_KEY.tokenCode;
                break;
            case "NUM":
                currentToken = Tokens.NATURAL_DT_KEY.tokenCode;
                break;
            case "HALF_NUM":
                currentToken = Tokens.REAL_DT_KEY.tokenCode;
                break;
            case "BOOL":
                currentToken = Tokens.BOOL_DT_KEY.tokenCode;
                break;
            case "vars":
                currentToken = Tokens.VAR_ID.tokenCode;
                break;
            case "funcs":
                currentToken = Tokens.FUNC_ID.tokenCode;
                break;
         }
    }

/* ******************************************************************************************************* */
    /* lexerAnalyzer: This is the main function for the lexical analyzer to get the token codes for each character class until the end of the string is reached */
    public void lexerAnalyzer()
    {
        getNonComments();
        switch(charClass){
            case LETTER:
                addChar();
                getTokens();
                while(charClass == LETTER || charClass == UNKNOWN && currentChar == '_'){
                    addChar();
                    getTokens();
                }
                identifyKeyword();
                break;

        }
    }

}




/* ******************************************************************************************************* */
/* STORES ALL THE TOKEN CODES FOR THE PROGRAMMING LANGUAGE*/
class Tokens{
    //Token Codes For Data Types
    static Token REAL_LITERAL;
    static Token NATURAL_LITERAL;
    static Token BOOL_LITERAL;
    static Token CHAR_LITER;
    static Token STRING_LITERAL;

    //Token Code For Selection Statements Keywords
    static Token IF_KEY;

    //Token Code for Loop Statement Keywords
    static Token LOOP_KEY;

    //Token Codes For Variable Declaration Keywords
    static Token STR_DT_KEY;
    static Token NATURAL_DT_KEY;
    static Token CHAR_DT_KEY;
    static Token REAL_DT_KEY;
    static Token BOOL_DT_KEY;

    //Token code For Special Symbols
    static Token ADD_OP;
    static Token SUB_OP;
    static Token MULT_OP;
    static Token DIV_OP;
    static Token EXP_OP;
    static Token LEFT_PAREN;
    static Token RIGHT_PAREN;
    static Token GREAT_OP;
    static Token LESS_OP;
    static Token GREAT_EQ_OP;
    static Token LESS_EQ_OP;
    static Token EQUAL_OP;
    static Token NOT_EQUAL_OP;
    static Token UNARY_NEG_OP;
    static Token LOGICAL_NOT;
    static Token LOGICAL_AND;
    static Token LOGICAL_OR;
    static Token LEFT_BRACKET;
    static Token RIGHT_BRACKET;
    static Token PARAM_SEP;

    //Token Code For Var and Function Identifier
    static Token VAR_ID;
    static Token FUNC_ID;

    Tokens(){
        REAL_LITERAL = new Token("[0-9]+/.[0-9]+", 10);
        NATURAL_LITERAL = new Token("[0-9]+", 11);
        BOOL_LITERAL = new Token("(True|False)", 12);
        CHAR_LITER = new Token("[A-Za-z0-9]+{1}", 13);
        STRING_LITERAL = new Token("[A-Za-z0-9]+", 14);

        IF_KEY = new Token("assume", 15);
        LOOP_KEY = new Token("reiterate", 16);

        STR_DT_KEY = new Token("WORD", 20);
        NATURAL_DT_KEY = new Token("NUM", 21);
        CHAR_DT_KEY = new Token("ENTITY", 22);
        REAL_DT_KEY = new Token("HALF_NUM", 23);
        BOOL_DT_KEY = new Token("BOOL", 24);

        ADD_OP = new Token("+", 30);
        SUB_OP = new Token("-", 31);
        MULT_OP = new Token("*", 32);
        DIV_OP = new Token("/", 33);
        EXP_OP = new Token("**", 34);
        LEFT_PAREN = new Token("(", 35);
        RIGHT_PAREN = new Token(")", 36);
        GREAT_OP = new Token(">", 37);
        LESS_OP = new Token("<", 38);
        GREAT_EQ_OP = new Token(">=", 39);
        LESS_EQ_OP = new Token("<=", 40);
        EQUAL_OP = new Token("=", 41);
        NOT_EQUAL_OP = new Token("!=", 42);
        UNARY_NEG_OP = new Token("!", 43);
        LOGICAL_NOT = new Token("!!", 44);
        LOGICAL_AND = new Token("&&", 45);
        LOGICAL_OR = new Token("||", 46);
        LEFT_BRACKET = new Token("{", 47);
        RIGHT_BRACKET = new Token("}", 48);
        PARAM_SEP = new Token(";", 49);

        VAR_ID  = new Token("vars", 50);
        FUNC_ID  = new Token("funcs", 51);
    }
}
