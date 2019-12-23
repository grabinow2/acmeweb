package com.acme.statusmgr.proxies;

import com.acme.statusmgr.ServiceUnavailableException;
import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.commands.DiskStatusCommand;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A proxy to respond to disk status requests quickly. If DiskStatus's data is not stale, it will return it immediately;
 * else it will throw an error with a message for the user to be patient.
 *
 * @see DiskStatus and its <code>isStale()</code> method
 */
public class QuickResponseProxy extends StatusResponseProxy {


    public QuickResponseProxy(long id, String template, String name) {
        super(id, template, name);
    }

    /**
     * This will return a response to the user very quickly, with either the status response if it's not stale
     * or an error if it is.
     * @return a StatusResponse in the form of a DiskStatus
     * @throws ServiceUnavailableException if data was null or stale.
     */
    public StatusResponse getResults(){

        DiskStatusCommand cmd = new DiskStatusCommand(id, template, name);

        DiskStatus instance = (DiskStatus) cmd.getResults();
        //The type-cast is because DiskStatusCommand.getResults() is obligated by the ExecutableWebCommand
        // interface to return a StatusResponse, while DiskStatus is the only one with an isStale() method

        if (instance.isStale() || instance.getStatusDesc() == null)
        {
            DiskCommandHandlerProxy proxy = new DiskCommandHandlerProxy(cmd);
            proxy.runMultiThreaded();
            throw new ServiceUnavailableException("Data is currently being processed. " +
                    "Please wait 30 seconds and refresh the page");
            //It would have been nice if I could throw an HTTP Code 102, but Spring handles that by doing nothing
            //todo - maybe do other stuff besides just throw an error in case of stale data?...
        }
        else
            return cmd.getResults();
    }
}
