/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Iván
 */
public class comprobarLogin extends ActionSupport {
    
    private String DNI;
    private String password; 
    
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
        
        if(getDNI().equals("PRUEBA")){
            return "correcto"; 
        }
        else{
            return SUCCESS; 
        }
        
    }
    
}
