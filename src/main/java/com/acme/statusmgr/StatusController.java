package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.servermgr.ServerManager;
import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.decorators.ExtensionsDetailDecorator;
import com.acme.statusmgr.decorators.MemoryDetailDecorator;
import com.acme.statusmgr.decorators.OperationsDetailDecorator;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * <p>
     * Additionally, the user can input their name into the URL to get similar feedback as "/status" requests.
     * Example of both in use:
     * http://localhost:8080/server/status/detailed?details=operations,extensions,memory&name=Noach
     *
     * @param details a comma-delimited list of strings parsed from the URL by Spring.
     * @param name    the name of user requesting status.
     * @return a <code>ServerStatus</code> object that will publish a detailed status update in the browser.
     * @throws BadRequestException
     */
    @RequestMapping(value = "/status/detailed", method = RequestMethod.GET)
    public StatusResponse detailedRequestHandler(
            @RequestParam(value = "details") List<String> details,
            @RequestParam(value = "name", required = false, defaultValue = "Anonymous") String name)
            throws BadRequestException {
        if (details == null) //for failure atomicity
            throw new BadRequestException(
                    "\"Required List parameter 'details' is not present\",\"path\":\"/server/status/detailed\"");

        long longid = counter.incrementAndGet();
        String header = String.format(template, name);

        return new ServerStatus(longid, header) {
            @Override
            public String getStatusDesc() {
                ServerStatus baseComp = new BasicStatusReport(longid, header);

                for (String s : details) {
                    if (s.equalsIgnoreCase("operations"))
                        baseComp = new OperationsDetailDecorator(longid, header, baseComp);

                    else if (s.equalsIgnoreCase("memory"))
                        baseComp = new MemoryDetailDecorator(longid, header, baseComp);

                    else if (s.equalsIgnoreCase("extensions"))
                        baseComp = new ExtensionsDetailDecorator(longid, header, baseComp);

                    else
                        throw new BadRequestException("Invalid details option: " + s);

                }

                this.statusDesc = baseComp.getStatusDesc();

                return statusDesc;
            }
        };

    }

}
