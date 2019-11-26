package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleExtensionsDetailDecorator extends ServerStatus {

    private ServerStatus baseComp;

    public SimpleExtensionsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return (baseComp.getStatusDesc() + ", and is using these extensions" + servermgr.ServerManager.getExtensions());
    }

}
