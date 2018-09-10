package co.com.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByMenuId", query = "SELECT m FROM Menu m WHERE m.menuId = :menuId")
    , @NamedQuery(name = "Menu.findByMenuName", query = "SELECT m FROM Menu m WHERE m.menuName = :menuName")
    , @NamedQuery(name = "Menu.findBySubmenu", query = "SELECT m FROM Menu m WHERE m.submenu = :submenu")
    , @NamedQuery(name = "Menu.findByGeneral", query = "SELECT m FROM Menu m WHERE m.general = :general")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_SEQ")
    @SequenceGenerator(sequenceName = "MENU_SEQ", allocationSize = 1, name = "MENU_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENU_ID")
    private BigDecimal menuId;
    @Size(max = 200)
    @Column(name = "MENU_NAME")
    private String menuName;
    @Column(name = "SUBMENU")
    private Short submenu;
    @Column(name = "GENERAL")
    private Short general;
    @OneToMany(mappedBy = "parentMenuId")
    private Collection<Menu> menuCollection;
    @JoinColumn(name = "PARENT_MENU_ID", referencedColumnName = "MENU_ID")
    @ManyToOne
    private Menu parentMenuId;
    @JoinColumn(name = "ID_PAGINA", referencedColumnName = "ID_PAGINA")
    @ManyToOne
    private Pagina idPagina;

    public Menu() {
    }

    public Menu(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public BigDecimal getMenuId() {
        return menuId;
    }

    public void setMenuId(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Short getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Short submenu) {
        this.submenu = submenu;
    }

    public Short getGeneral() {
        return general;
    }

    public void setGeneral(Short general) {
        this.general = general;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public Menu getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Menu parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Pagina getIdPagina() {
        return idPagina;
    }

    public void setIdPagina(Pagina idPagina) {
        this.idPagina = idPagina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menu[ menuId=" + menuId + " ]";
    }
    
}