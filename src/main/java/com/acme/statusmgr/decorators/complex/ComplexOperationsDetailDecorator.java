package com.acme.statusmgr.decorators.complex;

import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * Decorator for <code>ServerStatus.obtainStatusDesc()</code> that add the details of the server's operations to
 * the status report.
 */
public class ComplexOperationsDetailDecorator extends ServerStatus {

    private final ServerStatus baseComp;

    public ComplexOperationsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String obtainStatusDesc() {
        return baseComp.obtainStatusDesc() + ", and " + this.serverManager.getIsOperatingNormally();
    }
}
