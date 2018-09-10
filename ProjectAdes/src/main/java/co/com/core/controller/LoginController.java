package co.com.core.controller;

import static co.com.core.commons.LoadBundle.geProperty;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.core.commons.ApplicationConstants;
import co.com.core.commons.EncryptDecrypt;
import co.com.core.commons.SessionUtil;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IUsuariosService;

public class LoginController {

	private String userEmail;
	private String userPassword;
	private Integer userId;
	private boolean isLogged;
	
	private IUsuariosService userService;
	private MenuController  menuController;
	private UsuariosDTO dto;
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	public void init() {
		menuController.loadGeneral();
	}



	
	public String validateLogin() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String encrypted = EncryptDecrypt.encrypt(userPassword);
			
              dto = userService.login(userEmail, encrypted);
              
              if (dto != null) {
            	  short isLockedAccount = (dto.getAccountlocked() == null ? 0 : dto.getAccountlocked().shortValue());
            	  if (isLockedAccount == ApplicationConstants.USER_ACCOUNT_UNLOCKED) {
            		  if (dto.getActive() == 1) {
            			  
            			  logger.info("Entro en el if");
            			 
            			  context.getExternalContext().getSessionMap().put("usuario", dto);
                    	  this.isLogged = true;
                    	  menuController.loadMenu(dto);
                    	  return "home/perfil.xhtml?faces-redirect=true";
                    	  

					}else {
		            	  this.isLogged = false;
		            	  context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
									geProperty("inactiveUser"),geProperty("pleaseVerifySummary")));

					}
					
				}else {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							geProperty("inactiveUser"),geProperty("pleaseVerifySummary")));
				}
            	  
            	  
				
			}else {
				
				registerSessionAttemp((short) 0, userEmail);
          	    this.isLogged = false;
          	   

			}
			
		} catch (Exception e) {
			logger.error("Throwed Exception [LoginController.validateLogin]:"+e.getMessage());
		}
		return null;
		
	}
	
	
	public void lockUserAccount(String currentUserMail) {
		try {
			
			UsuariosDTO dto = userService.getByMail(currentUserMail);
			if (dto != null) {
				dto.setAccountlocked(ApplicationConstants.USER_ACCOUNT_LOCKED);
				userService.update(dto);
			}
			
		} catch (Exception e) {
			logger.error("Throwed Exception [LoginController.lockUserAccount]:"+e.getMessage());
		}
		
	}
	
	public void registerSessionAttemp(Short validAttempt, String userMail) {
		try {
			
			HttpServletRequest request = SessionUtil.getRequest();
			String userAgent = SessionUtil.getRequest().getHeader("User-Agent");
			
			String ipAdresss = request.getHeader("X-FORWARDED-FOR");
			
			if (ipAdresss == null) {
				ipAdresss = request.getRemoteAddr();
				
			}
			
			
	
		} catch (Exception e) {
			logger.error("Throwed Exception [LoginController.registerSessionAttemp]:"+e.getMessage());
		}
		
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		this.isLogged = false;
		return "/login.xhtml?faces-redirect=true";
	}




	public String getUserEmail() {
		return userEmail;
	}




	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}




	public String getUserPassword() {
		return userPassword;
	}




	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}




	public Integer getUserId() {
		return userId;
	}




	public void setUserId(Integer userId) {
		this.userId = userId;
	}




	public boolean isLogged() {
		return isLogged;
	}




	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}




	public IUsuariosService getuserService() {
		return userService;
	}




	public void setuserService(IUsuariosService userService) {
		this.userService = userService;
	}




	public MenuController getmenuController() {
		return menuController;
	}




	public void setmenuController(MenuController menuController) {
		this.menuController = menuController;
	}




	public UsuariosDTO getDto() {
		return dto;
	}




	public void setDto(UsuariosDTO dto) {
		this.dto = dto;
	}




}
