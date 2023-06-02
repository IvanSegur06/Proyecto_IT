<%-- 
    Document   : transferencia
    Created on : 31-may-2023, 19:24:45
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
        <h1>TRANSFERENCIA DESDE CUENTA <s:property value="%{#session.numCuenta}"/></h1>
        
        <s:form method="POST" action="">
            <s:textfield name="nombre" label="Nº Cuenta destino" />
            <s:textfield name="apellidos" label="Cantidad" />
            <s:textfield name="DNI" label="Descripción" />
            
            <s:submit value="Realizar transferencia" />
        </s:form>
        
    </body>
</html>
