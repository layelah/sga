package com.cmu.base.bean;

import com.cmu.util.CheckType;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("heureConvert")  
public class HeureConverter implements Converter {  
  
    @Override  
    public Object getAsObject(FacesContext context, UIComponent component,  String value) {  
        java.util.Date dateValue = null;
                
        if (CheckType.estVide(value)) {
            return null;
        } else if (CheckType.estHeure(value)) {
            dateValue = CheckType.getHeure(value);
        } else {
            FacesMessage msg = new FacesMessage("Erreur de conversion vers une heure");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
        }
        
        return dateValue;  
    }  
  
    @Override  
    public String getAsString(FacesContext context, UIComponent component, Object value) {  
  
        java.util.Date dateValue = null;
        String sHH, sMM, sResult = null;
        try {
            dateValue = (java.util.Date)value;
            sHH = CheckType.getHH(dateValue);
            sMM = CheckType.getMM(dateValue);
            sResult = sHH +":"+ sMM;
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Erreur de conversion depuis une heure");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
        }
        return sResult;
    }  
}