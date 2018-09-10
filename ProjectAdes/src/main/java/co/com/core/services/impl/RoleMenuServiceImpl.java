package co.com.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.core.commons.converter.RoleMenuUtil;
import co.com.core.commons.converter.RoleUtil;
import co.com.core.dao.RoleMenuDAO;
import co.com.core.domain.RoleMenu;
import co.com.core.dto.RoleDTO;
import co.com.core.dto.RoleMenuDTO;
import co.com.core.services.IRoleMenuService;

public class RoleMenuServiceImpl implements IRoleMenuService{
	
	RoleMenuDAO roleMenuDao;

	@Override
	public List<RoleMenuDTO> getAll() {
		 List<RoleMenuDTO> dtos= new ArrayList<RoleMenuDTO>();
		 List<RoleMenu> entity = roleMenuDao.getAll();
		 if (entity != null && entity.size() > 0) {
			 for (RoleMenu roleMenu : entity) {
				dtos.add(RoleMenuUtil.getDtoFromEntity(roleMenu));
			}
			
		}
		return dtos;
	}

	@Override
	public void create(RoleMenuDTO dto) {
         roleMenuDao.createRoleMenu(RoleMenuUtil.getEntityFromDto(dto));		
	}

	@Override
	public void delete(RoleMenuDTO dto) {
		roleMenuDao.delete(RoleMenuUtil.getEntityFromDto(dto));
		
	}

	@Override
	public void update(RoleMenuDTO dto) {
	   roleMenuDao.update(RoleMenuUtil.getEntityFromDto(dto));
		
	}

	@Override
	public List<RoleMenuDTO> findMenuById(RoleDTO dto) {
		List<RoleMenuDTO> dtos = new  ArrayList<RoleMenuDTO>() ; 
		List<RoleMenu> entity = roleMenuDao.findMenuByRole(RoleUtil.getEntityFromDto(dto));	
		
		if (entity != null && entity.size() >0) {
			for (RoleMenu roleMenu : entity) {
				dtos.add(RoleMenuUtil.getDtoFromEntity(roleMenu));
			}
			
		}
		return dtos;
	}

	public RoleMenuDAO getRoleMenuDao() {
		return roleMenuDao;
	}

	public void setRoleMenuDao(RoleMenuDAO roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}
	
	

}
