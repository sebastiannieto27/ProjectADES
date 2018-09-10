package co.com.core.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import co.com.core.domain.Menu;
import co.com.core.domain.Pagina;

public class MenuDTO {
	
    private BigDecimal menuId;
    private String menuName;
    private Short submenu;
    private Menu parentMenuId;
    private Short general;
    private Pagina idPagina;
    private Boolean isSelectedSubMenu;
    private Boolean isSelectedGeneral;
    
	public MenuDTO() {
	}


	public MenuDTO(BigDecimal menuId, String menuName, Short submenu, Menu parentMenuId, Short general,
			Pagina idPagina) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.submenu = submenu;
		this.parentMenuId = parentMenuId;
		this.general = general;
		this.idPagina = idPagina;
	}


	public BigDecimal getMenuId() {
		return menuId;
	}

	public void setMenuId(BigDecimal menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	
	public Short getSubmenu() {
		return submenu;
	}




	public void setSubmenu(Short submenu) {
		this.submenu = submenu;
	}




	public Short getGeneral() {
		return general;
	}




	public void setGeneral(Short general) {
		this.general = general;
	}






	public Menu getParentMenuId() {
		return parentMenuId;
	}


	public void setParentMenuId(Menu parentMenuId) {
		this.parentMenuId = parentMenuId;
	}


	public Pagina getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Pagina idPagina) {
		this.idPagina = idPagina;
	}

	public Boolean getIsSelectedSubMenu() {
		return isSelectedSubMenu;
	}


	public void setIsSelectedSubMenu(Boolean isSelectedSubMenu) {
		this.isSelectedSubMenu = isSelectedSubMenu;
	}


	public Boolean getIsSelectedGeneral() {
		return isSelectedGeneral;
	}


	public void setIsSelectedGeneral(Boolean isSelectedGeneral) {
		this.isSelectedGeneral = isSelectedGeneral;
	}


	@Override
	public String toString() {
		return "MenuDTO [menuId=" + menuId + ", menuName=" + menuName + ", submenu=" + submenu + ", parentMenuId="
				+ parentMenuId + ", general=" + general + ", idPagina=" + idPagina + "]";
	}
    
    


}
