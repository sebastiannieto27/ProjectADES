package co.com.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.core.commons.converter.MenuUtil;
import co.com.core.commons.converter.RoleUsuariosUtil;
import co.com.core.commons.converter.UsuariosUtil;
import co.com.core.dao.MenuDAO;
import co.com.core.domain.Menu;
import co.com.core.domain.RoleMenu;
import co.com.core.domain.RoleUsuarios;
import co.com.core.domain.Usuarios;
import co.com.core.dto.MenuDTO;
import co.com.core.dto.RoleUsuariosDTO;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IMenuService;

public class MenuServiceImpl implements IMenuService {
	
	MenuDAO  menuDao;
	

	@Override
	public List<MenuDTO> getUserMenu(Usuarios usuarios) {
		  List<MenuDTO> dtos = new ArrayList<MenuDTO>();
		  List<RoleUsuarios> roleUser = menuDao.getUserRoles(usuarios);
		  List<RoleMenu> roleMenu = menuDao.getUserRoleMenu(roleUser);
		  List<Menu> userMenu = menuDao.getUserMenuOptions(roleMenu);
		  
		  if (userMenu != null && userMenu.size() > 0) {
			  for (Menu menu : userMenu) {
				dtos.add(MenuUtil.getDtoFromEntity(menu));
			}
			
		}

		return dtos;
	}

	@Override
	public List<MenuDTO> getGeneral() {
		List<MenuDTO> dtos = new ArrayList<MenuDTO>();
		List<Menu> entity = menuDao.getMenuGeneral();
		
		if (entity != null && entity.size() > 0) {
			for (Menu menu : entity) {
				dtos.add(MenuUtil.getDtoFromEntity(menu));
			}
			
		}
		return dtos;
	}

	@Override
	public List<MenuDTO> getAll() {
		List<MenuDTO> dtos = new ArrayList<MenuDTO>();
		List<Menu> entity = menuDao.getAll();
		
		if (entity != null && entity.size() > 0) {
			for (Menu menu : entity) {
				dtos.add(MenuUtil.getDtoFromEntity(menu));
				
			}
			
		}
		
		return dtos;
	}

	@Override
	public List<RoleUsuariosDTO> getUserRoles(UsuariosDTO dto) {
		  List<RoleUsuariosDTO> dtos = null;
		  List<RoleUsuarios> userRole = menuDao.getUserRoles(UsuariosUtil.getEntityFromDto(dto));
		  	if (userRole != null && userRole.size() > 0) {
		  		
				dtos = new ArrayList<RoleUsuariosDTO>();
				
		  		for (RoleUsuarios roleUsuarios : userRole) {
		  			dtos.add(RoleUsuariosUtil.getDtoFromEntity(roleUsuarios));
				}
				
			}
				  
 		return dtos;
	}

	@Override
	public List<MenuDTO> getNotAssignedMenu(String ids) {
		List<MenuDTO> dtos = new ArrayList<MenuDTO>();
		List<Menu> entity = menuDao.getNotAssignedMenu(ids);
		
			if (entity != null && entity.size() > 0) {
				for (Menu menu : entity) {
					dtos.add(MenuUtil.getDtoFromEntity(menu));
				}
				
			}
		return dtos;
	}

	@Override
	public void create(MenuDTO dto) {
		 menuDao.create(MenuUtil.getEntityFromDto(dto));
		
	}

	@Override
	public void delete(MenuDTO dto) {
		menuDao.delete(MenuUtil.getEntityFromDto(dto));
		
	}

	@Override
	public void update(MenuDTO dto) {
		menuDao.update(MenuUtil.getEntityFromDto(dto));
		
	}

	public MenuDAO getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDAO menuDao) {
		this.menuDao = menuDao;
	}
	
	

}
