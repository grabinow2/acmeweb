package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleMemoryDetailDecorator extends ServerStatus {

    private ServerStatus baseComp;

    public SimpleMemoryDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return (baseComp.getStatusDesc() + ", and " + servermgr.ServerManager.getMemory());
    }
}
