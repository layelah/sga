package com.cmu.base.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.domaine.BaseLib;
import com.cmu.base.service.exception.EchecSelectException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

public abstract class BaseLibService extends BaseService {
    
    @EJB
    BaseItemService itemSrv;
    
    public boolean existeLib(BaseLib obj) throws EchecSelectException {
        return existe(obj, "lib", obj.getLib());
    }
    
    public boolean existeLib2(BaseLib obj, String refName, Integer refId) throws EchecSelectException {
        return existe(obj, "lib", obj.getLib(), refName, refId);
    }
    
    public List<BaseDomaine> getAllEntities(BaseDomaine obj) throws EchecSelectException {
        return tousLesEntitesAvecTri(obj, "lib");
    }
        
    public List<SelectItem> getSelectItemsWithId(BaseLib obj) throws EchecSelectException {
        return itemSrv.getSelectItems(obj, "id", "lib");
    }

    public List<SelectItem> getSelectItems(BaseLib obj) throws EchecSelectException {
        return itemSrv.getSelectItemsWithObject(obj, "lib");
    }    
}
