package co.com.core.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import co.com.core.commons.LoadBundle;
import co.com.core.commons.converter.MenuUtil;
import co.com.core.commons.converter.RoleUtil;
import co.com.core.dto.MenuDTO;
import co.com.core.dto.RoleDTO;
import co.com.core.dto.RoleMenuDTO;
import co.com.core.services.IMenuService;
import co.com.core.services.IRoleMenuService;
import co.com.core.services.IRoleService;

public class RoleController {
	
	IRoleService roleService;
	IRoleMenuService roleMenuService;
	IMenuService menuService;
	
	List<RoleDTO> items;
	List<RoleMenuDTO> roleItems;
	List<MenuDTO> menuItems;
	List<RoleMenuDTO> deleteItems;
	private List<MenuDTO> menuList;
	
	private RoleDTO selected;
	private BigDecimal userIdSelected;
	private BigDecimal roleIdSelected;
	private RoleDTO selectedRole;
	private MenuDTO selectedMenu;
	private RoleMenuDTO selectedRoleMenu;
	
	private boolean checkValue;
	private boolean roleMenuCheckValue;
	
	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	
	public void init () {
		menuList = new ArrayList<MenuDTO>();
		deleteItems = new ArrayList<RoleMenuDTO>();
		items = roleService.getAll();
	}
	
	
	public void findMenuByRole (RoleDTO dto) {
		this.selectedRole = dto;
		try {
			roleItems = roleMenuService.findMenuById(dto);
			String menuIds = getMenuIds();
			menuItems = menuService.getNotAssignedMenu(menuIds);
			
		} catch (Exception e) {
			logger.error("Error finding menus by role: " + e.getMessage());
		}
		
		
	}
	
