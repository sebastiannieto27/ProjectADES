package co.com.core.services;

import java.util.List;

import co.com.core.domain.Usuarios;
import co.com.core.dto.MenuDTO;
import co.com.core.dto.RoleUsuariosDTO;
import co.com.core.dto.UsuariosDTO;

public interface IMenuService {

	
	public List<MenuDTO> getUserMenu(Usuarios usuarios);
	
	public List<MenuDTO> getGeneral();
	
	public List<MenuDTO> getAll();
	
	public List<RoleUsuariosDTO> getUserRoles (UsuariosDTO dto);
	
	public List<MenuDTO> getNotAssignedMenu  (String ids);
	
	public void create (MenuDTO dto);
	
	public void delete (MenuDTO dto);
	
	public void update (MenuDTO dto);
	
	
}
