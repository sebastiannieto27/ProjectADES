package co.com.core.dao;

import java.util.List;

import co.com.core.domain.Usuarios;

public interface UsuariosDAO {
	
	public  Usuarios getUserById(Integer userID);
	
	public List<Usuarios> getAll();
	
	public Usuarios login(String userEmail, String userPassword);
	
	public void createUser(Usuarios user);
	
	public void delete(Usuarios user);
	
	public void update(Usuarios user);
	
	public Usuarios getByMail(String userEmail);
	
	public List<Usuarios> getUserByName(String name);
	

}
