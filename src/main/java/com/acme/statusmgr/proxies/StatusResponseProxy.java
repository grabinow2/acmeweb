package com.acme.statusmgr.proxies;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A simple abstract base to extract boilerplate for the proxies that directly handle id, template, and name fields.
 */
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
