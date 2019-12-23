package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom exception to be thrown if a service is unavailable due to server being overloaded or down for maintenance
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(){
        super();
    }

    public ServiceUnavailableException(String s) {
        super(s);
    }
}
