/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iván
 */
@Entity
@Table(name = "transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transacciones.findAll", query = "SELECT t FROM Transacciones t")
    , @NamedQuery(name = "Transacciones.findByIDTransaccion", query = "SELECT t FROM Transacciones t WHERE t.iDTransaccion = :iDTransaccion")
    , @NamedQuery(name = "Transacciones.findByCantidad", query = "SELECT t FROM Transacciones t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "Transacciones.findByNumcuentadestino", query = "SELECT t FROM Transacciones t WHERE t.numcuentadestino = :numcuentadestino")
    , @NamedQuery(name = "Transacciones.findByFecha", query = "SELECT t FROM Transacciones t WHERE t.fecha = :fecha")})
public class Transacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Transaccion")
    private Integer iDTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad")
    private long cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Num_cuenta_destino")
    private String numcuentadestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "Num_Cuenta", referencedColumnName = "Num_Cuenta")
    @ManyToOne(optional = false)
    private Cuenta numCuenta;

    public Transacciones() {
    }

    public Transacciones(Integer iDTransaccion) {
        this.iDTransaccion = iDTransaccion;
    }

    public Transacciones(Integer iDTransaccion, long cantidad, String numcuentadestino, Date fecha, String descripcion) {
        this.iDTransaccion = iDTransaccion;
        this.cantidad = cantidad;
        this.numcuentadestino = numcuentadestino;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Integer getIDTransaccion() {
        return iDTransaccion;
    }

    public void setIDTransaccion(Integer iDTransaccion) {
        this.iDTransaccion = iDTransaccion;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumcuentadestino() {
        return numcuentadestino;
    }

    public void setNumcuentadestino(String numcuentadestino) {
        this.numcuentadestino = numcuentadestino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripción() {
        return descripcion;
    }

    public void setDescripción(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuenta getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(Cuenta numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTransaccion != null ? iDTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transacciones)) {
            return false;
        }
        Transacciones other = (Transacciones) object;
        if ((this.iDTransaccion == null && other.iDTransaccion != null) || (this.iDTransaccion != null && !this.iDTransaccion.equals(other.iDTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = dateFormat.format(fecha);
    
    return "ID_Transaccion: " + iDTransaccion + "\nNº Cuenta Dest: " + numcuentadestino +
           "\nCantidad: " + cantidad + "€\nFecha: " + formattedDate +
           "\nDescripcion: " + descripcion + "\n\n";
}
    
}
