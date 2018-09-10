package co.com.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import co.com.core.commons.converter.PaginaUtil;
import co.com.core.dao.PaginaDAO;
import co.com.core.domain.Pagina;
import co.com.core.dto.PaginaDTO;
import co.com.core.services.IPaginaService;

public class PaginaServiceImpl implements IPaginaService{
	
	PaginaDAO paginaDao;
	private static final Logger logger = Logger.getLogger(PaginaServiceImpl.class);

	@Override
	public List<PaginaDTO> getAll() {
		List<PaginaDTO> dtos = new ArrayList<PaginaDTO>();
		List<Pagina> entity = paginaDao.getAll();
		
		if (entity != null && entity.size()>0) {
			for (Pagina pagina : entity) {
				dtos.add(PaginaUtil.getDtoFromEntity(pagina));
			}
		}
		return dtos;
	}

	@Override
	public void create(PaginaDTO dto) {
		 paginaDao.create(PaginaUtil.getEntityFromDto(dto));
		
	}

	@Override
	public void delete(PaginaDTO dto) {
		paginaDao.delete(PaginaUtil.getEntityFromDto(dto));
		
	}

	@Override
	public void update(PaginaDTO dto) {
          paginaDao.update(PaginaUtil.getEntityFromDto(dto));		
	}

	@Override
	public PaginaDTO getPageByUrl(String url) {
		   PaginaDTO dtos = null;
		   List<Pagina> entity = paginaDao.getPageByUrl(url);
		   
		   if (entity != null && entity.size() >0) {
			   for (Pagina pagina : entity) {
				dtos=  PaginaUtil.getDtoFromEntity(pagina);
			}
			
		} else {
			logger.info("Zero pages found related to : " + url);
		}
		   
		return dtos;
	}

	public PaginaDAO getPaginaDao() {
		return paginaDao;
	}

	public void setPaginaDao(PaginaDAO paginaDao) {
		this.paginaDao = paginaDao;
	}
	
	

}
