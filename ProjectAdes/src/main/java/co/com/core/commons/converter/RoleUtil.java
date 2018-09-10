package co.com.core.commons.converter;

import co.com.core.domain.Role;
import co.com.core.dto.RoleDTO;

public class RoleUtil {
	
	
	public static RoleDTO getDtoFromEntity(Role entity) {
		
		RoleDTO dto = new RoleDTO();
		dto.setDescripcion(entity.getDescripcion());
		dto.setRoleName(entity.getRoleName());
		dto.setRoleId(entity.getRoleId());
		
		return dto;
		
	}
	
	public static Role getEntityFromDto(RoleDTO dto) {
		Role role = new Role();
		role.setDescripcion(dto.getDescripcion());
		role.setRoleName(dto.getRoleName());
		role.setRoleId(dto.getRoleId());
		return role;
	}

}
