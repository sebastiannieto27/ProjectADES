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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.nieto
 */
@Entity
@Table(name = "COLUMNA_DATAPOOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColumnaDatapool.findAll", query = "SELECT c FROM ColumnaDatapool c")
    , @NamedQuery(name = "ColumnaDatapool.findByIdColumnaCaso", query = "SELECT c FROM ColumnaDatapool c WHERE c.idColumnaCaso = :idColumnaCaso")
    , @NamedQuery(name = "ColumnaDatapool.findByNombreColumna", query = "SELECT c FROM ColumnaDatapool c WHERE c.nombreColumna = :nombreColumna")
    , @NamedQuery(name = "ColumnaDatapool.findByDescripcion", query = "SELECT c FROM ColumnaDatapool c WHERE c.descripcion = :descripcion")})
public class ColumnaDatapool implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLUMNA_CASO")
    private BigDecimal idColumnaCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_COLUMNA")
    private String nombreColumna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_CASO", referencedColumnName = "ID_CASO")
    @ManyToOne(optional = false)
    private CasoPrueba idCaso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColumnaDatapool")
    private Collection<ValorColumnaDatapool> valorColumnaDatapoolCollection;

    public ColumnaDatapool() {
    }

    public ColumnaDatapool(BigDecimal idColumnaCaso) {
        this.idColumnaCaso = idColumnaCaso;
    }

    public ColumnaDatapool(BigDecimal idColumnaCaso, String nombreColumna, String descripcion) {
        this.idColumnaCaso = idColumnaCaso;
        this.nombreColumna = nombreColumna;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdColumnaCaso() {
        return idColumnaCaso;
    }

    public void setIdColumnaCaso(BigDecimal idColumnaCaso) {
        this.idColumnaCaso = idColumnaCaso;
    }

    public String getNombreColumna() {
        return nombreColumna;
    }

    public void setNombreColumna(String nombreColumna) {
        this.nombreColumna = nombreColumna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CasoPrueba getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(CasoPrueba idCaso) {
        this.idCaso = idCaso;
    }

    @XmlTransient
    public Collection<ValorColumnaDatapool> getValorColumnaDatapoolCollection() {
        return valorColumnaDatapoolCollection;
    }

    public void setValorColumnaDatapoolCollection(Collection<ValorColumnaDatapool> valorColumnaDatapoolCollection) {
        this.valorColumnaDatapoolCollection = valorColumnaDatapoolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColumnaCaso != null ? idColumnaCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColumnaDatapool)) {
            return false;
        }
        ColumnaDatapool other = (ColumnaDatapool) object;
        if ((this.idColumnaCaso == null && other.idColumnaCaso != null) || (this.idColumnaCaso != null && !this.idColumnaCaso.equals(other.idColumnaCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ColumnaDatapool[ idColumnaCaso=" + idColumnaCaso + " ]";
    }
    
}
