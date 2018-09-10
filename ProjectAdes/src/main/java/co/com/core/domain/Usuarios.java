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
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuarios.findByNombreUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuarios.findByNombreCompleto", query = "SELECT u FROM Usuarios u WHERE u.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password")
    , @NamedQuery(name = "Usuarios.findByIdNumber", query = "SELECT u FROM Usuarios u WHERE u.idNumber = :idNumber")
    , @NamedQuery(name = "Usuarios.findByActive", query = "SELECT u FROM Usuarios u WHERE u.active = :active")
    , @NamedQuery(name = "Usuarios.findByCreationDate", query = "SELECT u FROM Usuarios u WHERE u.creationDate = :creationDate")
    , @NamedQuery(name = "Usuarios.findByAccountlocked", query = "SELECT u FROM Usuarios u WHERE u.accountlocked = :accountlocked")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_SEQ")
    @SequenceGenerator(sequenceName = "USUARIOS_SEQ", allocationSize = 1, name = "USUARIOS_SEQ")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private BigDecimal idUsuario;
    @Size(max = 200)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Size(max = 400)
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Size(max = 400)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 50)
    @Column(name = "ID_NUMBER")
    private String idNumber;
    @Column(name = "ACTIVE")
    private Short active;
    @Size(max = 100)
    @Column(name = "CREATION_DATE")
    private String creationDate;
    @Column(name = "ACCOUNTLOCKED")
    private Short accountlocked;

    public Usuarios() {
    }

    public Usuarios(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Short getAccountlocked() {
        return accountlocked;
    }

    public void setAccountlocked(Short accountlocked) {
        this.accountlocked = accountlocked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}

