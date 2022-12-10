import java.util.*;

public class Lexer {
    /* GLOBAL DECLARATIONS */
    //Character Classes
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int UNKNOWN = 99;
    static final int EOF = -1;

    //Variables
    List<Token> tokensList;
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
        this.tokensList = new ArrayList<Token>();
        tokenDriver();
    }

    public List<Token> tokenize()
    {
        for(int x = 0; x < tokensList.size(); x++)
        {
            System.out.print(tokensList.get(x).tokenCode + ", ");
        }
        return tokensList;
    }

    public void tokenDriver()
    {
        getTokens();
        while(charIdx < fileContent.length() && currentToken != EOF)
        {
            lexerAnalyzer();
            lexeme = "";
        }
    }

/* ******************************************************************************************************* */
    /* getTokens: This is a function to get the next character from the input file and find out the character class */
    public void getTokens()
    {
        if(charIdx < fileContent.length())
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
            charClass = UNKNOWN;
        }
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
            while(currentChar != '*' && fileContent.charAt(charIdx++) != '/' && charIdx++ < fileContent.length())
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
            case "launch":
                currentToken = Tokens.BEGIN_KEY.tokenCode;
                break;
            case "terminate":
                currentToken = Tokens.END_KEY.tokenCode;
                break;
            case "declare":
                currentToken = Tokens.DECLARE_KEY.tokenCode;
                break;
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
            default:
                if(lexeme.matches(Tokens.BOOL_LITERAL.lexeme))
                {
                    currentToken = Tokens.BOOL_LITERAL.tokenCode;
                    break;
                }
                currentToken = EOF;
                break;
         }
    }
/* ******************************************************************************************************* */
    /* identifyVariableType: This is a function to match the regex of the lexeme to a natural or real number */   
    public void identifyVariableType()
    {
        if(lexeme.matches(Tokens.REAL_LITERAL.lexeme))
        {
            currentToken = Tokens.REAL_LITERAL.tokenCode;
        }
        else if(lexeme.matches(Tokens.NATURAL_LITERAL.lexeme))
        {
            currentToken = Tokens.NATURAL_LITERAL.tokenCode;
        }
        else if(lexeme.matches('"' + Tokens.CHAR_LITER.lexeme + '"'))
        {
            currentToken = Tokens.CHAR_LITER.tokenCode;
        }
        else if(lexeme.matches('"' + Tokens.STRING_LITERAL.lexeme + '"'))
        {
            currentToken = Tokens.STRING_LITERAL.tokenCode;
        }
        else if(lexeme.matches(Tokens.VAR_ID.lexeme))
        {
            currentToken = Tokens.VAR_ID.tokenCode;
        }
        else if(lexeme.matches(Tokens.FUNC_ID.lexeme))
        {
            currentToken = Tokens.FUNC_ID.tokenCode;
        }
        else{
            currentToken = EOF;
        }
    }

