package com.cmu.base.service.exception;

@javax.ejb.ApplicationException(rollback = true)
public abstract class CMUServiceException extends Exception {

    private String userMessage = "<NON INITILISé>";
    
    public CMUServiceException(String message, Throwable cause) {
        super(message +" [ex:"+ cause.getMessage() +"]", cause);
        this.userMessage = message;
    }

    public CMUServiceException(String message) {
        super(message);
        this.userMessage = message;
    }    
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public String getUserMessage() {
        return userMessage;
    }
}
