/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmu.base.service.exception;

/**
 *
 * @author bsow
 */
public class EchecNettoyerException extends CMUServiceException {

    public EchecNettoyerException(Throwable cause) {
        super("Une erreur est survenue lors du nettoyage", cause);
    }
 }