/* ******************************************************************************************************* */
    /* identifyUnknowns: This is a function to recognize and find token codes for all special symbols */
    public void identifyUnknowns()
    {
        switch(lexeme){
            case "+":
                currentToken = Tokens.ADD_OP.tokenCode;
                break;
            case "-":
                currentToken = Tokens.SUB_OP.tokenCode;
                break;
            case "*":
                if(currentChar == '*')
                {
                    addChar();
                    getTokens();
                    currentToken = Tokens.EXP_OP.tokenCode;
                    break;
                }
                currentToken = Tokens.MULT_OP.tokenCode;
                break;
            case "/":
                currentToken = Tokens.DIV_OP.tokenCode;
                break;
            case "(":
                currentToken = Tokens.LEFT_PAREN.tokenCode;
                break;
            case ")":
                currentToken = Tokens.RIGHT_PAREN.tokenCode;
                break;
            case ">":
                if(currentChar == '=')
                {
                    addChar();
                    getTokens();
                    currentToken = Tokens.GREAT_EQ_OP.tokenCode;
                    break;
                }
                currentToken = Tokens.GREAT_OP.tokenCode;
                break;
            case "<":
                if(currentChar == '=')
                {
                    addChar();
                    getTokens();
                    currentToken = Tokens.LESS_EQ_OP.tokenCode;
                    break;
                }
                currentToken = Tokens.LESS_OP.tokenCode;
                break;
            case "=":
                if(currentChar == '=')
                {
                    addChar();
                    getTokens();
                    currentToken = Tokens.EQUAL_OP.tokenCode;
                    break;
                }
                currentToken = Tokens.EQUAL_ASSIGN.tokenCode;
                break;
            case "!":
                if(currentChar == '=')
                {
                    addChar();
                    getTokens();
                    currentToken = Tokens.NOT_EQUAL_OP.tokenCode;
                    break;
                }
                else if(currentChar == '!')
                {
                    addChar();
                    getTokens();
                    currentToken = Tokens.LOGICAL_NOT.tokenCode;
                    break;
                }
                currentToken = Tokens.UNARY_NEG_OP.tokenCode;
                break;
            case "&":
                currentToken = Tokens.LOGICAL_AND.tokenCode;
                break;
            case "|":
                currentToken = Tokens.LOGICAL_OR.tokenCode;
                break;
            case "{":
                currentToken = Tokens.LEFT_BRACKET.tokenCode;
                break;
            case "}":
                currentToken = Tokens.RIGHT_BRACKET.tokenCode;
                break;
            case ";":
                currentToken = Tokens.PARAM_SEP.tokenCode;
                break;
            default:
                currentToken = EOF;
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
            case DIGIT:
                addChar();
                getTokens();
                while(charClass == DIGIT || charClass == UNKNOWN && currentChar == '.')
                {
                    addChar();
                    getTokens();
                }
                identifyVariableType();
                break;
            case UNKNOWN:
                if(currentChar == '.' && Character.isDigit(fileContent.charAt(charIdx++)))
                {
                    addChar();
                    getTokens();
                }
                else if(currentChar == '"')
                {
                    addChar();
                    getTokens();
                    while(charClass == LETTER || charClass == UNKNOWN && currentChar == '"')
                    {
                        addChar();
                        getTokens();
                    }
                    identifyVariableType();
                    break;
                }
                else if(currentChar == '@')
                {
                    addChar();
                    getTokens();
                    while(charClass == LETTER || charClass == UNKNOWN && currentChar == '_')
                    {
                        addChar();
                        getTokens();
                    }
                    identifyVariableType();
                    break;
                }
                else if(currentChar == '#')
                {
                    addChar();
                    getTokens();
                    while(charClass == LETTER)
                    {
                        addChar();
                        getTokens();
                    }
                    identifyVariableType();
                    break;
                }
                else{
                    addChar();
                    getTokens();
                    identifyUnknowns();
                    break;
                }
            case EOF:
                currentToken = EOF;
                lexeme = "";
                break;
        }
        //System.out.println("Lexeme: " + lexeme + ", code: " + currentToken);
        tokensList.add(new Token(lexeme, currentToken));

        lexeme = "";
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

    //Token Code For Declaraction Statement Keywords
    static Token BEGIN_KEY;
    static Token END_KEY;
    static Token DECLARE_KEY;

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
    static Token EQUAL_ASSIGN;

    //Token Code For Var and Function Identifier
    static Token VAR_ID;
    static Token FUNC_ID;

    Tokens(){
        REAL_LITERAL = new Token("[0-9]+.[0-9]+", 10);
        NATURAL_LITERAL = new Token("[0-9]+", 11);
        BOOL_LITERAL = new Token("(True|False)", 12);
        CHAR_LITER = new Token("[A-Za-z0-9]", 13);
        STRING_LITERAL = new Token("[A-Za-z0-9]+", 14);

        IF_KEY = new Token("assume", 15);
        LOOP_KEY = new Token("reiterate", 16);
        BEGIN_KEY = new Token("launch", 17);
        END_KEY = new Token("terminate", 18);
        DECLARE_KEY = new Token("declare", 19);

        STR_DT_KEY = new Token("WORD", 20);
        NATURAL_DT_KEY = new Token("NUM", 21);
        CHAR_DT_KEY = new Token("ENTITY", 22);
        REAL_DT_KEY = new Token("HALF_NUM", 23);
        BOOL_DT_KEY = new Token("BOOL", 24);

        EQUAL_ASSIGN = new Token("=", 29);
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
        EQUAL_OP = new Token("==", 41);
        NOT_EQUAL_OP = new Token("!=", 42);
        UNARY_NEG_OP = new Token("!", 43);
        LOGICAL_NOT = new Token("!!", 44);
        LOGICAL_AND = new Token("&&", 45);
        LOGICAL_OR = new Token("||", 46);
        LEFT_BRACKET = new Token("{", 47);
        RIGHT_BRACKET = new Token("}", 48);
        PARAM_SEP = new Token(";", 49);

        VAR_ID  = new Token("@[A-Za-z_]+", 50);
        FUNC_ID  = new Token("#[A-Za-z]+", 51);
    }
}
