/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;

import modelo.Transacciones;
import org.apache.struts2.ServletActionContext;
import servicios.TransaccionesDAO;

/**
 *
 * @author Iván
 */
public class actionTransacciones extends ActionSupport {
    
    private static GenericType<List<Transacciones>> genericType = new GenericType<List<Transacciones>>() {};
    String numCuenta; 
    List<Transacciones> listaTransacciones = new ArrayList<>();
    List<Transacciones> list = new ArrayList<>();
    TransaccionesDAO transaccionesDAO = new TransaccionesDAO(); 
    
    

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
            System.out.println("DENTRO DEL BUCLE");
            
            
            if(list.get(i).getNumCuenta().getNumCuenta().equals(numCuenta)){
                
                System.out.println("Descripcionnnnn: "+list.get(i).getDescripción());
                
                listaTransacciones.add(list.get(i)); 
                
            }
            
        }
        System.out.println("NUM CUENTA: " + numCuenta);
        
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("listaTransicciones", listaTransacciones);
        request.getSession().setAttribute("numCuenta", numCuenta);
        
        return SUCCESS; 
    }
    
}
