package com.cmu.base.service.exception;

public class EchecGetRefenceException extends CMUServiceException {

    public EchecGetRefenceException(Throwable cause) {
        super("Une erreur est survenue lors de la récupération de la référence", cause);
    }
    
    public EchecGetRefenceException(String message) {
        super(message);
    }
 }