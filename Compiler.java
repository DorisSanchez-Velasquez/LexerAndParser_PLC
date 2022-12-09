import java.util.*;
import java.io.*;

public class Compiler {
    //Takes in an input file
    //Converts it to one input string
    String fileContent;
    Compiler(String filepath){
        this.fileContent = getFileContents(filepath);
    }

    public String getFileContents(String filepath)
    {
        return filepath;
    }
}
