package com.acme.statusmgr.executors;

import com.acme.statusmgr.commands.ExecutableWebCommand;

public class SerialExecutor {

    private ExecutableWebCommand cmd;

    public SerialExecutor(ExecutableWebCommand cmd){
        this.cmd = cmd;
    }

    public void handleImmediately() {
        cmd.execute();
    }
}
