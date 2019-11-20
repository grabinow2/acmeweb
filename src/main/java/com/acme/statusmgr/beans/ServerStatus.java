package statusmgr.beans;

import statusmgr.BadRequestException;

import java.util.List;

/**
 * @author
 * @version
 *
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public abstract class ServerStatus implements StatusResponse {

    protected long id;
    protected String contentHeader;
    public String statusDesc;

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
    }

    public long getId() {
        return id;
    }

    public String getContentHeader() {
        return contentHeader;
    }

    public abstract String getStatusDesc();


}
