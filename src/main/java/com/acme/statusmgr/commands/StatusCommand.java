package com.acme.statusmgr.commands;

public abstract class StatusCommand implements ExecutableWebCommand {

    protected long id;
    protected String template;
    protected String name;

    public StatusCommand(long id, String template, String name){
        this.id = id;
        this.template = template;
        this.name = name;
    }

}
