package co.com.core.commons.converter;

import co.com.core.domain.Pagina;
import co.com.core.dto.PaginaDTO;

public class PaginaUtil {
	
	
	public static PaginaDTO getDtoFromEntity(Pagina pagina) {
		   PaginaDTO dto = new PaginaDTO();
		   dto.setRealUrl(pagina.getRealUrl());
		   dto.setUrl(pagina.getUrl());
		   dto.setPaginaName(pagina.getPaginaName());
		   dto.setIdPagina(pagina.getIdPagina());
		   return dto;
		
	}
	
	public static Pagina getEntityFromDto(PaginaDTO dto) {
		  Pagina pagina = new Pagina();
		  pagina.setRealUrl(dto.getRealUrl());
		  pagina.setUrl(dto.getUrl());
		  pagina.setPaginaName(dto.getPaginaName());
		  pagina.setIdPagina(dto.getIdPagina());
		  
		  return pagina;
	}

}
