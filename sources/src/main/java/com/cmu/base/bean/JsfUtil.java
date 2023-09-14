package com.cmu.base.bean;

import com.cmu.base.service.exception.CMUServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsfUtil {
    
    public static void finDeSession() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
        HttpServletRequest re = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
          
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
            System.out.println("com.cmu.base.bean.JsfUtil.finDeSession()");
        
    }
    
    public static String getIpAddress() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader( "X-FORWARDED-FOR" );
        if (ipAddress == null ) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }
    
    public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }
    
    // INFO
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    // WARN
    public static void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    // FATAL
          
    public static void addErrorMessages(List<String> messages) {
        messages.stream().forEach((message) -> {
            addErrorMessage(message);
        });
    }

    // ERREUR
   
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    
    public static void addErrorMessage(String clientId, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
        FacesContext.getCurrentInstance().addMessage (clientId, facesMsg);
    }

    public static void addErrorMessage(String clientId, String summary, String detail) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
        FacesContext.getCurrentInstance().addMessage (clientId, facesMsg);
    }

    public static void viderMessage() {
        try {
             FacesContext.getCurrentInstance().getMessageList().clear();
        } catch (Exception e) {}
       
    }
    
    
    // MESSAGES D'EXCEPTION
    /////////////////////////////////////////////////////////////////////////////
    
    public static void addExceptionMessage(Exception ex) {
        String msg = "Opération impossible";
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, ex.toString());
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public static void addExceptionMessage(String msg, Exception ex) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, ex.toString());
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
            
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }
    
    
    // TRAITEMENT DE REQUETES
    /////////////////////////////////////////////////////////////////////////////
    
    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
   
    
    // MANIPULATION DE TABLEAU
    /////////////////////////////////////////////////////////////////////////////
    
    public static <T> Collection<T> arrayToCollection(T[] arr) {
        if (arr == null) {
            return new ArrayList<T>();
        }
        return Arrays.asList(arr);
    }
    
    public static Object[] collectionToArray(Collection<?> c) {
        if (c == null) {
            return new Object[0];
        }
        return c.toArray();
    }
     
    
    // METHODES DE CONVERSION
    /////////////////////////////////////////////////////////////////////////////
   
    public static String getAsConvertedString(Object object, Converter converter) {
        return converter.getAsString(FacesContext.getCurrentInstance(), null, object);
    }
    
    public static String getAsString(Object object) {
        if (object instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>)object;
            if (collection.size() == 0) {
                return "(No Items)";
            }
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (Object item : collection) {
                if (i > 0) {
                    sb.append("<br />");
                }
                sb.append(item);
                i++;
            }
            return sb.toString();
        }
        return String.valueOf(object);
    }
    
    public static int random(){
        Random randGen = new Random();
        int randNum = randGen.nextInt(10000);
        return randNum;
    }
}
