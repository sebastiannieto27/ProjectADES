package co.com.core.services;

import java.util.List;

import co.com.core.dto.UsuariosDTO;

public interface IUsuariosService {
	
	public UsuariosDTO getUserById(Integer userId);
	public List<UsuariosDTO> getAll(); 	
	public UsuariosDTO login(String userEmail, String password);
	public void createUser(UsuariosDTO UserDto);
	public void delete(UsuariosDTO UserDto);
	public void update(UsuariosDTO UserDto);
	public UsuariosDTO getByMail(String userEmail);
	public List<UsuariosDTO> getUserByName(String name); 



	

}
