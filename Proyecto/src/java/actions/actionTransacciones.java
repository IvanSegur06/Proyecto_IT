/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import modelo.Cliente;
import modelo.Cuenta;

import modelo.Transacciones;
import org.apache.struts2.ServletActionContext;
import servicios.CuentasDAO;
import servicios.TransaccionesDAO;

/**
 *
 * @author Iván
 */
public class actionTransacciones extends ActionSupport {

    private static GenericType<List<Transacciones>> genericType = new GenericType<List<Transacciones>>() {
    };
    String numCuenta, CuentaDest, cantidad, descripcion;
    List<Transacciones> listaTransacciones = new ArrayList<>();
    List<Transacciones> list = new ArrayList<>();
    TransaccionesDAO transaccionesDAO = new TransaccionesDAO();

    public String getCuentaDest() {
        return CuentaDest;
    }

    public void setCuentaDest(String CuentaDest) {
        this.CuentaDest = CuentaDest;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public actionTransacciones() {
    }

    public String execute() throws Exception {

        list = (List<Transacciones>) transaccionesDAO.findAll_XML(genericType);

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getNumCuenta().getNumCuenta().equals(numCuenta)) {

                listaTransacciones.add(list.get(i));

            }

        }

        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("listaTransicciones", listaTransacciones);
        request.getSession().setAttribute("numCuenta", numCuenta);

        return SUCCESS;
    }

    public String guardarNumCuenta() {

        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("numCuenta", numCuenta);

        return SUCCESS;
    }

    public String crearTransaccion() {

        HttpServletRequest request = ServletActionContext.getRequest();
        numCuenta = (String) request.getSession().getAttribute("numCuenta");

        GenericType<Cuenta> genericType = new GenericType<Cuenta>() {
        };
        Cuenta cuenta = new Cuenta();

        CuentasDAO dao = new CuentasDAO();

        cuenta = dao.find_XML(genericType, numCuenta);

        Transacciones t = new Transacciones();

        t.setNumcuentadestino(CuentaDest);

        int cantidadInt = Integer.parseInt(cantidad);

        t.setCantidad(cantidadInt);
        t.setDescripción(descripcion);

        t.setNumCuenta(cuenta);

        LocalDate fechaActual = LocalDate.now();

        Date fechaActualDate = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

        t.setFecha(fechaActualDate);

        TransaccionesDAO tranDAO = new TransaccionesDAO();

        t.setIDTransaccion(0);

        tranDAO.create_XML(t);

        return SUCCESS;
    }

}
