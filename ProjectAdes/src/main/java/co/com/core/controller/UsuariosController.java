package co.com.core.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

import co.com.core.commons.EncryptDecrypt;
import co.com.core.commons.LoadBundle;
import co.com.core.commons.ValidationUtil;
import co.com.core.commons.converter.RoleUsuariosUtil;
import co.com.core.commons.converter.RoleUtil;
import co.com.core.commons.converter.UsuariosUtil;
import co.com.core.domain.Usuarios;
import co.com.core.dto.RoleDTO;
import co.com.core.dto.RoleUsuariosDTO;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IRoleService;
import co.com.core.services.IRoleUsuariosService;
import co.com.core.services.IUsuariosService;


public class UsuariosController {
	
	private IUsuariosService userService;
	private IRoleUsuariosService roleUsuariosService;
	private IRoleService roleService;
	private List<UsuariosDTO> items;
	private UsuariosDTO selected;
	
    private LazyDataModel<UsuariosDTO> lazyModel;
    private static final Logger logger = Logger.getLogger(UsuariosController.class);
    private static final String DEFAULT_PASSWORD = "12345";
	
	
	
	private List<RoleDTO> notAssignedRoleItems;
	private List<RoleDTO> roleAssignItems;
	private List<RoleUsuariosDTO> userRoleQueryItems;
	private boolean roleCheckValues;
	private RoleDTO selectedRole;
	
	private String userNameSearch; 
	private BigDecimal userIdSearchResult; 
	private boolean showSearchData;
	private List<UsuariosDTO> searchResultsItems;
	private UsuariosDTO listBoxer;
	
	private List<RoleUsuariosDTO> deleteItems;
	private RoleUsuariosDTO selectdRoleUser;
	private boolean roleUserCheckValues;
	
	private String newPassword;
	private String confirmPassword;

	
	
