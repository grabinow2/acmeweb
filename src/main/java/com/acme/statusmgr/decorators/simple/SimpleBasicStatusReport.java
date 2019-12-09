package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleBasicStatusReport extends ServerStatus {

    public SimpleBasicStatusReport(long id, String contentHeader) {
        super(id, contentHeader);
    }

    @Override
    public String getStatusDesc() {
        return servermgr.ServerManager.getCurrentServerStatus();
    }

    @Override
    public String getContentHeader(){
        return null;
    }

    @Override
    public long getId(){
        return 0;
    }
}
