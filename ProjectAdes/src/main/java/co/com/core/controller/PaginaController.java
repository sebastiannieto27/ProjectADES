package co.com.core.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import co.com.core.dto.PaginaDTO;
import co.com.core.services.IPaginaService;

public class PaginaController {
	
	
	IPaginaService iPaginaService;
	List<PaginaDTO> dtos;
	private PaginaDTO selected;
	private static final Logger logger = Logger.getLogger(PaginaController.class);
	
	public void init () {
		dtos = iPaginaService.getAll();
	}
	
	
	public void saveNew() {
	
		try {
			iPaginaService.create(selected);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion exitosa","Pagina"));
	        	
		} catch (Exception e) {
			logger.error("Throwed Exception [PaginaController.saveNew]:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en creacion","Pagina"));
		}finally {
			dtos = iPaginaService.getAll();
		}
		
	}
	
	
	public void delete () {
		if (selected != null) {
			
			try {
				iPaginaService.delete(selected);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion exitosa","Pagina"));
				
			} catch (Exception e) {
				logger.error("Throwed Exception [PaginaController.delete]:"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en eliminacion","Pagina"));
			}finally {
				dtos = iPaginaService.getAll();
			}
			
		}
	}
	
	
	public void save() {
		if (selected != null) {
			try {
				iPaginaService.update(selected);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizacion exitosa","Pagina"));
				
			} catch (Exception e) {
				logger.error("Throwed Exception [PaginaController.delete]:"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de actualizacion","Pagina"));
			}finally {
				dtos= iPaginaService.getAll();
			}
			
		}
	}
	
	
	public void prepareCreate() {
		selected = new PaginaDTO();
	}


	public IPaginaService getiPaginaService() {
		return iPaginaService;
	}


	public void setiPaginaService(IPaginaService iPaginaService) {
		this.iPaginaService = iPaginaService;
	}


	public List<PaginaDTO> getDtos() {
		return dtos;
	}


	public void setDtos(List<PaginaDTO> dtos) {
		this.dtos = dtos;
	}


	public PaginaDTO getSelected() {
		return selected;
	}


	public void setSelected(PaginaDTO selected) {
		this.selected = selected;
	}





	
	

}
