package com.acme.statusmgr.decorators.simple;

import com.acme.statusmgr.beans.ServerStatus;

public class SimpleExtensionsDetailDecorator extends ServerStatus {

    private ServerStatus baseComp;

    public SimpleExtensionsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String obtainStatusDesc() {
        return (baseComp.obtainStatusDesc() + ", and is using these extensions" + this.serverManager.getExtensions());
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
