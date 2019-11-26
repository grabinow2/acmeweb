package com.acme.statusmgr.decorators.complex;

import servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * The basic behaviour of <code>ServerStatus.getStatusDesc()</code>.
 */
public class ComplexBasicStatusReport extends ServerStatus {

    public ComplexBasicStatusReport(long id, String contentHeader) {
        super(id, contentHeader);
    }

    @Override
    public String getStatusDesc() {
        return ServerManager.getCurrentServerStatus();
    }
}
