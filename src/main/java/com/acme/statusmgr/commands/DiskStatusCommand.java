package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.StatusResponse;

public class DiskStatusCommand extends StatusCommand {

    private DiskStatus response;

    public DiskStatusCommand(long id, String template, String name) {
        super(id, template, name);
    }

    @Override
    public void execute() {
        response = new DiskStatus(id, String.format(template, name));

        response.setStatusDesc(response.obtainStatusDesc()); // obtainStatusDesc is a long-running operation

    }

    @Override
    public StatusResponse getResults() {
        return response;
    }
}
