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
    
    private static GenericType<List<Transacciones>> genericType = new GenericType<List<Transacciones>>() {};
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
        
        
        for(int i = 0; i<list.size(); i++){
            
            
            
            if(list.get(i).getNumCuenta().getNumCuenta().equals(numCuenta)){
                
                
                listaTransacciones.add(list.get(i)); 
                
            }
            
        }
        
        
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("listaTransicciones", listaTransacciones);
        request.getSession().setAttribute("numCuenta", numCuenta);
        
        return SUCCESS; 
    }
    
    public String guardarNumCuenta(){
        
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("numCuenta", numCuenta);
        
        return SUCCESS; 
    }
    
    
    public String crearTransaccion(){
        
        //PRUEBAS
        
        HttpServletRequest request = ServletActionContext.getRequest();
        numCuenta = (String) request.getSession().getAttribute("numCuenta");
        
        GenericType<Cuenta> genericType = new GenericType<Cuenta>() {};
        Cuenta cuenta = new Cuenta();
        
        CuentasDAO dao = new CuentasDAO(); 
        
        System.out.println("NUMCUENTA" + numCuenta);
        cuenta = dao.find_XML(genericType, numCuenta); 
        
        if(cuenta != null){
            
             System.out.println("ELEM CUENTA: numCuenta: "+ cuenta.getNumCuenta() + "NDNI cliente: " + cuenta.getDNICliente());
        }
       
        
        ////////////////////////////////////////////////
       
        Transacciones t = new Transacciones(); 
         
        System.out.println("N CUENTA DEST: " + CuentaDest);
        
        t.setNumcuentadestino(CuentaDest);
        
        
        int cantidadInt = Integer.parseInt(cantidad); 
        
        t.setCantidad(cantidadInt);
        t.setDescripción(descripcion);
        
        t.setNumCuenta(cuenta);
        
        LocalDate fechaActual = LocalDate.now();
        
        Date fechaActualDate = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        t.setFecha(fechaActualDate);
        
        TransaccionesDAO tranDAO = new TransaccionesDAO(); 
        
        int idTran = (int) Math.random()*100; 
        t.setIDTransaccion(idTran);
        
        System.out.println("Antes");
        tranDAO.create_XML(t);
        System.out.println("Despues");
        
        System.out.println("ID_TRANSICCION:" + t.getIDTransaccion() + " Num_cuenta: " + t.getNumCuenta().getNumCuenta() + " CANTIDAD: " + t.getCantidad() + " NUM Destino: " + t.getNumcuentadestino()
               + "FECHA: " + t.getFecha() + "DESCRIPCION: " + t.getDescripción()); 
        
        return SUCCESS; 
    }
   
    
}
