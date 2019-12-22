package com.acme.statusmgr.proxies;

import com.acme.statusmgr.ProcessingException;
import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.commands.DiskStatusCommand;

public class QuickResponseProxy extends StatusResponseProxy {

    public QuickResponseProxy(long id, String template, String name) {
        super(id, template, name);
    }

    public StatusResponse getResults(){

        DiskStatusCommand cmd = new DiskStatusCommand(id, template, name);

        DiskStatus instance = (DiskStatus) cmd.getResults();
        //The type-cast is because DiskStatusCommand.getResults() is obligated by the ExecutableWebCommand
        // interface to return a StatusResponse, while DiskStatus is the only one with an isStale() method

        if (instance.isStale() || instance.getStatusDesc() == null)
        {
            DiskCommandHandlerProxy proxy = new DiskCommandHandlerProxy(cmd);
            proxy.runMultiThreaded();
            throw new ProcessingException("Data is currently being processed. " +
                    "Please wait 30 seconds and refresh the page");
            //todo - maybe do other stuff besides just throw an error in case of stale data?...
        }
        else
            return cmd.getResults();
    }
}
