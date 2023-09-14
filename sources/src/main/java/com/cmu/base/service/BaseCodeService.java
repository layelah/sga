package com.cmu.base.service;

import com.cmu.base.domaine.BaseCode;
import com.cmu.base.service.exception.EchecSelectException;

public abstract class BaseCodeService extends BaseService {
    
     public boolean existeCode(BaseCode obj) throws EchecSelectException {
        return existe(obj, "code", obj.getCode());
    }
}
