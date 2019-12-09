package com.acme.statusmgr.commands;

/**
 * @version
 * @author
 *
 * An abstract of a general ExecutableWebCommand.
 *
 * @see ExecutableWebCommand
 */
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
