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
@Table(name = "ROLE_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleMenu.findAll", query = "SELECT r FROM RoleMenu r"),
     @NamedQuery(name = "RoleMenu.findByRoleId", query = "SELECT r FROM RoleMenu r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "RoleMenu.findByIdRoleMeu", query = "SELECT r FROM RoleMenu r WHERE r.idRoleMeu = :idRoleMeu")})
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLE_MEU")
    private BigDecimal idRoleMeu;
    @JoinColumn(name = "MENU_ID", referencedColumnName = "MENU_ID")
    @ManyToOne(optional = false)
    private Menu menuId;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role roleId;

    public RoleMenu() {
    }

    public RoleMenu(BigDecimal idRoleMeu) {
        this.idRoleMeu = idRoleMeu;
    }

    public BigDecimal getIdRoleMeu() {
        return idRoleMeu;
    }

    public void setIdRoleMeu(BigDecimal idRoleMeu) {
        this.idRoleMeu = idRoleMeu;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
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
        hash += (idRoleMeu != null ? idRoleMeu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleMenu)) {
            return false;
        }
        RoleMenu other = (RoleMenu) object;
        if ((this.idRoleMeu == null && other.idRoleMeu != null) || (this.idRoleMeu != null && !this.idRoleMeu.equals(other.idRoleMeu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoleMenu[ idRoleMeu=" + idRoleMeu + " ]";
    }
    
}
