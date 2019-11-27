package com.acme.statusmgr.decorators.complex;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * Decorator for <code>ServerStatus.getStatusDesc()</code> that add the details of the server's operations to
 * the status report.
 */
public class ComplexOperationsDetailDecorator extends ServerStatus {

    private final ServerStatus baseComp;

    public ComplexOperationsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return baseComp.getStatusDesc() + ", and " + this.serverManager.getIsOperatingNormally();
    }
}
