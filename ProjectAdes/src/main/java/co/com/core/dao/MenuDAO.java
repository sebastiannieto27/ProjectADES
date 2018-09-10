package co.com.core.dao;

import java.util.List;

import co.com.core.domain.Menu;
import co.com.core.domain.RoleMenu;
import co.com.core.domain.RoleUsuarios;
import co.com.core.domain.Usuarios;

public interface MenuDAO {
	
	public List<RoleUsuarios> getUserRoles(Usuarios usuarios);
	
	public List<RoleMenu> getUserRoleMenu (List<RoleUsuarios> list);
	
	public List<Menu> getUserMenuOptions (List<RoleMenu> list);
	
	public List<Menu> getMenuGeneral();
	
	public List<Menu> getAll();
	
	public List<Menu> getNotAssignedMenu(String ids);
	
	public void create (Menu menu);
	 
	public void delete (Menu menu);
	
	public void update(Menu menu);

}
