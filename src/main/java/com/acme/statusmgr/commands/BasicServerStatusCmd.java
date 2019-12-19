package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.decorators.complex.ComplexBasicStatusReport;

/**
 * @version
 * @author
 *
 * Command to create and ready a ComplexBasicStatusReport to be returned/handed over to spring
 *
 * @see StatusCommand
 * @see ComplexBasicStatusReport
 */
public class BasicServerStatusCmd extends StatusCommand {

    private ComplexBasicStatusReport statusReport;

    public BasicServerStatusCmd(long id, String template, String name) {
        super(id, template, name);
    }

    @Override
    public void execute() {
        statusReport = new ComplexBasicStatusReport(id, String.format(template, name));
        statusReport.setStatusDesc(statusReport.obtainStatusDesc());
    }

    @Override
    public StatusResponse getResults() {
        return statusReport;
    }

}
