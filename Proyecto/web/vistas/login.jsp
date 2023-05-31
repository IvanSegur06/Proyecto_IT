<%-- 
    Document   : login
    Created on : 31-may-2023, 15:38:12
    Author     : Iván
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <s:form method="POST" action="comprobarLogin">
            <s:textfield name="DNI" label="DNI" value=""/>
            <s:textfield name="password" label="Contraseña" value=""/>
            
            <s:submit name="boton" value="Login"/>
        </s:form>
        
        <s:form method="POST" action="registro.jsp">
            <s:submit name="boton" value="Registrarse"/>         
        </s:form>
        
        
    </body>
</html>
