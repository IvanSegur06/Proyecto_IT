<%-- 
    Document   : transferencia
    Created on : 31-may-2023, 19:24:45
    Author     : Iván
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/transferencia.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>TRANSFERENCIA DESDE CUENTA <s:property value="%{#session.numCuenta}"/></h1>
        
        <s:form method="POST" action="crearTransaccion">
            <s:textfield name="CuentaDest" label="Nº Cuenta destino" value="" />
            <s:textfield name="cantidad" label="Cantidad" value="" />
            <s:textfield name="descripcion" label="Descripción" value="" />
            
            <s:submit value="Realizar transferencia" />
        </s:form>
        
    </body>
</html>
