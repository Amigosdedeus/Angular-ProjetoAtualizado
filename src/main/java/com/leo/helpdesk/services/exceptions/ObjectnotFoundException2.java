package com.leo.helpdesk.services.exceptions;

public class ObjectnotFoundException2 extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectnotFoundException2(String message, Throwable cause){
       super(message,cause);
    }

    public ObjectnotFoundException2(String message){
        super(message);
    }

    


}
