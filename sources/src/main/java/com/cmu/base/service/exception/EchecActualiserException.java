/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.base.service.exception;

/**
 *
 * @author bsow
 */
public class EchecActualiserException extends CMUServiceException {

    public EchecActualiserException(Throwable cause) {
        super("Une erreur est survenue lors de l'actualisation", cause);
    }
 }