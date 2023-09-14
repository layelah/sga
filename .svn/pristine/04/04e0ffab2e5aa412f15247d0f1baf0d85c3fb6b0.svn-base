package com.cmu.sigicmu.param.service;

import com.cmu.base.domaine.BaseDomaine;
import com.cmu.base.service.BaseLibCodeService;
import com.cmu.base.service.exception.EchecSelectException;
import com.cmu.sigicmu.param.domaine.TableValeurType;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TableValeurTypeService extends BaseLibCodeService {

    public List<BaseDomaine> getAllEntities(BaseDomaine obj) throws EchecSelectException {
        return tousLesEntitesAvecTri(obj, "code");
    }
 
    public boolean existeTypeCode(String sCode) throws EchecSelectException {
        return existe(new TableValeurType(), "code", sCode);
    }
}
