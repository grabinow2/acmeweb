package statusmgr.beans;

import servermgr.ServerManager;
import statusmgr.BadRequestException;
import statusmgr.decorators.StatusReporterComponent;
import statusmgr.decorators.ExtensionsDetailDecorator;
import statusmgr.decorators.MemoryDetailDecorator;
import statusmgr.decorators.OperationsDetailDecorator;

import java.util.List;

/**
 * A POJO that represents Server Status and can be used to generate JSON for that status
 */
public class ServerStatus {

    private  long id;
    private String contentHeader;
    private String statusDesc = "Unknown";

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain current status of server
        ServerManager sm = new ServerManager();
        this.statusDesc = sm.getCurrentServerStatus();
    }

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     *
     * @param id                    a numeric identifier/counter of which request this
     * @param contentHeader         info about the request
     * @param details               a <code>List</code> of supported request parameters that are used to find additional
     *                              info about the server
     * @throws BadRequestException  if an element in details is not a supported request parameter
     */
    public ServerStatus(long id, String contentHeader, List<String> details) throws BadRequestException{

        this.id = id;
        this.contentHeader = contentHeader;

        StatusReporterComponent baseComponent = new ServerManager();

        for (String s : details)
        {
            if (s.equalsIgnoreCase("operations"))
                baseComponent = new OperationsDetailDecorator(baseComponent);

            else if (s.equalsIgnoreCase("memory"))
                baseComponent = new MemoryDetailDecorator(baseComponent);

            else if (s.equalsIgnoreCase("extensions"))
                baseComponent = new ExtensionsDetailDecorator(baseComponent);

            else
                throw new BadRequestException("Invalid details option: " + s);
        }

        this.statusDesc = baseComponent.getCurrentServerStatus();

    }

    public ServerStatus() {

    }

    public long getId() {
        return id;
    }

    public String getContentHeader() {

        return contentHeader;
    }


    public String getStatusDesc() {
        return statusDesc;
    }


}
