package co.com.core.lazy.sorter;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import co.com.core.dto.UsuariosDTO;

public class UsuarioLazySorter implements Comparator<UsuariosDTO>   {
	
	
	private String sortField;
	private SortOrder sortOrder;
	

	public UsuarioLazySorter() {
	}




	public UsuarioLazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}




	@Override
	public int compare(UsuariosDTO o1, UsuariosDTO o2) {
		try {
			
			Object value1 = UsuariosDTO.class.getField(this.sortField).get(o1);
			Object value2 = UsuariosDTO.class.getField(this.sortField).get(o2);
			
			int value = ((Comparable)value1).compareTo(value2);
			
			return SortOrder.ASCENDING.equals(this.sortOrder) ? value : -1 * value;
			
		} catch (Exception e) {
			 throw new RuntimeException();
			 }
	}
	

	
	
	

}
