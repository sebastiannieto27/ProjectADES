package co.com.core.lazy.loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import co.com.core.dto.UsuariosDTO;
import co.com.core.lazy.sorter.UsuarioLazySorter;
import co.com.core.services.IUsuariosService;

public class UsuarioLazyLoader extends LazyDataModel<UsuariosDTO> {
	
	private List<UsuariosDTO> datasource;
	
	public UsuarioLazyLoader(IUsuariosService usuariosService) {
		this.datasource = usuariosService.getAll();
	
	}
	
	@Override
	public UsuariosDTO getRowData(String key) {
		for (UsuariosDTO usuariosDTO : datasource) {
			if (usuariosDTO.getIdUsuario().equals(key)) {
				return usuariosDTO;
			}
			
		}
		
		return null;
		
	}
	
	
	@Override
	public Object getRowKey(UsuariosDTO dto) {
		return dto.getIdUsuario();
	}
	
	@Override
	public List<UsuariosDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters){
		 List<UsuariosDTO> listUsuarios = new ArrayList<UsuariosDTO>();
		
		for (UsuariosDTO usuariosDTO : datasource) {
			boolean match = true;
			if (filters != null) {
				for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filtersValue =  filters.get(filterProperty);
						String fieldValue = String.valueOf(usuariosDTO.getClass().getField(filterProperty).get(usuariosDTO));
						
						if (filtersValue == null || fieldValue.startsWith(filtersValue.toString())) {
							match = true;
						}else {
							match = false;
							break;
						}
		
					} catch (Exception e) {
						match = false;
					}
					
				}
				
			}
			
			if (match) {
			  listUsuarios.add(usuariosDTO);	
			}
	
		}
		
		if (sortField != null) {
			Collections.sort(listUsuarios, new UsuarioLazySorter(sortField, sortOrder));
			
			
		}
		
		int dataSize = listUsuarios.size();
		this.setRowCount(dataSize);
		
		
		if (dataSize > pageSize) {
			
			try {
				
				return listUsuarios.subList(first, first + pageSize);
				
			} catch (Exception e) {
				return listUsuarios.subList(first, first + (dataSize % pageSize )); 
			}
			
		}else {
			return listUsuarios;
		}
		
	
		
		
	}
	
}
