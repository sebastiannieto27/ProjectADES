package co.com.core.dao;

import java.util.List;

import co.com.core.domain.Pagina;

public interface PaginaDAO {

	
	public List<Pagina> getAll();
	
	public void create (Pagina pagina);
	
	public void delete (Pagina pagina);
	
	public void update (Pagina pagina);
	
	public List<Pagina> getPageByUrl(String url);
	
	
}
