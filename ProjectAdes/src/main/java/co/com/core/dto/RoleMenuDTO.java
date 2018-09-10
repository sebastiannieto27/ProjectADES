package co.com.core.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import co.com.core.domain.Menu;
import co.com.core.domain.Role;

public class RoleMenuDTO {
	
    private BigDecimal idRoleMeu;
    private Menu menuId;
    private Role roleId;
    
	public RoleMenuDTO() {
	}

	public RoleMenuDTO(BigDecimal idRoleMeu, Menu menuId, Role roleId) {
		this.idRoleMeu = idRoleMeu;
		this.menuId = menuId;
		this.roleId = roleId;
	}

	public BigDecimal getIdRoleMeu() {
		return idRoleMeu;
	}

	public void setIdRoleMeu(BigDecimal idRoleMeu) {
		this.idRoleMeu = idRoleMeu;
	}

	public Menu getMenuId() {
		return menuId;
	}

	public void setMenuId(Menu menuId) {
		this.menuId = menuId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "RoleMenuDTO [idRoleMeu=" + idRoleMeu + ", menuId=" + menuId + ", roleId=" + roleId + "]";
	}


   
    


}
