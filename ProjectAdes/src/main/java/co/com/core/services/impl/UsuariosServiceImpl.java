package co.com.core.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import co.com.core.commons.converter.UsuariosUtil;
import co.com.core.dao.UsuariosDAO;
import co.com.core.domain.Usuarios;
import co.com.core.dto.UsuariosDTO;
import co.com.core.services.IUsuariosService;

public class UsuariosServiceImpl implements IUsuariosService  {
	
	UsuariosDAO usuarioDao;

	@Override
	public UsuariosDTO getUserById(Integer userId) {
		Usuarios usuarios = usuarioDao.getUserById(userId);
		UsuariosDTO dto = UsuariosUtil.getDtoFromEntity(usuarios);
		return dto;
	}

	@Override
	public List<UsuariosDTO> getAll() {
		List<UsuariosDTO> dtos = new ArrayList<UsuariosDTO>();
		List<Usuarios> entity = usuarioDao.getAll();
		
		if (entity != null && entity.size()>0) {
			for (Usuarios usuarios : entity) {
				dtos.add(UsuariosUtil.getDtoFromEntity(usuarios));
			}
			
		}

		return dtos;
	}

	@Override
	public void createUser(UsuariosDTO UserDto) {
          usuarioDao.createUser(UsuariosUtil.getEntityFromDto(UserDto));		
	}

	@Override
	public void delete(UsuariosDTO UserDto) {
		usuarioDao.delete(UsuariosUtil.getEntityFromDto(UserDto));
		
	}

	@Override
	public void update(UsuariosDTO UserDto) {
		usuarioDao.update(UsuariosUtil.getEntityFromDto(UserDto));
		
	}

	@Override
	public UsuariosDTO getByMail(String userEmail) {
		UsuariosDTO dto = null;
		Usuarios usuarios = usuarioDao.getByMail(userEmail);
		if (usuarios != null) {
			dto = UsuariosUtil.getDtoFromEntity(usuarios);
		}
		return dto;
	}

	@Override
	public List<UsuariosDTO> getUserByName(String name) {
		List<UsuariosDTO> dtos = null;
		List<Usuarios> entity = usuarioDao.getUserByName(name);
		
		if (entity != null) {
			dtos= new ArrayList<UsuariosDTO>();
			for (Usuarios usuarios : entity) {
				dtos.add(UsuariosUtil.getDtoFromEntity(usuarios));
			}
			
		}
		return dtos;
	}

	@Override
	public UsuariosDTO login(String userEmail, String password) {
		UsuariosDTO dto = null;
		Usuarios usuarios = usuarioDao.login(userEmail, password);
		if (usuarios != null) {
			System.out.println("No es Nulo");
			dto = UsuariosUtil.getDtoFromEntity(usuarios);
			
		}
		return dto;
	}

	public UsuariosDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuariosDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	

}
