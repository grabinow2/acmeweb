package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.executors.SerialExecutor;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiskStatusCommandTest {

    private static final String template = "Server Status requested by %s";

    @Test
    public void getResults() {

        DiskStatusCommand cmd = new DiskStatusCommand(1, template, "Jim");
        SerialExecutor executor = new SerialExecutor(cmd);

        executor.handleImmidiatly();
        DiskStatus results = (DiskStatus) cmd.getResults();

        assertEquals(1, results.getId());
        assertEquals(String.format(template, "Jim"), results.getContentHeader());
        assertTrue(results.getStatusDesc().contains("Total Files Listed:"));

    }
}