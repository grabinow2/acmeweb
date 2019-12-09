package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.decorators.complex.ComplexBasicStatusReport;

public class BasicServerStatusCmd extends StatusCommand {

    private ComplexBasicStatusReport statusReport;

    public BasicServerStatusCmd(long id, String template, String name) {
        super(id, template, name);
    }

    @Override
    public void execute() {
        statusReport = new ComplexBasicStatusReport(id, String.format(template, name));
    }

    @Override
    public StatusResponse getResults() {
        return statusReport;
    }

}
