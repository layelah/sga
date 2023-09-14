package com.cmu.base.service.exception;

public class EchecDetacherException extends CMUServiceException {

    public EchecDetacherException(Throwable cause) {
        super("Une erreur est survenue lors du détachement", cause);
    }
 }
