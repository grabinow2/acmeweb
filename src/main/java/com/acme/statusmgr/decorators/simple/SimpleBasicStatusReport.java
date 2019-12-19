package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleBasicStatusReport extends ServerStatus {

    public SimpleBasicStatusReport(long id, String contentHeader) {
        super(id, contentHeader);
    }

    @Override
    public String obtainStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
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
