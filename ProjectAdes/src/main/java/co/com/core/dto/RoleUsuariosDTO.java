package co.com.core.dto;

import java.math.BigDecimal;

import co.com.core.domain.Role;
import co.com.core.domain.Usuarios;

public class RoleUsuariosDTO {
	
    private BigDecimal idUsuarioRole;
    private Role roleId;
    private Usuarios idUsuario;
    
	public RoleUsuariosDTO() {
	}

	public RoleUsuariosDTO(BigDecimal idUsuarioRole, Role roleId, Usuarios idUsuario) {
		this.idUsuarioRole = idUsuarioRole;
		this.roleId = roleId;
		this.idUsuario = idUsuario;
	}

	public BigDecimal getIdUsuarioRole() {
		return idUsuarioRole;
	}

	public void setIdUsuarioRole(BigDecimal idUsuarioRole) {
		this.idUsuarioRole = idUsuarioRole;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Usuarios getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuarios idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "RoleUsuariosDTO [idUsuarioRole=" + idUsuarioRole + ", roleId=" + roleId + ", idUsuario=" + idUsuario
				+ "]";
	}
    
    
}
