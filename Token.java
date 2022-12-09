public class Token {
    //Token class must contain a string for lexeme representation
    //And int for token code
    String lexeme;
    int tokenCode;
    
    Token(String lexeme, int tokenCode){
        this.lexeme = lexeme;
        this.tokenCode = tokenCode;
    }
}
