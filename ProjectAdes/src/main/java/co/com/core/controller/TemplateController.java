package co.com.core.controller;

import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import co.com.core.commons.converter.MenuUtil;
import co.com.core.commons.converter.UsuariosUtil;
import co.com.core.domain.Usuarios;
import co.com.core.dto.MenuDTO;
import co.com.core.dto.PaginaDTO;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IMenuService;
import co.com.core.services.IPaginaService;

public class TemplateController {
	
	private IMenuService menuService;
	private IPaginaService iPaginaService;
	private static final Logger logger = Logger.getLogger(TemplateController.class);
	
	
	public void validateSession() {
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		
			if (viewId != null && !viewId.contains("login")) {
				
				UsuariosDTO dto = (UsuariosDTO) context.getExternalContext().getSessionMap().get("usuario");
				
				if (dto == null) {
					context.getExternalContext().redirect("/ProjectAdes/inicio");
					
				}else {
					boolean validatePage= validateAllowPage(dto, viewId);
						if (validatePage) {
							context.getExternalContext().redirect("/ProjectAdes/bienvenido");
							logger.info("Invalid page access: "+dto.getNombreUsuario() +"Page "+viewId);
						}
						logger.info("Valid Session: "+dto.getNombreUsuario() +"Page "+viewId);
				}
			}
		} catch (Exception e) {
			logger.error("Exception validating session: "+e.getMessage());
		}
		
		
		
	}
	
	
	public boolean validateAllowPage(UsuariosDTO dto , String viewid) {
		 boolean allowPage = false;
		 
		 Usuarios usuarios = UsuariosUtil.getEntityFromDto(dto);
		 
		 List<MenuDTO> menu = menuService.getUserMenu(usuarios);
		 
		 PaginaDTO paginaDTO = iPaginaService.getPageByUrl(viewid);
		 
		 if (menu != null && menu.size() >0) {
			 if (paginaDTO != null) {
				 for (MenuDTO menuDTO : menu) {
					if (menuDTO.getIdPagina() != null) {
						if (menuDTO.getIdPagina().getIdPagina() == paginaDTO.getIdPagina()) {
							allowPage = true;
							logger.info("Allowed page:"+paginaDTO.getUrl());
							
						}
						
					}
				}
				
			}
			
		}
		 
		 return allowPage;
		 
		
	}
	
	
	public PaginaDTO getPageFromUrl() {
		PaginaDTO dto = new PaginaDTO();
		return dto;
		
	}


	public IMenuService getmenuService() {
		return menuService;
	}


	public void setmenuService(IMenuService menuService) {
		this.menuService = menuService;
	}


	public IPaginaService getiPaginaService() {
		return iPaginaService;
	}


	public void setiPaginaService(IPaginaService iPaginaService) {
		this.iPaginaService = iPaginaService;
	}
	
	

}
