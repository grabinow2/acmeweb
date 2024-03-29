package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleMemoryDetailDecorator extends ServerStatus {

    private ServerStatus baseComp;

    public SimpleMemoryDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String obtainStatusDesc() {
        return (baseComp.obtainStatusDesc() + ", and " + this.serverManager.getMemory());
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
