package main;

import data.input.LogCleaner;
import data.input.LogTracker;
import data.output.LogWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Manager {
    
    private LogWriter writer;

    public Manager() throws IOException {
        this.writer = new LogWriter();
    }

    public void run() throws IOException {
        new LogTracker().run(new LogCleaner(), writer);
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy z");
        Date currentDate = new Date();
        writer.writeLog(sdf.format(currentDate));
    }
}
