import java.util.*;
import java.io.*;

public class Compiler {
    //Takes in an input file
    //Converts it to one input string
    String fileContents;
    List<Token> tokens;
    Compiler(String filepath){
        this.fileContents = getFileContents(filepath);
        Lexer lexer = new Lexer(fileContents);
        tokens = lexer.tokenize();
        
    }

    public String getFileContents(String filepath)
    {
        String fileInput = "";
        try{
            BufferedReader myReader = new BufferedReader(new FileReader(filepath));
            String line = myReader.readLine();
            while(line != null)
            {
                fileInput = fileInput + line + " ";
                line = myReader.readLine();
            }
            myReader.close();
    
        } catch(IOException error){
            System.out.println(error);
        }
        return fileInput;
    }
}
