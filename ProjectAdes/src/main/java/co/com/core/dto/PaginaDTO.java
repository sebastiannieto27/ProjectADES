package co.com.core.dto;

import java.math.BigDecimal;

public class PaginaDTO {

    private BigDecimal idPagina;
    private String paginaName;
    private String url;
    private String realUrl;

    
	public PaginaDTO() {
	}



	public PaginaDTO(BigDecimal idPagina, String paginaName, String url, String realUrl) {
		this.idPagina = idPagina;
		this.paginaName = paginaName;
		this.url = url;
		this.realUrl = realUrl;
	}



	public BigDecimal getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(BigDecimal idPagina) {
		this.idPagina = idPagina;
	}

	public String getPaginaName() {
		return paginaName;
	}

	public void setPaginaName(String paginaName) {
		this.paginaName = paginaName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public String getRealUrl() {
		return realUrl;
	}



	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}



	@Override
	public String toString() {
		return "PaginaDTO [idPagina=" + idPagina + ", paginaName=" + paginaName + ", url=" + url + ", realUrl="
				+ realUrl + "]";
	}


	
	
    
    
	
	
	
	
}