	public void advancedSearch() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (userNameSearch != null && userNameSearch.length() > 4) {
			
			String localNameSearch = ValidationUtil.cleanString(userNameSearch);
			searchResultsItems = new ArrayList<UsuariosDTO>();
			searchResultsItems = userService.getUserByName(localNameSearch.toLowerCase());
			
			if (searchResultsItems != null && searchResultsItems.size() >0) {
				showSearchData = true;
				
			}else {
				showSearchData = false;
			}
			
		}else {
			System.out.println("Menos de 4 letras");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "menos de 4 letras", null));
		}
		
	}
	
	
	public void selectResult(UsuariosDTO dto) {
		userIdSearchResult = dto.getIdUsuario();
		userNameSearch = dto.getNombreCompleto();
		showSearchData = false;
		searchResultsItems = new ArrayList<UsuariosDTO>();
		
		
	}
	
	
	public void updatePasswrod() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			UsuariosDTO usuariosDTO = (UsuariosDTO) context.getExternalContext().getSessionMap().get("usuario");
			
			if (usuariosDTO != null) {
				context.getExternalContext().redirect("/ProjectAdes/login.xhtml");
				
			}else {
				if (newPassword.equals(confirmPassword)) {
					
					String encryptedPasswd = EncryptDecrypt.encrypt(newPassword);
					usuariosDTO.setPassword(encryptedPasswd);
					userService.update(usuariosDTO);
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , LoadBundle.geProperty("passwordConfirmationMsg"), null ));
					logger.info("Updated password:" +usuariosDTO.getIdUsuario() + "---" +  new Date());
					context.getExternalContext().invalidateSession();
					context.getExternalContext().redirect("/ProjectAdes/login.xhtml");
					}else {
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, LoadBundle.geProperty("editionError"), null));
					}
			}
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, LoadBundle.geProperty("editionError"), null));
			logger.error("Throwed Exception [UsuariosController.updatePasswrod]:"+e.getMessage());
		}
	}
	
	public void findNotAssignedRolesByUser(UsuariosDTO  dto) {
		this.selected = dto;
		try {
			deleteItems = new ArrayList<RoleUsuariosDTO>();
			roleAssignItems = new ArrayList<RoleDTO>();
			userRoleQueryItems = roleUsuariosService.findByUser(dto);
			String getRoleId =  getRolesIds();
			notAssignedRoleItems = roleService.getNotAssignedRole(getRoleId);
			
		} catch (Exception e) {
			logger.error("Error finding permissions by role: " + e.getMessage());
		}
		
	}
	
	public String getRolesIds() {
		int count = 0;
		StringBuilder ids = new StringBuilder();
		if (userRoleQueryItems != null &&  userRoleQueryItems.size() > 0) {
			for (RoleUsuariosDTO roleUsuariosDTO : userRoleQueryItems) {
				
				if (count > 0) {
					
					ids.append(",");
					
				}
			
				ids.append(roleUsuariosDTO.getIdUsuario().getIdUsuario());
				count ++;
				
			}
			
			
		}
		
		return ids.toString();
		
	}
	
	
	public void addRemoveRoleList(RoleDTO role) {
		
		try {
			if (roleCheckValues) {
				if (!roleAssignItems.contains(role)) {
					roleAssignItems.add(role);
				}
				
			} else {
				if (roleAssignItems.contains(role)) {
					roleAssignItems.remove(role);
				}

			}
		} catch (Exception e) {
			logger.error("Error UsuarioController.addRemoveRoleList: " + e.getMessage());
		}

		
	}
	
	
	public void addRoleToUser() {
		try {
			if (roleAssignItems != null && roleAssignItems.size() > 0) {
				for (RoleDTO roleDTO : roleAssignItems) {
					RoleUsuariosDTO roleUsuariosDTO = new RoleUsuariosDTO();
					roleUsuariosDTO.setRoleId(RoleUtil.getEntityFromDto(roleDTO));
					roleUsuariosDTO.setIdUsuario(UsuariosUtil.getEntityFromDto(selected));
					roleUsuariosService.createUserRole(roleUsuariosDTO);					
					
				}
				
			}
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuarioController.addRoleToUser]: " +e.getMessage());
		}finally {
			roleAssignItems = new ArrayList<RoleDTO>();
			findNotAssignedRolesByUser(selected);
		}
	}
	
	public void addRemoveUserRole(RoleUsuariosDTO dto) {
		
		try {
			if (roleUserCheckValues) {
				if (!deleteItems.contains(dto) ) {
					deleteItems.add(dto);
				}
				
			}else {
				if (deleteItems.contains(dto)) {
					deleteItems.remove(dto);
					
				}
				
			}
		} catch (Exception e) {
			logger.error("Error addRemoveUserRole: " + e.getMessage());
		}
		
	}
	
	
	public void removeRoleFromUser() {
		
		try {
			if (deleteItems != null && deleteItems.size() > 0) {
				 for (RoleUsuariosDTO roleUsuariosDTO : deleteItems) {
				    	roleUsuariosService.delete(roleUsuariosDTO);
						
					}
				
			}
			
		} catch (Exception e) {
			logger.error("Throwed Exception [UsuarioController.removeRoleFromUser]: " +e.getMessage());
		}finally {
			deleteItems = new ArrayList<RoleUsuariosDTO>();
			findNotAssignedRolesByUser(selected);
		}
	}
	
	
	public void init() {
		items = userService.getAll();
	}

	
	/**
	 * USER ADMIN ---  USER ADMIN ---  USER ADMIN ---  USER ADMIN ---  USER ADMIN ---  USER ADMIN ---  USER ADMIN ---  USER ADMIN --- 
	 */
	/**
	 * 
	 */
	
	public void saveNew() {
		try {
			String encryptedPasswd = EncryptDecrypt.encrypt(selected.getPassword()); 
			selected.setPassword(encryptedPasswd);
            userService.createUser(selected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion  Exitosa","Usuario"));
            
		} catch (Exception e) {
			
			logger.error("Throwed Exception [UsuarioController.saveNew]: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en Creacion ","Usuario"));

		} finally {
			items = userService.getAll();
		}
		
	}
	
	
	public void delete () {
		
		if (selected != null) {
			try {
				userService.delete(selected);
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion  Exitosa","Usuario"));
				
			} catch (Exception e) {
				logger.error("Throwed Exception [UsuarioController.delete]: "+e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en Eliminacion ","Usuario"));

			}finally {
				items = userService.getAll();
			}
			
		}
	}
	
	
	public void save() {
		if (this.selected != null) {
			try {
				
				String encryptedPasswd = EncryptDecrypt.encrypt(selected.getPassword()); 
				selected.setPassword(encryptedPasswd);
				userService.update(selected);
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion  Exitosa","Usuario"));		
			} catch (Exception e) {
				logger.error("Throwed Exception [UsuarioController.save]: "+e.getMessage());
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Error en Actualizacion ","Usuario"));		

			}finally {
				items = userService.getAll();
			}
			
		}
	}
	
	public void resetPassword(UsuariosDTO dto) {
		String encryptedPasswd = EncryptDecrypt.encrypt(DEFAULT_PASSWORD);
		dto.setPassword(encryptedPasswd);
		userService.update(dto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña Actualizada  Exitosa","Usuario"));		
	}
	
	
	public void selectOne() {
		System.out.println("selected:" + selected);
	}
	
	public void prepareCreate() {
		selected = new UsuariosDTO();
	}


	public IUsuariosService getUserService() {
		return userService;
	}


	public void setUserService(IUsuariosService userService) {
		this.userService = userService;
	}


	public IRoleUsuariosService getroleUsuariosService() {
		return roleUsuariosService;
	}


	public void setroleUsuariosService(IRoleUsuariosService roleUsuariosService) {
		this.roleUsuariosService = roleUsuariosService;
	}


	public IRoleService getRoleService() {
		return roleService;
	}


	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}


	public List<UsuariosDTO> getItems() {
		return items;
	}


	public void setItems(List<UsuariosDTO> items) {
		this.items = items;
	}


	public UsuariosDTO getSelected() {
		return selected;
	}


	public void setSelected(UsuariosDTO selected) {
		this.selected = selected;
	}


	public LazyDataModel<UsuariosDTO> getLazyModel() {
		return lazyModel;
	}


	public void setLazyModel(LazyDataModel<UsuariosDTO> lazyModel) {
		this.lazyModel = lazyModel;
	}


	public List<RoleDTO> getNotAssignedRoleItems() {
		return notAssignedRoleItems;
	}


	public void setNotAssignedRoleItems(List<RoleDTO> notAssignedRoleItems) {
		this.notAssignedRoleItems = notAssignedRoleItems;
	}


	public List<RoleUsuariosDTO> getUserRoleQueryItems() {
		return userRoleQueryItems;
	}


	public void setUserRoleQueryItems(List<RoleUsuariosDTO> userRoleQueryItems) {
		this.userRoleQueryItems = userRoleQueryItems;
	}


	public boolean isRoleCheckValues() {
		return roleCheckValues;
	}


	public void setRoleCheckValues(boolean roleCheckValues) {
		this.roleCheckValues = roleCheckValues;
	}


	public RoleDTO getSelectedRole() {
		return selectedRole;
	}


	public void setSelectedRole(RoleDTO selectedRole) {
		this.selectedRole = selectedRole;
	}


	public String getUserNameSearch() {
		return userNameSearch;
	}


	public void setUserNameSearch(String userNameSearch) {
		this.userNameSearch = userNameSearch;
	}


	public boolean isShowSearchData() {
		return showSearchData;
	}


	public void setShowSearchData(boolean showSearchData) {
		this.showSearchData = showSearchData;
	}

	public List<UsuariosDTO> getSearchResultsItems() {
		return searchResultsItems;
	}


	public void setSearchResultsItems(List<UsuariosDTO> searchResultsItems) {
		this.searchResultsItems = searchResultsItems;
	}


	public UsuariosDTO getListBoxer() {
		return listBoxer;
	}


	public void setListBoxer(UsuariosDTO listBoxer) {
		this.listBoxer = listBoxer;
	}


	public RoleUsuariosDTO getSelectdRoleUser() {
		return selectdRoleUser;
	}


	public void setSelectdRoleUser(RoleUsuariosDTO selectdRoleUser) {
		this.selectdRoleUser = selectdRoleUser;
	}


	public boolean isRoleUserCheckValues() {
		return roleUserCheckValues;
	}


	public void setRoleUserCheckValues(boolean roleUserCheckValues) {
		this.roleUserCheckValues = roleUserCheckValues;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
	
	
	

}
