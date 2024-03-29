package com.acme.statusmgr.beans;

import com.acme.statusmgr.beans.StatusResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import com.acme.Application;
import com.acme.servermgr.ServerManager;

/**
 * @author
 * @version
 *
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public abstract class ServerStatus implements StatusResponse {

    protected long id;
    protected String contentHeader;
    private String statusDesc;

    protected ServerManager serverManager;

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id            a numeric identifier/counter of which request this
     * @param contentHeader info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        serverManager = (ServerManager) Application.getApplicationContext().getBean("serverManager");
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long getId() {
        return id;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getContentHeader() {
        return contentHeader;
    }

    public abstract String obtainStatusDesc();

    @Override
    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
