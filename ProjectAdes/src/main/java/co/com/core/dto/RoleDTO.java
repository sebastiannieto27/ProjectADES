package co.com.core.dto;

import java.math.BigDecimal;

public class RoleDTO {
	
    private BigDecimal roleId;
    private String roleName;
    private String descripcion;
    
	public RoleDTO() {
	}
	
	

	public RoleDTO(BigDecimal roleId, String roleName, String descripcion) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.descripcion = descripcion;
	}



	public BigDecimal getRoleId() {
		return roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", roleName=" + roleName + ", descripcion=" + descripcion + "]";
	}



}
