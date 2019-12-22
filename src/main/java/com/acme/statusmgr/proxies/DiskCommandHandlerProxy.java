package com.acme.statusmgr.proxies;

import com.acme.statusmgr.commands.DiskStatusCommand;
import com.acme.statusmgr.executors.SerialExecutor;

public class DiskCommandHandlerProxy {

    private DiskStatusCommand cmd;

    private static boolean isThreadRunning;

    public DiskCommandHandlerProxy(DiskStatusCommand cmd) {
        this.cmd = cmd;
    }

    public void runMultiThreaded() {

        if (isThreadRunning){
            return;
        }
        else {
            isThreadRunning = true;

            Thread thread = new Thread(() -> {
                SerialExecutor serialExecutor = new SerialExecutor(cmd);
                serialExecutor.handleImmediately();
            });

        }

    }

}
