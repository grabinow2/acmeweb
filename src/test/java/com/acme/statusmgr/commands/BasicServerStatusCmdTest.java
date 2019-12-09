package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.executors.SerialExecutor;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

public class BasicServerStatusCmdTest {

    private static final String template = "Server Status requested by %s";
      @Test
    public void getResults() {

        BasicServerStatusCmd cmd = new BasicServerStatusCmd(1, template, "Jim");
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmidiatly();

        ServerStatus results = (ServerStatus) cmd.getResults();

        assertEquals(String.format(template, "Jim"), results.getContentHeader());
        assertEquals( 1,results.getId());
        assertEquals("Server is up", results.getStatusDesc());

    }
}