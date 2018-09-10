package co.com.core.commons;

import java.math.BigDecimal;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.com.core.dto.UsuariosDTO;


public class SessionUtil {
	 
	public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
 
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
 
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
    
    public static UsuariosDTO getSessionUser() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	UsuariosDTO userDto = (UsuariosDTO) context.getExternalContext().getSessionMap().get("user");
    	return userDto;
    }
    
    public static BigDecimal getSessionUserId() {
    	FacesContext context = FacesContext.getCurrentInstance();
		UsuariosDTO userDto = (UsuariosDTO) context.getExternalContext().getSessionMap().get("user");
		if(userDto.getIdUsuario()!=null) {
			return userDto.getIdUsuario();
		}
		return null;
    }
    
}
