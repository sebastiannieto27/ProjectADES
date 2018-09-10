package co.com.core.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import co.com.core.commons.converter.UsuariosUtil;
import co.com.core.domain.Menu;
import co.com.core.domain.Pagina;
import co.com.core.domain.Usuarios;
import co.com.core.dto.MenuDTO;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IMenuService;

public class MenuController {
	
	private List<Menu> dtos;
	private MenuModel menuModel;
	private MenuModel GeneralModel;
	
	private IMenuService menuService;
	
	List<MenuDTO> items;
	private MenuDTO selected;
	private BigDecimal parentIdMenu;
	private BigDecimal paginaId;
	
	private static final Logger logger =  Logger.getLogger(MenuController.class);
	
	
	public void init() {
		menuModel = new DefaultMenuModel();
		items = menuService.getAll();
	}
	
	
	public void loadMenu(UsuariosDTO dto) {
		
		Usuarios usuarios = UsuariosUtil.getEntityFromDto(dto);
		List<MenuDTO>  dtos = menuService.getUserMenu(usuarios);
		
		if (dtos != null && dtos.size() > 0) {
			for (MenuDTO menuDTO : dtos) {
				if (menuDTO.getSubmenu() != null){
					DefaultSubMenu submenu = new DefaultSubMenu(menuDTO.getMenuName());
					
					for (MenuDTO menuItem : dtos) {
						
						if (menuItem.getSubmenu() == 1  && menuItem.getParentMenuId() != null) {
							
							logger.info("submenu"+menuItem.getSubmenu());
							logger.info("ParentMenu"+menuItem.getParentMenuId());
		
							if (menuItem.getParentMenuId().getMenuId() == menuDTO.getMenuId()) {
								
								logger.info("Menu id parent"+menuItem.getParentMenuId().getMenuId());
								logger.info("Menu id "+menuDTO.getMenuId());
								
								DefaultMenuItem defaultMenuItem = new DefaultMenuItem(menuItem.getMenuName());
								
								if (menuItem.getIdPagina() != null) {
									
									logger.info("Pagina no null");

									defaultMenuItem.setUrl(menuItem.getIdPagina().getUrl());	
								}
								submenu.addElement(defaultMenuItem);

							}	
						}
						
					}
					
					if (menuDTO.getParentMenuId() == null) {
						menuModel.addElement(submenu);
					}
					
					
				}
				
			}
			
		}
		

		
		
		
	}
	
	
	public void loadGeneral() {
		
		GeneralModel = new DefaultMenuModel();
		List<MenuDTO> dtos = menuService.getGeneral();
		
		if (dtos != null && dtos.size() > 0) {
			
			for (MenuDTO menuDTO : dtos) {
				
				if (menuDTO.getSubmenu() != null) {
					
					DefaultSubMenu subMenu = new DefaultSubMenu(menuDTO.getMenuName());
					
					for (MenuDTO menuItem : dtos) {
						
						if (menuItem.getSubmenu() == 1 && menuItem.getParentMenuId() != null ) {
							
							if (menuItem.getParentMenuId().getMenuId() == menuDTO.getMenuId()) {
							
								DefaultMenuItem item = new DefaultMenuItem(menuItem.getMenuName());
								item.setUrl(menuItem.getIdPagina().getUrl());
							
							
						   }
						}
							
					}
					
					GeneralModel.addElement(subMenu);
			
				}	
				
			}	
		}

	}
	
	
	/*************************ADMIN********************************/
	
	
	public void prepareEdit() {
		
		selected = new MenuDTO();
	}
	
	public void prepareCreate() {
		selected = new MenuDTO();
	}
	
	
	public void saveNew() {
		
		try {
			
			Pagina pagina = new Pagina(paginaId);
			
			if (parentIdMenu != null  &&  parentIdMenu.signum() != 0) {
				
				Menu parentMenu = new Menu(parentIdMenu);
				selected.setParentMenuId(parentMenu);
				
			}
			
			selected.setIdPagina(pagina);
			menuService.create(selected);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion Exitosa","Menu"));
			
			
		} catch (Exception e) {
			
			logger.error("Throwed Exception [MenuController.saveNew]:"+e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la Creacion","Menu"));
			
		}finally {
			
			menuService.getAll();
		}
		
	}
	
	
	public void delete () {
		
		if (this.selected != null) {
			
			try {
				menuService.delete(selected);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion Exitosa","Menu"));
			} catch (Exception e) {
				logger.error("Throwed Exception [MenuController.delete]:"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en  la Eliminacion","Menu"));

			}finally {
				menuService.getAll();
			}
			
		}
		
		
		
	}
	
	
	public void save() {
		
		if (this.selected != null) {
			
			try {
				
				Pagina pagina = new Pagina(paginaId);
				
				if (parentIdMenu != null && parentIdMenu.signum() != 0) {
					
					Menu parentMenu = new Menu(parentIdMenu);
					selected.setParentMenuId(parentMenu);
					
				}
				
				selected.setIdPagina(pagina);
				
				menuService.update(selected);

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Actualizado", "Menu"));
				
			} catch (Exception e) {
				logger.error("Throwed Exception [MenuController.save]:"+e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error en la Actualizacion ","Menu"));

				
				
			}finally {
				menuService.getAll();
			}
			
		}
	}


	public List<MenuDTO> getItems() {
		return items;
	}


	public void setItems(List<MenuDTO> items) {
		this.items = items;
	}


	public MenuDTO getSelected() {
		return selected;
	}


	public void setSelected(MenuDTO selected) {
		this.selected = selected;
	}


	public IMenuService getMenuService() {
		return menuService;
	}


	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}


	public MenuModel getMenuModel() {
		return menuModel;
	}


	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}


	public MenuModel getGeneralModel() {
		return GeneralModel;
	}


	public void setGeneralModel(MenuModel generalModel) {
		GeneralModel = generalModel;
	}


	public BigDecimal getParentIdMenu() {
		return parentIdMenu;
	}


	public void setParentIdMenu(BigDecimal parentIdMenu) {
		this.parentIdMenu = parentIdMenu;
	}


	public BigDecimal getPaginaId() {
		return paginaId;
	}


	public void setPaginaId(BigDecimal paginaId) {
		this.paginaId = paginaId;
	}
	
	

}
