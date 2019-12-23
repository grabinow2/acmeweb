package com.acme.statusmgr.proxies;

import com.acme.statusmgr.commands.DiskStatusCommand;
import com.acme.statusmgr.executors.SerialExecutor;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A proxy to directly handle the DiskStatusCommand in an efficient and secure way.
 */
public class DiskCommandHandlerProxy {

    private DiskStatusCommand cmd;

    private static boolean isThreadRunning;

    public DiskCommandHandlerProxy(DiskStatusCommand cmd) {
        this.cmd = cmd;
    }

    /**
     * Executes the cmd in a separate thread. Will only create and start a new thread if there is not already
     * one running.
     */
    public void runMultiThreaded() {

        if (isThreadRunning){
            return;
        }
        else {
            isThreadRunning = true;

            Thread thread = new Thread(() -> {

                SerialExecutor serialExecutor = new SerialExecutor(cmd);
                serialExecutor.handleImmediately();

                isThreadRunning = false;
            });

            thread.start();

        }

    }

}
