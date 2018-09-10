package co.com.core.services;

import java.util.List;

import co.com.core.dto.PaginaDTO;

public interface IPaginaService {

	
	public List<PaginaDTO> getAll();
	
	public void create (PaginaDTO dto);
	
	public void delete (PaginaDTO dto);
	
	public void update (PaginaDTO dto);
	
	public PaginaDTO getPageByUrl (String url); 
	
}
