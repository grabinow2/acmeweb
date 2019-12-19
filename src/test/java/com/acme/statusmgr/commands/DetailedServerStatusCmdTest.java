package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.executors.SerialExecutor;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DetailedServerStatusCmdTest {

    private static final String template = "Server Status requested by %s";
    ArrayList<String> details = new ArrayList<>();


    @Test
    public void getResults() {

        details.add("operations");
        details.add("memory");

        DetailedServerStatusCmd cmd = new DetailedServerStatusCmd(1, template, "Jim",
                details,"simple");
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmidiatly();

        ServerStatus results = (ServerStatus) cmd.getResults();

        assertEquals(null, results.getContentHeader());
        assertEquals( 0,results.getId());
        assertEquals("Server is up, and is operating normally, and memory is running low",
                results.getStatusDesc());

    }
}