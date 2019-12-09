package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.StatusResponse;

/**
 * @version
 * @author
 *
 * Represents a Command object that can execute operations on a web server to get status from it.
 */
public interface ExecutableWebCommand {

    /**
     * create a StatusResponse object and prepare it by running all status-getting operations
     */
    void execute();

    /**
     * @return a StatusResponse object with all of its fields set to correct values
     * @see StatusResponse
     */
    StatusResponse getResults();

}
