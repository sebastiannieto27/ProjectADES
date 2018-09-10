package co.com.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.core.commons.converter.RoleUsuariosUtil;
import co.com.core.commons.converter.UsuariosUtil;
import co.com.core.dao.RoleUsuariosDAO;
import co.com.core.domain.RoleUsuarios;
import co.com.core.dto.RoleUsuariosDTO;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IRoleUsuariosService;

public class RoleUsuariosServiceImpl implements IRoleUsuariosService {
	
	RoleUsuariosDAO roleUsuariosDao;

	@Override
	public List<RoleUsuariosDTO> getAll() {
		List<RoleUsuariosDTO> dtos = null;
		List<RoleUsuarios> entity= roleUsuariosDao.getAll();
		
		if (entity != null && entity.size() > 0) {
			dtos = new ArrayList<RoleUsuariosDTO>();
			for (RoleUsuarios roleUsuarios : entity) {
				dtos.add(RoleUsuariosUtil.getDtoFromEntity(roleUsuarios));
			}
			
		}
		return dtos;
	}

	@Override
	public void createUserRole(RoleUsuariosDTO userRoleDto) {
		roleUsuariosDao.createUserRole(RoleUsuariosUtil.getEntityFromDto(userRoleDto));
		
	}

	@Override
	public void delete(RoleUsuariosDTO userRoleDto) {
		roleUsuariosDao.delete(RoleUsuariosUtil.getEntityFromDto(userRoleDto));
		
	}

	@Override
	public void update(RoleUsuariosDTO userRoleDto) {
		roleUsuariosDao.update(RoleUsuariosUtil.getEntityFromDto(userRoleDto));
		
	}

	@Override
	public List<RoleUsuariosDTO> findByUser(UsuariosDTO dto) {
		List<RoleUsuariosDTO> dtos = new ArrayList<RoleUsuariosDTO>();
		List<RoleUsuarios> entity = roleUsuariosDao.findByUser(UsuariosUtil.getEntityFromDto(dto));
		
		if (entity != null && entity.size() > 0) {
			for (RoleUsuarios roleUsuarios : entity) {
				dtos.add(RoleUsuariosUtil.getDtoFromEntity(roleUsuarios));
			}
			
		}
		
		
		return dtos;
	}

	public RoleUsuariosDAO getRoleUsuariosDao() {
		return roleUsuariosDao;
	}

	public void setRoleUsuariosDao(RoleUsuariosDAO roleUsuariosDao) {
		this.roleUsuariosDao = roleUsuariosDao;
	}
	
	

}
