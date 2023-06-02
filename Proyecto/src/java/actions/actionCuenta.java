/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import modelo.Cliente;
import modelo.Cuenta;
import org.apache.struts2.ServletActionContext;
import servicios.CuentasDAO;

/**
 *
 * @author Iván
 */
public class actionCuenta extends ActionSupport {
    
    
    private static GenericType<List<Cuenta>> genericType = new GenericType<List<Cuenta>>() {};
    private static CuentasDAO cuentaDAO = new CuentasDAO(); 
    private Cuenta cuenta = new Cuenta(); 
    private List<Cuenta> listaCuentas = new ArrayList<>(); 
    private List<Cuenta> list = new ArrayList<>(); 
    
    public actionCuenta() {
    }
    
    
    
    
    public String execute() throws Exception {
        Cliente cliente = new Cliente(); 
        HttpServletRequest request = ServletActionContext.getRequest();
        cliente = (Cliente) request.getSession().getAttribute("cliente"); 
        double saldo = 0; 
        
        
        list = cuentaDAO.findAll_XML(genericType); 
        
        for (int i = 0; i < list.size(); i++ ){
            
            if(list.get(i).getDNICliente().getDni().equals(cliente.getDni())){
                
                listaCuentas.add(list.get(i)); 
                saldo += list.get(i).getSaldo(); 
                
            }
            
        }
        
        request.getSession().setAttribute("nCuentas", listaCuentas.size());
        request.getSession().setAttribute("saldo", saldo);
        request.getSession().setAttribute("listaCuentas", listaCuentas);
        
        
        return SUCCESS; 
    }
    
    
    public String crearCuenta(){
        
        Cliente cliente = new Cliente(); 
        HttpServletRequest request = ServletActionContext.getRequest();
        cliente = (Cliente) request.getSession().getAttribute("cliente"); 
        
        Cuenta cuenta = new Cuenta(); 
        
        cuenta.setDNICliente(cliente);
        
       Random random = new Random();

        int numeroAleatorio = random.nextInt(1000000);

        String cadenaAleatoria = String.valueOf(numeroAleatorio);
        
        cuenta.setNumCuenta(cadenaAleatoria);
        
        cuenta.setSaldo(0);
        
        CuentasDAO cuentaDao = new CuentasDAO(); 
        
        cuentaDao.create_XML(cuenta);
        
        return SUCCESS; 
    }
    
}
