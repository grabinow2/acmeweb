package statusmgr.decorators;

import servermgr.ServerManager;
import statusmgr.beans.ServerStatus;

/**
 * @author
 * @version
 *
 * Decorator for <code>ServerStatus.getStatusDesc()</code> that add the details of the server's operations to
 * the status report.
 */
public class OperationsDetailDecorator extends ServerStatus {

    private final ServerStatus baseComp;

    public OperationsDetailDecorator(long id, String contentHeader, ServerStatus baseComp) {
        super(id, contentHeader);
        this.baseComp = baseComp;
    }

    @Override
    public String getStatusDesc() {
        return baseComp.getStatusDesc() + ", and " + ServerManager.getIsOperatingNormally();
    }
}
