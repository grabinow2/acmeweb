package com.acme.statusmgr.decorators;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * The basic behaviour of <code>ServerStatus.getStatusDesc()</code>.
 */
public class BasicStatusReport extends ServerStatus {

    public BasicStatusReport(long id, String contentHeader) {
        super(id, contentHeader);
    }

    @Override
    public String getStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
    }
}
