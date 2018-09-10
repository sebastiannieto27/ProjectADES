package co.com.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "REGISTRO_EJECUCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroEjecucion.findAll", query = "SELECT r FROM RegistroEjecucion r")
    , @NamedQuery(name = "RegistroEjecucion.findByIdRegistroEjecucion", query = "SELECT r FROM RegistroEjecucion r WHERE r.idRegistroEjecucion = :idRegistroEjecucion")
    , @NamedQuery(name = "RegistroEjecucion.findByFechaRegistro", query = "SELECT r FROM RegistroEjecucion r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RegistroEjecucion.findByIdCaso", query = "SELECT r FROM RegistroEjecucion r WHERE r.idCaso = :idCaso")
    , @NamedQuery(name = "RegistroEjecucion.findByRutaEvidencias", query = "SELECT r FROM RegistroEjecucion r WHERE r.rutaEvidencias = :rutaEvidencias")})
public class RegistroEjecucion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGISTRO_EJECUCION")
    private BigDecimal idRegistroEjecucion;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CASO")
    private BigInteger idCaso;
    @Size(max = 1500)
    @Column(name = "RUTA_EVIDENCIAS")
    private String rutaEvidencias;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public RegistroEjecucion() {
    }

    public RegistroEjecucion(BigDecimal idRegistroEjecucion) {
        this.idRegistroEjecucion = idRegistroEjecucion;
    }

    public RegistroEjecucion(BigDecimal idRegistroEjecucion, BigInteger idCaso) {
        this.idRegistroEjecucion = idRegistroEjecucion;
        this.idCaso = idCaso;
    }

    public BigDecimal getIdRegistroEjecucion() {
        return idRegistroEjecucion;
    }

    public void setIdRegistroEjecucion(BigDecimal idRegistroEjecucion) {
        this.idRegistroEjecucion = idRegistroEjecucion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigInteger getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(BigInteger idCaso) {
        this.idCaso = idCaso;
    }

    public String getRutaEvidencias() {
        return rutaEvidencias;
    }

    public void setRutaEvidencias(String rutaEvidencias) {
        this.rutaEvidencias = rutaEvidencias;
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
        hash += (idRegistroEjecucion != null ? idRegistroEjecucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroEjecucion)) {
            return false;
        }
        RegistroEjecucion other = (RegistroEjecucion) object;
        if ((this.idRegistroEjecucion == null && other.idRegistroEjecucion != null) || (this.idRegistroEjecucion != null && !this.idRegistroEjecucion.equals(other.idRegistroEjecucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RegistroEjecucion[ idRegistroEjecucion=" + idRegistroEjecucion + " ]";
    }
    
}
