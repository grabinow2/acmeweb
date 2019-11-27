package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.factories.SimpleResponseFactory;
import com.acme.statusmgr.beans.factories.StatusResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.decorators.BasicStatusReport;

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

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();

    @Autowired
    StatusResponseFactory factory;

    public StatusController(StatusResponseFactory factory) {
        this.factory = factory;
    }

    /**
     * Handles server requests with "/status" in the URL. User can pass in their name by typing it in to the URL as in
     * the above example: http://localhost:8080/server/status?name=Noach
     *
     * @param name the name of user requesting the status
     * @return a <code>ServerStatus</code> object that creates a greeting message in a browser
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public StatusResponse statusRequestHandler(@RequestParam(value = "name", defaultValue = "Anonymous") String name) {
        return new BasicStatusReport(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * Handles a "/status/detailed" request. User must pass in a comma-delimited list of any amount of supported
     * detail requests in any order.
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

        long id = counter.incrementAndGet();
        String header = String.format(template, name);

        if (levelOfDetail.equalsIgnoreCase("complex"))
            return factory.getServerStatus(id, header, details);

        else if (levelOfDetail.equalsIgnoreCase("simple")) {
            factory = new SimpleResponseFactory();
            return factory.getServerStatus(0, null, details);
        }
        else {
            throw new BadRequestException("invalid levelofdetail param was " + levelOfDetail);
        }
    }
}