	public void addMenuToRol() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (menuList != null && menuList.size() > 0) {
				for (MenuDTO menuDTO : menuList) {
					RoleMenuDTO dto = new RoleMenuDTO();
					dto.setMenuId(MenuUtil.getEntityFromDto(menuDTO));
					dto.setRoleId(RoleUtil.getEntityFromDto(selectedRole));
					roleMenuService.create(dto);
				}
				
			}
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LoadBundle.geProperty("addItemSuccess"), null));
			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LoadBundle.geProperty("addItemError"), null));
			logger.error("Throwed Exception [RoleController.addMenuToRol]: " +e.getMessage());
			
		}
		

	}
	
	
	
	
	
	public String getMenuIds() {
		int counter = 0 ;
		StringBuilder ids = new StringBuilder();
		if (roleItems != null && roleItems.size() >0) {
			for (RoleMenuDTO roleMenuDTO : roleItems) {
				if (counter > 0) {
					ids.append(",");
					
				}
				ids.append(roleMenuDTO.getMenuId().getMenuId());
				counter++;
				
				
			}
			
		}
		return ids.toString();
	}
	
	
	public void addRemoveMenuList(MenuDTO  dto) {
		try {
			if (checkValue) {
				if (!menuList.contains(dto)) {
					menuList.add(dto);
					
				}else {
					if (menuList.contains(dto)) {
						menuList.remove(dto);
						
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("Error addRemoveMenuList: " + e.getMessage());
		}
		
	}
	
	
	public void addRemoveRoleMenu (RoleMenuDTO roleMenu) {
		try {
			if (roleMenuCheckValue) {
				if (!deleteItems.contains(roleMenu)) {
					deleteItems.add(roleMenu);
					
				}else {
					if (deleteItems.contains(roleMenu)) {
						deleteItems.remove(roleMenu);
						
					}
				}
				
			}
			
		} catch (Exception e) {
			logger.error("Error addRemoveRoleMenu: " + e.getMessage());
		}
		
	}
	
	public void removeMenuFromRol() {
		FacesContext context = FacesContext.getCurrentInstance();
		 try {
			 if (deleteItems != null && deleteItems.size() > 0) {
				 for (RoleMenuDTO roleMenuDTO : deleteItems) {
					roleMenuService.delete(roleMenuDTO);
				}
				
			}
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LoadBundle.geProperty("removeItemSucces"), null));			
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, LoadBundle.geProperty("removeItemError"), null));
			logger.error("Throwed Exception [RoleController.removeMenuFromRol]: " +e.getMessage());		
	    }finally {
	    	deleteItems = new ArrayList<RoleMenuDTO>();
	    	findMenuByRole(selectedRole);
		}

	}
	
	
	/*********************************************************************************************************************************
	*************************************************ADMIN****************************************************************************/
	/**
	 * creates a new entry
	 */
	
	public void saveNew() {
		try {
			roleService.createRole(selected);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Creación exitosa", "Página"));
		} catch (Exception e) {
			logger.error("Throwed Exception [RoleController.saveNew]: " +e.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error en creación", "Página"));		
		}finally {
			items = roleService.getAll();
		}
	}
	
	public void delete () {
		if (this.selected != null) {
			try {
				roleService.delete(selected);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Eliminacion exitosa", "Página"));
			} catch (Exception e) {
				logger.error("Throwed Exception [RoleController.delete]: " +e.getMessage());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error en eliminacion", "Página"));			
			}finally {
				items = roleService.getAll();

			}
			
			
			
			
		}
		
	}
	
	public void save() {
		if (this.selected != null) {
			try {
				
				roleService.update(selected);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Actualizacion exitosa", "Página"));
				
			} catch (Exception e) {
				logger.error("Throwed Exception [RoleController.delete]: " +e.getMessage());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error en actualizacion", "Página"));				}
			
		}
	}
	
	
    public void create () {
		selected = new RoleDTO();
	}


	public IRoleService getRoleService() {
		return roleService;
	}


	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}


	public IRoleMenuService getroleMenuService() {
		return roleMenuService;
	}


	public void setroleMenuService(IRoleMenuService roleMenuService) {
		this.roleMenuService = roleMenuService;
	}


	public IMenuService getMenuService() {
		return menuService;
	}


	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}


	public List<RoleDTO> getItems() {
		return items;
	}


	public void setItems(List<RoleDTO> items) {
		this.items = items;
	}


	public List<RoleMenuDTO> getRoleItems() {
		return roleItems;
	}


	public void setRoleItems(List<RoleMenuDTO> roleItems) {
		this.roleItems = roleItems;
	}


	public List<MenuDTO> getMenuItems() {
		return menuItems;
	}


	public void setMenuItems(List<MenuDTO> menuItems) {
		this.menuItems = menuItems;
	}


	public RoleDTO getSelected() {
		return selected;
	}


	public void setSelected(RoleDTO selected) {
		this.selected = selected;
	}


	public BigDecimal getUserIdSelected() {
		return userIdSelected;
	}


	public void setUserIdSelected(BigDecimal userIdSelected) {
		this.userIdSelected = userIdSelected;
	}


	public RoleDTO getSelectedRole() {
		return selectedRole;
	}


	public void setSelectedRole(RoleDTO selectedRole) {
		this.selectedRole = selectedRole;
	}


	public MenuDTO getSelectedMenu() {
		return selectedMenu;
	}


	public void setSelectedMenu(MenuDTO selectedMenu) {
		this.selectedMenu = selectedMenu;
	}


	public RoleMenuDTO getSelectedRoleMenu() {
		return selectedRoleMenu;
	}


	public void setSelectedRoleMenu(RoleMenuDTO selectedRoleMenu) {
		this.selectedRoleMenu = selectedRoleMenu;
	}


	public boolean isCheckValue() {
		return checkValue;
	}


	public void setCheckValue(boolean checkValue) {
		this.checkValue = checkValue;
	}


	public boolean isRoleMenuCheckValue() {
		return roleMenuCheckValue;
	}


	public void setRoleMenuCheckValue(boolean roleMenuCheckValue) {
		this.roleMenuCheckValue = roleMenuCheckValue;
	}
    
    
    
    

}



