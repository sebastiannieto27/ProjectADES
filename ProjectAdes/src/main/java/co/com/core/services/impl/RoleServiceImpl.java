package co.com.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import co.com.core.commons.converter.RoleUtil;
import co.com.core.dao.RoleDAO;
import co.com.core.domain.Role;
import co.com.core.dto.RoleDTO;
import co.com.core.services.IRoleService;

public class RoleServiceImpl implements IRoleService {
	
	RoleDAO roleDao;
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Override
	public List<RoleDTO> getAll() {
		List<RoleDTO> dtos = new ArrayList<RoleDTO>();
		List<Role> entity = roleDao.getAll();
		
		if (entity != null && entity.size() >0) {
			
			for (Role role : entity) {
				dtos.add(RoleUtil.getDtoFromEntity(role));
			}
			
		}
		return dtos;
	}

	@Override
	public void createRole(RoleDTO roleDto) {
		roleDao.createRole(RoleUtil.getEntityFromDto(roleDto));
		
	}

	@Override
	public void delete(RoleDTO roleDto) {
		roleDao.delete(RoleUtil.getEntityFromDto(roleDto));
		
	}

	@Override
	public void update(RoleDTO roleDto) {
		roleDao.update(RoleUtil.getEntityFromDto(roleDto));
		
	}

	@Override
	public List<RoleDTO> getNotAssignedRole(String ids) {
		List<RoleDTO> dtos = new ArrayList<RoleDTO>();
		List<Role> entity = roleDao.getAll();
		
		if (entity != null && entity.size() >0) {
			
			for (Role role : entity) {
				dtos.add(RoleUtil.getDtoFromEntity(role));
			}
			
		}
		return dtos;
	}

	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}
	
	

}
