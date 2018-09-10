package co.com.core.commons.converter;

import co.com.core.domain.Menu;
import co.com.core.dto.MenuDTO;

public class MenuUtil {
	
	public static MenuDTO getDtoFromEntity(Menu entity) {
		
		MenuDTO dto =  new MenuDTO();
		dto.setGeneral(entity.getGeneral());
		dto.setIdPagina(entity.getIdPagina());
		dto.setMenuName(entity.getMenuName());
		dto.setParentMenuId(entity.getParentMenuId());
		dto.setSubmenu(entity.getSubmenu());
		dto.setMenuId(entity.getMenuId());
		
		// valida lo que esta en el Entity 1 , 0.
		if(entity.getSubmenu()==1 ) 
			dto.setIsSelectedSubMenu(true);
		else 
			dto.setIsSelectedSubMenu(false);
		
		if(entity.getGeneral()!=null) {
			if (entity.getGeneral() == 1)
				dto.setIsSelectedGeneral(true);
			else
				dto.setIsSelectedGeneral(false);
		}else {
			dto.setIsSelectedGeneral(false);
		}
      
		return dto;
		
	}
	
	public static Menu getEntityFromDto(MenuDTO dto) {
		Menu menu = new Menu();
		menu.setGeneral(dto.getGeneral());
		menu.setIdPagina(dto.getIdPagina());
		menu.setMenuName(dto.getMenuName());
		menu.setParentMenuId(dto.getParentMenuId());
		menu.setSubmenu(dto.getSubmenu());
		menu.setMenuId(dto.getMenuId());
		
		//Valida lo que viene del dto, False o True
		if(dto.getIsSelectedSubMenu()) 
			menu.setSubmenu(Short.parseShort("1"));
		else 
			menu.setSubmenu(Short.parseShort("0"));
		
		if(dto.getIsSelectedGeneral())
			menu.setGeneral(Short.parseShort("1"));
		else
		menu.setGeneral(Short.parseShort("0"));
		
		
		return menu;
		
		
	}
	

}
