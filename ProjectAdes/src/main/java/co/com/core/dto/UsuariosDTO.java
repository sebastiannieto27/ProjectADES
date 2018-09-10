package co.com.core.dto;

import java.math.BigDecimal;

public class UsuariosDTO {
	
	private BigDecimal idUsuario;
	private String nombreUsuario;
	private String nombreCompleto;
	private String correo;
	private String password;
    private String idnumber;
    private Short active;
    private Boolean isActive;
    private String creationDate;
    private Short accountlocked;

	
	
	public UsuariosDTO() {
	}






	public BigDecimal getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombreUsuario() {
		return nombreUsuario;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public String getNombreCompleto() {
		return nombreCompleto;
	}



	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getIdnumber() {
		return idnumber;
	}



	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}



	public Short getActive() {
		return active;
	}



	public void setActive(Short active) {
		this.active = active;
	}



	public String getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}



	public Short getAccountlocked() {
		return accountlocked;
	}



	public void setAccountlocked(Short accountlocked) {
		this.accountlocked = accountlocked;
	}

	
	




	public Boolean getIsActive() {
		return isActive;
	}






	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}






	@Override
	public String toString() {
		return "UsuariosDTO [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", nombreCompleto="
				+ nombreCompleto + ", correo=" + correo + ", password=" + password + ", idnumber=" + idnumber
				+ ", active=" + active + ", creationDate=" + creationDate + ", accountlocked=" + accountlocked + "]";
	}

	


}
