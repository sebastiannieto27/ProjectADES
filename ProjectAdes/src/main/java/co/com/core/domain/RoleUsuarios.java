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
@Table(name = "ROLE_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleUsuarios.findAll", query = "SELECT r FROM RoleUsuarios r")
    , @NamedQuery(name = "RoleUsuarios.findByIdUsuarioRole", query = "SELECT r FROM RoleUsuarios r WHERE r.idUsuarioRole = :idUsuarioRole")})
public class RoleUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_ROLE")
    private BigDecimal idUsuarioRole;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role roleId;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public RoleUsuarios() {
    }

    public RoleUsuarios(BigDecimal idUsuarioRole) {
        this.idUsuarioRole = idUsuarioRole;
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
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRole != null ? idUsuarioRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUsuarios)) {
            return false;
        }
        RoleUsuarios other = (RoleUsuarios) object;
        if ((this.idUsuarioRole == null && other.idUsuarioRole != null) || (this.idUsuarioRole != null && !this.idUsuarioRole.equals(other.idUsuarioRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoleUsuarios[ idUsuarioRole=" + idUsuarioRole + " ]";
    }
    
}
