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
@Table(name = "ROLE_PERMISOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolePermisos.findAll", query = "SELECT r FROM RolePermisos r")
    , @NamedQuery(name = "RolePermisos.findByRolePermisos", query = "SELECT r FROM RolePermisos r WHERE r.rolePermisos = :rolePermisos")})
public class RolePermisos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE_PERMISOS")
    private BigDecimal rolePermisos;
    @JoinColumn(name = "ID_PAGINA_PERMISOS", referencedColumnName = "ID_PAGINA_PERMISOS")
    @ManyToOne(optional = false)
    private PaginaPermiso idPaginaPermisos;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role roleId;

    public RolePermisos() {
    }

    public RolePermisos(BigDecimal rolePermisos) {
        this.rolePermisos = rolePermisos;
    }

    public BigDecimal getRolePermisos() {
        return rolePermisos;
    }

    public void setRolePermisos(BigDecimal rolePermisos) {
        this.rolePermisos = rolePermisos;
    }

    public PaginaPermiso getIdPaginaPermisos() {
        return idPaginaPermisos;
    }

    public void setIdPaginaPermisos(PaginaPermiso idPaginaPermisos) {
        this.idPaginaPermisos = idPaginaPermisos;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePermisos != null ? rolePermisos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermisos)) {
            return false;
        }
        RolePermisos other = (RolePermisos) object;
        if ((this.rolePermisos == null && other.rolePermisos != null) || (this.rolePermisos != null && !this.rolePermisos.equals(other.rolePermisos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RolePermisos[ rolePermisos=" + rolePermisos + " ]";
    }
    
}
