package statusmgr.beans;

/**
 * @author
 * @version
 *
 * An object representing a response to a sever status request.
 */
public interface StatusResponse {

    /**
     * Gets a numeric identifier/counter of which request this is.
     * @return a <code>long</code> id number
     */
    long getId();

    /**
     * Gets info about the request (for example, name of requester)
     * @return a String containing the content header
     */
    String getContentHeader();

    /**
     * Gets details about the status of the server.
     * @return a String containing the details.
     */
    String getStatusDesc();
}
