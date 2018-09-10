package co.com.core.commons.converter;

import co.com.core.domain.RoleUsuarios;
import co.com.core.dto.RoleUsuariosDTO;

public class RoleUsuariosUtil {
	
	
	public static RoleUsuariosDTO getDtoFromEntity(RoleUsuarios entity) {
		RoleUsuariosDTO dto = new RoleUsuariosDTO();
		dto.setIdUsuario(entity.getIdUsuario());
		dto.setRoleId(entity.getRoleId());
		dto.setIdUsuarioRole(entity.getIdUsuarioRole());
		
		return dto;
	} 
	
	
	public static RoleUsuarios getEntityFromDto (RoleUsuariosDTO dto) {
		RoleUsuarios roleUsuarios = new RoleUsuarios();
		roleUsuarios.setIdUsuario(dto.getIdUsuario());
		roleUsuarios.setRoleId(dto.getRoleId());
		roleUsuarios.setIdUsuarioRole(dto.getIdUsuarioRole());
		return roleUsuarios;
		
	}

}
