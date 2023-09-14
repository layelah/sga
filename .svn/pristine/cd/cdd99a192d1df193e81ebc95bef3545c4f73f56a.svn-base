package com.cmu.base.service;

import com.cmu.base.domaine.BaseLib;
import com.cmu.base.domaine.BaseLibCode;
import com.cmu.base.service.exception.EchecSelectException;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

public abstract class BaseLibCodeService extends BaseLibService {

    @Inject
    BaseItemService itemSrv;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SelectItem> getSelectItemsByCode(BaseLib obj) throws EchecSelectException {
        return itemSrv.getSelectItems(obj, "code", "lib");
    }

    public BaseLibCode getEntiteByCode(BaseLibCode obj, String code) throws EchecSelectException {
        return (BaseLibCode) uneEntiteFiltree(obj, "code", code);
    }

    public boolean existeCode(BaseLibCode obj) throws EchecSelectException {
        return existe(obj, "code", obj.getCode());
    }

}
