package com.acme.statusmgr.decorators;

/**
 * @author
 * @version
 *
 * An Object that can return the status of a Server.
 */
public interface StatusReporterComponent {

    /**
     * Gets the status of the server
     *
     * @return a string containing the status of the server
     */
    public String getCurrentServerStatus();

}
