package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom Exception to be thrown when a user makes a bad request of the server
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Throwable {

    public BadRequestException(){}

    public BadRequestException(String message){
        System.out.println(message);
    }
}
