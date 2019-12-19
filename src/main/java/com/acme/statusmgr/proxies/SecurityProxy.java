package com.acme.statusmgr.proxies;

import com.acme.statusmgr.BadRequestException;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.commands.DiskStatusCommand;
import com.acme.statusmgr.executors.SerialExecutor;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A Proxy that checks input name for validity before running an expensive disk command
 */
public class SecurityProxy {

    private long id;
    private String template;
    private String name;

    public SecurityProxy(long id, String template, String name) {
        this.id = id;
        this.template = template;
        this.name = name;
    }

    /**
     * @return a Disk Status if name was valid
     * @throws BadRequestException if name was not valid
     *
     * @see DiskStatusCommand
     */
    public StatusResponse getResults() {

        if (name.equalsIgnoreCase("Anonymous"))
            throw new BadRequestException("Long-Running Disk Operation will not run " +
                    "unless the user identifies himself!");

        DiskStatusCommand cmd = new DiskStatusCommand(id, template, name);
        SerialExecutor executor = new SerialExecutor(cmd);
        executor.handleImmediately();
        return cmd.getResults();
    }
}
