package com.cmu.base.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("boolConvert")  
public class BooleanConverter implements Converter {  
  
    @Override  
    public Object getAsObject(FacesContext context, UIComponent component,  String value) {  
  
        return (value != null && value.trim().equalsIgnoreCase("OUI"));   
    }  
  
    @Override  
    public String getAsString(FacesContext context, UIComponent component, Object value) {  
  
        Boolean bValue = (Boolean)value;
        
        return (bValue != null && (bValue) ? "OUI" : "NON");  
  
    }  
  
}