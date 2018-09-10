package co.com.core.commons.converter;

import co.com.core.domain.RoleMenu;
import co.com.core.dto.RoleMenuDTO;

public class RoleMenuUtil {
	
	public static RoleMenuDTO getDtoFromEntity(RoleMenu entity ) {
		    RoleMenuDTO dto = new RoleMenuDTO();
		    dto.setRoleId(entity.getRoleId());
		    dto.setMenuId(entity.getMenuId());
		    dto.setIdRoleMeu(entity.getIdRoleMeu());
		 return dto;
	}
	
	
	public static RoleMenu getEntityFromDto (RoleMenuDTO dto ) {
		RoleMenu entity = new RoleMenu();
		entity.setRoleId(dto.getRoleId());
		entity.setMenuId(dto.getMenuId());
		entity.setIdRoleMeu(entity.getIdRoleMeu());
		
		return entity;
		
	}

}
