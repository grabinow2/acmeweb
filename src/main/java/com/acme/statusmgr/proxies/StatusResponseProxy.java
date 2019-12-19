package com.acme.statusmgr.proxies;

public abstract class StatusResponseProxy {

    protected final long id;
    protected final String template;
    protected final String name;

    public StatusResponseProxy(long id, String template, String name) {
        this.id = id;
        this.template = template;
        this.name = name;
    }

}
