package co.com.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "PAGINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagina.findAll", query = "SELECT p FROM Pagina p")
    , @NamedQuery(name = "Pagina.findByIdPagina", query = "SELECT p FROM Pagina p WHERE p.idPagina = :idPagina")
    , @NamedQuery(name = "Pagina.findByPaginaName", query = "SELECT p FROM Pagina p WHERE p.paginaName = :paginaName")
    , @NamedQuery(name = "Pagina.findByUrl", query = "SELECT p FROM Pagina p WHERE p.url = :url")
    , @NamedQuery(name = "Pagina.findByRealUrl", query = "SELECT p FROM Pagina p WHERE p.realUrl = :realUrl")})
public class Pagina implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGINA_SEQ")
    @SequenceGenerator(sequenceName = "PAGINA_SEQ", allocationSize = 1, name = "PAGINA_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAGINA")
    private BigDecimal idPagina;
    @Size(max = 300)
    @Column(name = "PAGINA_NAME")
    private String paginaName;
    @Size(max = 200)
    @Column(name = "URL")
    private String url;
    @Size(max = 200)
    @Column(name = "REAL_URL")
    private String realUrl;

    public Pagina() {
    }

    public Pagina(BigDecimal idPagina) {
        this.idPagina = idPagina;
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
    public int hashCode() {
        int hash = 0;
        hash += (idPagina != null ? idPagina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagina)) {
            return false;
        }
        Pagina other = (Pagina) object;
        if ((this.idPagina == null && other.idPagina != null) || (this.idPagina != null && !this.idPagina.equals(other.idPagina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pagina[ idPagina=" + idPagina + " ]";
    }
    
}
