package com.example.java;

import javax.swing.*;

public class BackgroundRunning extends SwingWorker<Void, Void> {
    private String cmdLine;

    public BackgroundRunning(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    protected Void doInBackground() throws InterruptedException {
        Robust.excute(cmdLine);
        return null;
    }

    protected void done() {
        try {
            String data = CLIReader.getDateInformation();

        } catch (Exception ignore) {
            // *** ignoring exceptions is usually not a good idea. ***
        }
    }
}
