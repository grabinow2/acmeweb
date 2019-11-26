package com.acme.statusmgr.decorators.complex;

import servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * Decorator for <code>ServerStatus.getStatusDesc()</code>that add the details of the extensions in use by the server
 * to the status report.
 */
public class ComplexExtensionsDetailDecorator extends ServerStatus {

    private final ServerStatus baseComp;

    public ComplexExtensionsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return baseComp.getStatusDesc() + ", and is using these extensions - " + ServerManager.getExtensions();
    }
}