package co.com.core.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import co.com.core.domain.Menu;
import co.com.core.domain.Role;
import co.com.core.dto.RoleMenuDTO;
import co.com.core.services.IRoleMenuService;

public class RoleMenuController {
	
	IRoleMenuService roleMenuService;
    List<RoleMenuDTO> dtos;
    
    private RoleMenuDTO selectd;
    private BigDecimal selectedMenuId;
    private BigDecimal selectedRoleid;
    
    private static final Logger logger = Logger.getLogger(RoleMenuController.class);
    
    
    public void init() {
    	dtos = roleMenuService.getAll();
    }
    
    
    public void saveNew() {
    	try {
    		
    		Menu menu = new Menu(selectedMenuId);
    		selectd.setMenuId(menu);
    		
    		Role role = new Role(selectedRoleid);
    		selectd.setRoleId(role);
    		
    		roleMenuService.create(selectd);
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación exitosa","Pagina"));
    		
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuController.saveNew]:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de creacion","Pagna"));
		}finally {
			dtos = roleMenuService.getAll(); 
		}
    }
    
    
    public void save() {
    	if (selectd != null) {
			try {
    		Menu menu = new Menu(selectedMenuId);
    		selectd.setMenuId(menu);
    		
    		Role role = new Role(selectedRoleid);
    		selectd.setRoleId(role);
    		
    		roleMenuService.update(selectd);
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación exitosa","Pagina"));
			
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleMenuController.save]:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Creacion","Pagina"));
		}finally {
			dtos  = roleMenuService.getAll();
		}
			
		}
    	
    }
    
    public void delete() {
    	if (selectd != null) {
    		try {
				roleMenuService.delete(selectd);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion exitosa","Pagina"));
			} catch (Exception e) {
				logger.error("Throwed Exception [RoleMenuController.delete]:"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la eliminacion","Pagina"));
			}finally {
				dtos = roleMenuService.getAll();
			}
			
		}
    }
    
    
    
    public void prepareCreate() {
    	selectd = new RoleMenuDTO();
    }


	public IRoleMenuService getroleMenuService() {
		return roleMenuService;
	}


	public void setroleMenuService(IRoleMenuService roleMenuService) {
		this.roleMenuService = roleMenuService;
	}


	
	
	public List<RoleMenuDTO> getDtos() {
		return dtos;
	}


	public void setDtos(List<RoleMenuDTO> dtos) {
		this.dtos = dtos;
	}


	public RoleMenuDTO getSelectd() {
		return selectd;
	}


	public void setSelectd(RoleMenuDTO selectd) {
		this.selectd = selectd;
	}


	public BigDecimal getSelectedMenuId() {
		return selectedMenuId;
	}


	public void setSelectedMenuId(BigDecimal selectedMenuId) {
		this.selectedMenuId = selectedMenuId;
	}


	public BigDecimal getSelectedRoleid() {
		return selectedRoleid;
	}


	public void setSelectedRoleid(BigDecimal selectedRoleid) {
		this.selectedRoleid = selectedRoleid;
	}
    
    
    

}
