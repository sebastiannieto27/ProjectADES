package co.com.core.dao;

import java.util.List;

import co.com.core.domain.RoleUsuarios;
import co.com.core.domain.Usuarios;

public interface RoleUsuariosDAO {
	
	public List<RoleUsuarios> getAll();
	
    public void createUserRole(RoleUsuarios userRole);
	
	public void delete(RoleUsuarios userRole);
	
	public void update(RoleUsuarios userRole);
	
	public List<RoleUsuarios> findByUser(Usuarios user);

}
