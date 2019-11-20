package statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

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
        return ServerManager.getCurrentServerStatus();
    }
}
