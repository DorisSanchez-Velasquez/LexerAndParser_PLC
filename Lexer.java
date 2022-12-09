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
            case "reiterate"
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
    Token REAL_LITERAL;
    Token NATURAL_LITERAL;
    Token BOOL_LITERAL;
    Token CHAR_LITER;
    Token STRING_LITERAL;

    //Token Code For Selection Statements Keywords
    Token IF_KEY;

    //Token Code for Loop Statement Keywords
    Token LOOP_KEY;

    //Token Codes For Variable Declaration Keywords
    Token STR_DT_KEY;
    Token NATURAL_DT_KEY;
    Token CHAR_DT_KEY;
    Token REAL_DT_KEY;
    Token BOOL_DT_KEY;

    //Token code For Special Symbols
    Token ADD_OP;
    Token SUB_OP;
    Token MULT_OP;
    Token DIV_OP;
    Token EXP_OP;
    Token LEFT_PAREN;
    Token RIGHT_PAREN;
    Token GREAT_OP;
    Token LESS_OP;
    Token GREAT_EQ_OP;
    Token LESS_EQ_OP;
    Token EQUAL_OP;
    Token NOT_EQUAL_OP;
    Token UNARY_NEG_OP;
    Token LOGICAL_NOT;
    Token LOGICAL_AND;
    Token LOGICAL_OR;
    Token LEFT_BRACKET;
    Token RIGHT_BRACKET;
    Token PARAM_SEP;

    //Token Code For Var and Function Identifier
    Token VAR_ID;
    Token FUNC_ID;

    Tokens(){
        this.REAL_LITERAL = new Token("[0-9]+/.[0-9]+", 10);
        this.NATURAL_LITERAL = new Token("[0-9]+", 11);
        this.BOOL_LITERAL = new Token("(True|False)", 12);
        this.CHAR_LITER = new Token("[A-Za-z0-9]+{1}", 13);
        this.STRING_LITERAL = new Token("[A-Za-z0-9]+", 14);

        this.IF_KEY = new Token("assume", 15);
        this.LOOP_KEY = new Token("reiterate", 16);

        this.STR_DT_KEY = new Token("WORD", 20);
        this.NATURAL_DT_KEY = new Token("NUM", 21);
        this.CHAR_DT_KEY = new Token("ENTITY", 22);
        this.REAL_DT_KEY = new Token("HALF_NUM", 23);
        this.BOOL_DT_KEY = new Token("BOOL", 24);

        this.ADD_OP = new Token("+", 30);
        this.SUB_OP = new Token("-", 31);
        this.MULT_OP = new Token("*", 32);
        this.DIV_OP = new Token("/", 33);
        this.EXP_OP = new Token("**", 34);
        this.LEFT_PAREN = new Token("(", 35);
        this.RIGHT_PAREN = new Token(")", 36);
        this.GREAT_OP = new Token(">", 37);
        this.LESS_OP = new Token("<", 38);
        this.GREAT_EQ_OP = new Token(">=", 39);
        this.LESS_EQ_OP = new Token("<=", 40);
        this.EQUAL_OP = new Token("=", 41);
        this.NOT_EQUAL_OP = new Token("!=", 42);
        this.UNARY_NEG_OP = new Token("!", 43);
        this.LOGICAL_NOT = new Token("!!", 44);
        this.LOGICAL_AND = new Token("&&", 45);
        this.LOGICAL_OR = new Token("||", 46);
        this.LEFT_BRACKET = new Token("{", 47);
        this.RIGHT_BRACKET = new Token("}", 48);
        this.PARAM_SEP = new Token(";", 49);

        this.VAR_ID  = new Token("vars", 50);
        this.FUNC_ID  = new Token("funcs", 51);
    }
}
