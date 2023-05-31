/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.ws.rs.core.GenericType;
import modelo.Cliente;
import servicios.ClientesDAO;

/**
 *
 * @author Iván
 */
public class comprobarLogin extends ActionSupport {
    
    private String DNI = null;
    private String password = null; 
    private static GenericType<Cliente> genericType = new GenericType<Cliente>() {};
    private static ClientesDAO clienteDAO = new ClientesDAO(); 
    
    public comprobarLogin() {
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
    
    public String execute() throws Exception {
        
        Cliente cliente = (Cliente) clienteDAO.find_XML(genericType, this.getDNI()); 
        
        if(cliente  != null && password.equals(cliente.getPassword())){
            return SUCCESS; 
        }else{
            return ERROR; 
        }
        
        
        
    }
    
}
