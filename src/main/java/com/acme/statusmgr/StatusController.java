package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.factories.SimpleResponseFactory;
import com.acme.statusmgr.beans.factories.StatusResponseFactory;
import com.acme.statusmgr.commands.BasicServerStatusCmd;
import com.acme.statusmgr.commands.DetailedServerStatusCmd;
import com.acme.statusmgr.commands.DiskStatusCommand;
import com.acme.statusmgr.executors.SerialExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.acme.statusmgr.beans.StatusResponse;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 * All start with /server
 * /status  will give back status of server
 * a param of 'name' specifies a requestor name to appear in response
 * <p>
 * Examples:
 * http://localhost:8080/server/status
 * <p>
 * http://localhost:8080/server/status?name=Noach
 */

@RestController
@RequestMapping("/server")
public class StatusController {

    private static final String template = "Server Status requested by %s";
    private final AtomicLong counter = new AtomicLong();


    /**
     * Handles server requests with "/status" in the URL. User can pass in their name by typing it in to the URL as in
     * the above example: http://localhost:8080/server/status?name=Noach
     *
     * @param name the name of user requesting the status
     * @return a <code>ServerStatus</code> object that creates a greeting message in a browser
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public StatusResponse statusRequestHandler(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {

        BasicServerStatusCmd cmd = new BasicServerStatusCmd(counter.incrementAndGet(), template, name);
        SerialExecutor exc = new SerialExecutor(cmd);
        exc.handleImmidiatly();
        return cmd.getResults();

    }

    /**
     * Handles a "/status/detailed" request. User must pass in a comma-delimited list of any amount of supported
     * detail requests in any order.
     * <p>
     * <p>
     * Currently the list of supported detail requests includes:
     * <ul>
     * <li>operations - for a confirmation that the server is operational</li>
     * <li>memory - for a status update on the memory of the server</li>
     * <li>extensions - for a list of extensions that the server is using</li>
     * </ul>
     * <p>
     * Examples of valid URLs:
     * http://localhost:8080/server/status/detailed?details=operations
     * http://localhost:8080/server/status/detailed?details=operations,extensions,memory
     * </p>
     *
     * <p>
     * Additionally, the user can input their name into the URL to get similar feedback as "/status" requests.
     * Example of both in use:
     * http://localhost:8080/server/status/detailed?details=operations,extensions,memory&name=Noach
     * </p>
     *
     * <p>
     * Additionally, the user can adjust level of detail that he will see by setting the levelofdetail parameter
     * to either complex or simple. Setting it to simple will remove the header and id from the JSON string.
     * Its default value is complex to include all the JSON properties.
     * <p>
     * Example:
     * http://localhost:8080/server/status/detailed?details=operations,extensions,memory&levelofdetail=simple
     * </p>
     *
     * @param details       a comma-delimited list of strings parsed from the URL by Spring.
     * @param name          the name of user requesting status.
     * @param levelOfDetail level of detail that the user wants to see.
     * @return a <code>ServerStatus</code> object that will publish a detailed status update in the browser.
     * @throws BadRequestException
     */
    @RequestMapping(value = "/status/detailed", method = RequestMethod.GET)
    public StatusResponse detailedRequestHandler(
            @RequestParam(value = "details") List<String> details,
            @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name,
            @RequestParam(value = "levelofdetail", required = false, defaultValue = "complex") String levelOfDetail)
            throws BadRequestException {
        if (details == null) //here for failure atomicity
            throw new BadRequestException(
                    "\"Required List parameter 'details' is not present\",\"path\":\"/server/status/detailed\"");

        DetailedServerStatusCmd cmd = new DetailedServerStatusCmd(counter.incrementAndGet(), template, name,
                details, levelOfDetail);

        SerialExecutor executor = new SerialExecutor(cmd);

        executor.handleImmidiatly();

        return cmd.getResults();

    }

    /**
     * Handles a "/disk/status" request to see the status of the disk of the server.
     *
     * User can pass their name in as in all other status requests.
     * Example URL: http://localhost:8080/server/disk/status?name=Billy
     *
     * @param name  of user requesting status
     * @return      a DiskStatus POJO that Spring will create JSON from
     *
     * @see DiskStatus
     */
    @RequestMapping(value = "/disk/status", method = RequestMethod.GET)
    public StatusResponse diskStatusRequestHandler(
            @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name )
    {
        DiskStatusCommand cmd = new DiskStatusCommand(counter.incrementAndGet(), template, name);
        SerialExecutor executor = new SerialExecutor(cmd);
        executor.handleImmidiatly();
        return cmd.getResults();
    }

}
