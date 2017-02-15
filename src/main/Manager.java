package main;

import data.input.LogCleaner;
import data.input.LogTracker;
import data.output.LogWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Manager {

    public static void run() throws IOException {
        LogWriter.initiate();
        LogTracker.run();
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy z");
        Date currentDate = new Date();
        LogWriter.writeLog(sdf.format(currentDate));
    }
}
