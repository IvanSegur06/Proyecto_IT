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
import java.io.FileNotFoundException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

        long saldo = cuenta.getSaldo();

        Transacciones t = new Transacciones();

        t.setNumcuentadestino(CuentaDest);

        int cantidadInt = Integer.parseInt(cantidad);

        int nuevoSaldo = (int) saldo - cantidadInt;

        cuenta.setSaldo(nuevoSaldo);

        CuentasDAO cuentaDAO = new CuentasDAO();

        cuentaDAO.edit_XML(cuenta, cuenta.getNumCuenta());

        t.setCantidad(cantidadInt);
        t.setDescripción(descripcion);

        t.setNumCuenta(cuenta);

        LocalDate fechaActual = LocalDate.now();

        Date fechaActualDate = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

        t.setFecha(fechaActualDate);

        TransaccionesDAO tranDAO = new TransaccionesDAO();

        t.setIDTransaccion(0);

        comprobarNumCuenta(CuentaDest, cantidadInt);

        tranDAO.create_XML(t);

        return SUCCESS;
    }

    public void comprobarNumCuenta(String numCuenta, int cantidadInt) {

        int saldo;

        GenericType<Cuenta> genericTypes = new GenericType<Cuenta>() {
        };

        CuentasDAO cuentaDAO = new CuentasDAO();

        Cuenta cuenta = new Cuenta();

        cuenta = cuentaDAO.find_XML(genericTypes, numCuenta);

        if (cuenta != null) {

            saldo = (int) cuenta.getSaldo();

            saldo += cantidadInt;

            cuenta.setSaldo(saldo);

            cuentaDAO.edit_XML(cuenta, numCuenta);

        }

    }

    public String imprimirTransacciones() throws FileNotFoundException {

        List<Transacciones> listTransacciones = new ArrayList<>();
        
        HttpServletRequest request = ServletActionContext.getRequest();
        numCuenta = (String) request.getSession().getAttribute("numCuenta");

        
        listTransacciones = (List<Transacciones>) request.getSession().getAttribute("listaTransicciones");

        // Crear el documento PDF
        Document document = new Document();

        try {
            // Crear el escritor PDF
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Iván/Documents/GitHub/Proyecto_IT/Proyecto/PDFs/MovimientosCuenta" +numCuenta + ".pdf"));


            // Abrir el documento
            document.open();

            // Iterar sobre la lista de transacciones y agregar cada una al documento
            for (Transacciones transaccion : listTransacciones) {
                String contenidoTransaccion = transaccion.toString(); // Asume que la clase Transacciones tiene un método toString() adecuado
                
                document.add(new Paragraph(contenidoTransaccion));
            }

            // Cerrar el documento
            document.close();
            System.out.println(System.getProperty("user.dir")); 
            System.out.println("PDF generado correctamente...");

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return SUCCESS; 

    }

}
