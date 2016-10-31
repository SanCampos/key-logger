package main;

import data.input.LogCleaner;
import data.input.LogTracker;
import data.output.LogWriter;
import java.io.IOException;


public class Manager {

    public void run() throws InterruptedException, IOException {
        new LogTracker().run(new LogCleaner(), new LogWriter());
    }
}
