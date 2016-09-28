package main;

import data.input.LogCleaner;
import data.input.LogTracker;
import data.output.LogWriter;
import java.io.IOException;


public class Manager {

    private LogTracker tracker;
    private LogCleaner cleaner;
    private LogWriter writer;

    public Manager() throws IOException {
        this.tracker = new LogTracker();
        this.cleaner = new LogCleaner();
        this.writer = new LogWriter();
    }
    
    public void run() throws InterruptedException {
        tracker.run(cleaner, writer);
    }
}
