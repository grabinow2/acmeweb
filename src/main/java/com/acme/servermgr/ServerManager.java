package servermgr;

/**
 * @author
 * @version
 *
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project
 */
public class ServerManager {

    /**
     * Get the status of this server
     *
     * @return a descriptive string about the servers status
     */
    static public String getCurrentServerStatus() {
        return "Server is up";
    }

    /**
     * Find out if this server is operating normally
     *
     * @return String indicating if server is operating normally
     */
    static public String getIsOperatingNormally() {
        return ("is operating normally");
    }

    /**
     * Gets the extensions in use by the server
     *
     * @return a comma-delimited list of the extensions surrounded by '[]' as a String.
     */
    static public String getExtensions() {
        return "[Hypervisor, Kubernetes, RAID-6]";
    }

    /**
     * @return a String message on the status of the server's memory
     */
    static public String getMemory() {
        return "memory is running low";
    }

}
