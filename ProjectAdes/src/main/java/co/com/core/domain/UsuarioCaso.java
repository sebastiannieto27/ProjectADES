package co.com.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "USUARIO_CASO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioCaso.findAll", query = "SELECT u FROM UsuarioCaso u")
    , @NamedQuery(name = "UsuarioCaso.findByIdUsuarioCaso", query = "SELECT u FROM UsuarioCaso u WHERE u.idUsuarioCaso = :idUsuarioCaso")})
public class UsuarioCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_CASO")
    private BigDecimal idUsuarioCaso;
    @JoinColumn(name = "ID_CASO", referencedColumnName = "ID_CASO")
    @ManyToOne(optional = false)
    private CasoPrueba idCaso;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public UsuarioCaso() {
    }

    public UsuarioCaso(BigDecimal idUsuarioCaso) {
        this.idUsuarioCaso = idUsuarioCaso;
    }

    public BigDecimal getIdUsuarioCaso() {
        return idUsuarioCaso;
    }

    public void setIdUsuarioCaso(BigDecimal idUsuarioCaso) {
        this.idUsuarioCaso = idUsuarioCaso;
    }

    public CasoPrueba getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(CasoPrueba idCaso) {
        this.idCaso = idCaso;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioCaso != null ? idUsuarioCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioCaso)) {
            return false;
        }
        UsuarioCaso other = (UsuarioCaso) object;
        if ((this.idUsuarioCaso == null && other.idUsuarioCaso != null) || (this.idUsuarioCaso != null && !this.idUsuarioCaso.equals(other.idUsuarioCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UsuarioCaso[ idUsuarioCaso=" + idUsuarioCaso + " ]";
    }
    
}