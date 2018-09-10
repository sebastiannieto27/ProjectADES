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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "VALOR_COLUMNA_DATAPOOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorColumnaDatapool.findAll", query = "SELECT v FROM ValorColumnaDatapool v")
    , @NamedQuery(name = "ValorColumnaDatapool.findByIdValorColumnaDatapool", query = "SELECT v FROM ValorColumnaDatapool v WHERE v.idValorColumnaDatapool = :idValorColumnaDatapool")
    , @NamedQuery(name = "ValorColumnaDatapool.findByValor", query = "SELECT v FROM ValorColumnaDatapool v WHERE v.valor = :valor")})
public class ValorColumnaDatapool implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_VALOR_COLUMNA_DATAPOOL")
    private BigDecimal idValorColumnaDatapool;
    @Size(max = 1000)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "ID_COLUMNA_DATAPOOL", referencedColumnName = "ID_COLUMNA_CASO")
    @ManyToOne(optional = false)
    private ColumnaDatapool idColumnaDatapool;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public ValorColumnaDatapool() {
    }

    public ValorColumnaDatapool(BigDecimal idValorColumnaDatapool) {
        this.idValorColumnaDatapool = idValorColumnaDatapool;
    }

    public BigDecimal getIdValorColumnaDatapool() {
        return idValorColumnaDatapool;
    }

    public void setIdValorColumnaDatapool(BigDecimal idValorColumnaDatapool) {
        this.idValorColumnaDatapool = idValorColumnaDatapool;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ColumnaDatapool getIdColumnaDatapool() {
        return idColumnaDatapool;
    }

    public void setIdColumnaDatapool(ColumnaDatapool idColumnaDatapool) {
        this.idColumnaDatapool = idColumnaDatapool;
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
        hash += (idValorColumnaDatapool != null ? idValorColumnaDatapool.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorColumnaDatapool)) {
            return false;
        }
        ValorColumnaDatapool other = (ValorColumnaDatapool) object;
        if ((this.idValorColumnaDatapool == null && other.idValorColumnaDatapool != null) || (this.idValorColumnaDatapool != null && !this.idValorColumnaDatapool.equals(other.idValorColumnaDatapool))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ValorColumnaDatapool[ idValorColumnaDatapool=" + idValorColumnaDatapool + " ]";
    }
    
}
