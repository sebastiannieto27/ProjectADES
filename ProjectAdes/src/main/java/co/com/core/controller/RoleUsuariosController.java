package co.com.core.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import co.com.core.domain.Role;
import co.com.core.domain.Usuarios;
import co.com.core.dto.RoleUsuariosDTO;
import co.com.core.services.IRoleUsuariosService;

public class RoleUsuariosController {
	
	IRoleUsuariosService roleUsuariosService;
	List<RoleUsuariosDTO> dtos;
	private RoleUsuariosDTO selected;
	private BigDecimal userIdSelected;
	private BigDecimal roleIdSelected;
	
	private static final Logger logger = Logger.getLogger(RoleUsuariosController.class);
	
	public void init() {
		dtos = roleUsuariosService.getAll();
	}
	
	
	public void saveNew() {
		try {
			Usuarios usuarios = new Usuarios(userIdSelected);
			selected.setIdUsuario(usuarios);
			
			Role role = new Role(roleIdSelected);
			selected.setRoleId(role);
			
			roleUsuariosService.createUserRole(selected);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion Exitosa","Pagina"));
			
			
		} catch (Exception e) {
			
			logger.error("Throwed Exception [RoleUsuariosController.saveNew]:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Creacion ","Pagina"));
		}finally {
			dtos = roleUsuariosService.getAll();
		}
		
		
	}
	
	
	public void delete() {
		
		if (this.selected != null) {
			
			try {
				roleUsuariosService.delete(selected);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminacion Exitosa","Pagina"));
				
			} catch (Exception e) {
				logger.error("Throwed Exception [RoleUsuariosController.delete]"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Eliminacion","Pagina"));
			}finally {
				dtos = roleUsuariosService.getAll();
			}
			
		}
	}
	
	public void save() {
		if (this.selected != null) {
			try {
				Usuarios usuarios = new Usuarios(userIdSelected);
				selected.setIdUsuario(usuarios);
				
				Role role = new Role(roleIdSelected);
				selected.setRoleId(role);
				
				roleUsuariosService.update(selected);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion Exitosa","Pagina"));
			} catch (Exception e) {
				logger.error("Throwed Exception [RoleUsuariosController.save]"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Actualizacion","Pagina"));
			}finally {
				dtos= roleUsuariosService.getAll();
			}
			
			
			
		}
		
	}
	
	public void prepareCreate() {
		selected = new RoleUsuariosDTO();
		
	}


	public IRoleUsuariosService getRoleUsuariosService() {
		return roleUsuariosService;
	}


	public void setRoleUsuariosService(IRoleUsuariosService roleUsuariosService) {
		this.roleUsuariosService = roleUsuariosService;
	}


	public List<RoleUsuariosDTO> getDtos() {
		return dtos;
	}


	public void setDtos(List<RoleUsuariosDTO> dtos) {
		this.dtos = dtos;
	}


	public RoleUsuariosDTO getSelected() {
		return selected;
	}


	public void setSelected(RoleUsuariosDTO selected) {
		this.selected = selected;
	}


	public BigDecimal getUserIdSelected() {
		return userIdSelected;
	}


	public void setUserIdSelected(BigDecimal userIdSelected) {
		this.userIdSelected = userIdSelected;
	}


	public BigDecimal getRoleIdSelected() {
		return roleIdSelected;
	}


	public void setRoleIdSelected(BigDecimal roleIdSelected) {
		this.roleIdSelected = roleIdSelected;
	}


}
