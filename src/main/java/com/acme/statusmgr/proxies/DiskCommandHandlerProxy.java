package com.acme.statusmgr.proxies;

import com.acme.statusmgr.commands.DiskStatusCommand;

public class DiskCommandHandlerProxy {

    private DiskStatusCommand cmd;

    public DiskCommandHandlerProxy(DiskStatusCommand cmd) {
        this.cmd = cmd;
    }

    public void runMultiThreaded() {
        //todo logic for checking that only one other thread be run at one time
    }
}
