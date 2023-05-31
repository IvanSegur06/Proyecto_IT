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
public class actionCliente extends ActionSupport {
    
    private static ClientesDAO clienteDAO = new ClientesDAO(); 
    
    String nombre, apellidos, DNI, email, telefono, password; 
    
    public actionCliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public String crearCliente(){
        
        Cliente cliente = new Cliente(); 
        
        cliente.setNombre(this.nombre);
        cliente.setApellidos(this.apellidos);
        cliente.setDni(this.DNI);
        cliente.setEmail(this.email);
        cliente.setTeléfono(this.telefono);
        cliente.setPassword(this.password);
        
        clienteDAO.create_XML(cliente);
        
        return SUCCESS; 
        
    }
    
    
}
