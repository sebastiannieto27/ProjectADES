package co.com.core.services;

import java.util.List;

import co.com.core.dto.RoleUsuariosDTO;
import co.com.core.dto.UsuariosDTO;

public interface IRoleUsuariosService {
	
	public List<RoleUsuariosDTO> getAll();
	
	public void createUserRole(RoleUsuariosDTO userRoleDto);
	
	public void delete(RoleUsuariosDTO userRoleDto);
	
	public void update(RoleUsuariosDTO userRoleDto);
	
	public List<RoleUsuariosDTO> findByUser(UsuariosDTO dto);

}
