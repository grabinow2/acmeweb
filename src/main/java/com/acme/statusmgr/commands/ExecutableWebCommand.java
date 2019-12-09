package com.acme.statusmgr.commands;

import com.acme.statusmgr.beans.StatusResponse;

public interface ExecutableWebCommand {

    void execute();

    StatusResponse getResults();

}
