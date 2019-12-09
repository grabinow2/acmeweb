package com.acme.statusmgr.decorators.complex;

import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * Decorator for <code>ServerStatus.obtainStatusDesc()</code>that add the details of the extensions in use by the server
 * to the status report.
 */
public class ComplexExtensionsDetailDecorator extends ServerStatus {

    private final ServerStatus baseComp;

    public ComplexExtensionsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String obtainStatusDesc() {
        return baseComp.obtainStatusDesc() + ", and is using these extensions - " + this.serverManager.getExtensions();
    }
}
