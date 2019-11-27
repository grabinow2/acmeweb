package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleOperationsDetailDecorator extends ServerStatus {

    private ServerStatus baseComp;

    public SimpleOperationsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return (baseComp.getStatusDesc() + ", and " + servermgr.ServerManager.getIsOperatingNormally());
    }
}
