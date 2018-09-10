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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "PAGINA_PERMISO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaginaPermiso.findAll", query = "SELECT p FROM PaginaPermiso p")
    , @NamedQuery(name = "PaginaPermiso.findByIdPaginaPermisos", query = "SELECT p FROM PaginaPermiso p WHERE p.idPaginaPermisos = :idPaginaPermisos")})
public class PaginaPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAGINA_PERMISOS")
    private BigDecimal idPaginaPermisos;
    @JoinColumn(name = "ID_PAGINA", referencedColumnName = "ID_PAGINA")
    @ManyToOne(optional = false)
    private Pagina idPagina;
    @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID_PERMISO")
    @ManyToOne(optional = false)
    private Permiso idPermiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaginaPermisos")
    private Collection<RolePermisos> rolePermisosCollection;

    public PaginaPermiso() {
    }

    public PaginaPermiso(BigDecimal idPaginaPermisos) {
        this.idPaginaPermisos = idPaginaPermisos;
    }

    public BigDecimal getIdPaginaPermisos() {
        return idPaginaPermisos;
    }

    public void setIdPaginaPermisos(BigDecimal idPaginaPermisos) {
        this.idPaginaPermisos = idPaginaPermisos;
    }

    public Pagina getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(Pagina idPagina) {
        this.idPagina = idPagina;
    }

    public Permiso getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permiso idPermiso) {
        this.idPermiso = idPermiso;
    }

    @XmlTransient
    public Collection<RolePermisos> getRolePermisosCollection() {
        return rolePermisosCollection;
    }

    public void setRolePermisosCollection(Collection<RolePermisos> rolePermisosCollection) {
        this.rolePermisosCollection = rolePermisosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaginaPermisos != null ? idPaginaPermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaginaPermiso)) {
            return false;
        }
        PaginaPermiso other = (PaginaPermiso) object;
        if ((this.idPaginaPermisos == null && other.idPaginaPermisos != null) || (this.idPaginaPermisos != null && !this.idPaginaPermisos.equals(other.idPaginaPermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PaginaPermiso[ idPaginaPermisos=" + idPaginaPermisos + " ]";
    }
    
}
