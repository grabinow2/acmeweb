package com.acme.statusmgr.proxies;

import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.commands.DiskStatusCommand;
import com.acme.statusmgr.executors.SerialExecutor;

public class QuickResponseProxy extends StatusResponseProxy {

    public QuickResponseProxy(long id, String template, String name) {
        super(id, template, name);
    }

    public StatusResponse getResults(){

        // todo logic to handle quick response

        DiskStatusCommand cmd = new DiskStatusCommand(id, template, name);
        SerialExecutor executor = new SerialExecutor(cmd);
        executor.handleImmediately();
        return cmd.getResults();
    }
}
