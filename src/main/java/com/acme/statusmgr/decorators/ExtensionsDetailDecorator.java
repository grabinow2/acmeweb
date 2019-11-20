package com.acme.statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * Decorator for <code>ServerStatus.getStatusDesc()</code>that add the details of the extensions in use by the server
 * to the status report.
 */
public class ExtensionsDetailDecorator extends ServerStatus {

    private final ServerStatus baseComp;

    public ExtensionsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return baseComp.getStatusDesc() + ", and is using these extensions - " + ServerManager.getExtensions();
    }
}
