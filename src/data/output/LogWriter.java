package data.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class LogWriter {
    
    private static final String FILE_PATH = "C:\\DataFiles\\Sys64\\BootLoader\\debug.txt";
    
    public static void initiate() throws IOException {
        File f = new File(FILE_PATH);
        
        if (!(f.exists() && f.isFile())) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
    }

    public static void writeLog(String s) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH, true);
        writer.write(s + System.lineSeparator());
        writer.close();
    }
}
