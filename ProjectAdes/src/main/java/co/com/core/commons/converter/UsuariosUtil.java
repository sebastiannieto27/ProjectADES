package co.com.core.commons.converter;

import javax.persistence.Entity;

import co.com.core.domain.Usuarios;
import co.com.core.dto.UsuariosDTO;

public class UsuariosUtil {
	
	public static UsuariosDTO getDtoFromEntity(Usuarios entity) {
		
		UsuariosDTO dto = new UsuariosDTO();
		dto.setCorreo(entity.getCorreo());
		dto.setNombreCompleto(entity.getNombreCompleto());
		dto.setNombreUsuario(entity.getNombreUsuario());
		dto.setPassword(entity.getPassword());
		dto.setIdnumber(entity.getIdNumber());
		dto.setCreationDate(entity.getCreationDate());
		dto.setActive(entity.getActive());
		dto.setAccountlocked(entity.getAccountlocked());
		dto.setIdUsuario(entity.getIdUsuario());
		
		if (entity.getActive() == 1)
			dto.setIsActive(true);
		else
			dto.setIsActive(false);
				
		
		return dto;
		
	}
	
	
	public static Usuarios getEntityFromDto(UsuariosDTO dto) {
		Usuarios usuarios = new Usuarios();
		usuarios.setCorreo(dto.getCorreo());
		usuarios.setNombreCompleto(dto.getNombreCompleto());
		usuarios.setNombreUsuario(dto.getNombreUsuario());
		usuarios.setPassword(dto.getPassword());
		usuarios.setIdNumber(dto.getIdnumber());
		usuarios.setCreationDate(dto.getCreationDate());
		usuarios.setActive(dto.getActive());
		usuarios.setAccountlocked(dto.getAccountlocked());
		usuarios.setIdUsuario(dto.getIdUsuario());
		
		if(dto.getIsActive())
			usuarios.setActive(Short.parseShort("1"));
		else
			usuarios.setActive(Short.parseShort("0"));
		
		
		return usuarios;
		
		
		
		
	}

}
