package co.com.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName")
    , @NamedQuery(name = "Role.findByDescripcion", query = "SELECT r FROM Role r WHERE r.descripcion = :descripcion")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE_ID")
    private BigDecimal roleId;
    @Size(max = 100)
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<RoleUsuarios> roleUsuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<RoleMenu> roleMenuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<RolePermisos> rolePermisosCollection;

    public Role() {
    }

    public Role(BigDecimal roleId) {
        this.roleId = roleId;
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

    @XmlTransient
    public Collection<RoleUsuarios> getRoleUsuariosCollection() {
        return roleUsuariosCollection;
    }

    public void setRoleUsuariosCollection(Collection<RoleUsuarios> roleUsuariosCollection) {
        this.roleUsuariosCollection = roleUsuariosCollection;
    }

    @XmlTransient
    public Collection<RoleMenu> getRoleMenuCollection() {
        return roleMenuCollection;
    }

    public void setRoleMenuCollection(Collection<RoleMenu> roleMenuCollection) {
        this.roleMenuCollection = roleMenuCollection;
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
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Role[ roleId=" + roleId + " ]";
    }
    
}
