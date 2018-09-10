package co.com.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "CASO_PRUEBA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoPrueba.findAll", query = "SELECT c FROM CasoPrueba c")
    , @NamedQuery(name = "CasoPrueba.findByIdCaso", query = "SELECT c FROM CasoPrueba c WHERE c.idCaso = :idCaso")
    , @NamedQuery(name = "CasoPrueba.findByNombreCaso", query = "SELECT c FROM CasoPrueba c WHERE c.nombreCaso = :nombreCaso")
    , @NamedQuery(name = "CasoPrueba.findByDescripcion", query = "SELECT c FROM CasoPrueba c WHERE c.descripcion = :descripcion")})
public class CasoPrueba implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CASO")
    private BigDecimal idCaso;
    @Size(max = 100)
    @Column(name = "NOMBRE_CASO")
    private String nombreCaso;
    @Size(max = 300)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinTable(name = "USUARIO_CASO", joinColumns = {
        @JoinColumn(name = "ID_CASO", referencedColumnName = "ID_CASO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private Collection<Usuarios> usuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaso")
    private Collection<ColumnaDatapool> columnaDatapoolCollection;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;

    public CasoPrueba() {
    }

    public CasoPrueba(BigDecimal idCaso) {
        this.idCaso = idCaso;
    }

    public BigDecimal getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(BigDecimal idCaso) {
        this.idCaso = idCaso;
    }

    public String getNombreCaso() {
        return nombreCaso;
    }

    public void setNombreCaso(String nombreCaso) {
        this.nombreCaso = nombreCaso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @XmlTransient
    public Collection<ColumnaDatapool> getColumnaDatapoolCollection() {
        return columnaDatapoolCollection;
    }

    public void setColumnaDatapoolCollection(Collection<ColumnaDatapool> columnaDatapoolCollection) {
        this.columnaDatapoolCollection = columnaDatapoolCollection;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaso != null ? idCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoPrueba)) {
            return false;
        }
        CasoPrueba other = (CasoPrueba) object;
        if ((this.idCaso == null && other.idCaso != null) || (this.idCaso != null && !this.idCaso.equals(other.idCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CasoPrueba[ idCaso=" + idCaso + " ]";
    }
    
}
