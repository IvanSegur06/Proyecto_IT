<%-- 
    Document   : ajusteCuentas
    Created on : 31-may-2023, 19:25:42
    Author     : Iván
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ajusteCuentas.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ajuste de su acceso personalizado <s:property value="%{#session.nombre}"/></h1>

        <s:form method="POST" action="crearCuenta">
            <s:submit name="boton" value="Abrir cuenta"/>      
        </s:form>

     


        <s:iterator value="#session.listaCuentas" var="cuenta">
            <table>
                <tr>
                    <th>Número de cuenta</th>
                    <td><s:property value="#cuenta.numCuenta"/></td>
                </tr>

                <tr>              
                    <th>Saldo</th>
                    <td><s:property value="#cuenta.saldo"/></td>
                </tr>
                <s:form method="POST" action="eliminacionCuenta">
                    <s:hidden name="numCuenta" value="%{#cuenta.numCuenta}"/>
                    <s:submit name="boton" value="Eliminar Cuenta"/>      
                </s:form>
                <hr>
            </table>
        </s:iterator>

    </body>
</html>
