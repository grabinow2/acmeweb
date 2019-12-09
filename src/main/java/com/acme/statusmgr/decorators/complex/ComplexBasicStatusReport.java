package com.acme.statusmgr.decorators.complex;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * The basic behaviour of <code>ServerStatus.obtainStatusDesc()</code>.
 */
public class ComplexBasicStatusReport extends ServerStatus {

    public ComplexBasicStatusReport(long id, String contentHeader) {
        super(id, contentHeader);
    }

    @Override
    public String obtainStatusDesc() {
        return this.serverManager.getCurrentServerStatus();
    }
}
