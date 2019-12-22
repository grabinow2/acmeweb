package com.acme.statusmgr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.PROCESSING)
public class ProcessingException extends RuntimeException {

    public ProcessingException(){
        super();
    }

    public ProcessingException(String s) {
        super(s);
    }
}
