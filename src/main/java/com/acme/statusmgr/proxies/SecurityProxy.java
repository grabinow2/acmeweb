package com.acme.statusmgr.proxies;

import com.acme.statusmgr.BadRequestException;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.commands.DiskStatusCommand;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A Proxy that checks input name for validity before running an expensive disk command
 */
public class SecurityProxy extends StatusResponseProxy {

    public SecurityProxy(long id, String template, String name) {

        super(id, template, name);

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

        QuickResponseProxy quickResponseProxy = new QuickResponseProxy(id, template, name);

        return quickResponseProxy.getResults();
    }
}
