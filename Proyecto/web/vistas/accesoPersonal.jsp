<%-- 
    Document   : accesoPersonal
    Created on : 31-may-2023, 17:27:02
    Author     : Iván
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/accesoPersonal.css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenid@ <s:property value="%{#session.nombre}"/></h1>

        <s:form method="POST" action="cerrarSesion">
            <s:submit name="boton" value="Cerrar Sesion"/>      
        </s:form>
        
        <s:form method="POST" action="/vistas/ajusteCuentas.jsp">
            <s:submit name="boton" value="Ajuste Cuentas"/>      
        </s:form>


        Nº Cuentas: <s:property value="%{#session.nCuentas}"/>
        <hr>
        Saldo Total: <s:property value="%{#session.saldo}"/>

        <h2>Lista de cuentas:</h2>

        <s:iterator value="#session.listaCuentas" var="cuenta">
            <table>
                <tr>
                    <th>Número de cuenta</th>
                    <td><s:property value="#cuenta.numCuenta"/></td>
                </tr>

                <tr>              
                    <th>Saldo</th>
                    <td><s:property value="#cuenta.saldo"/>€</td>
                </tr>
                <s:form action="guardarNumCuenta">
                    <s:hidden name="numCuenta" value="%{#cuenta.numCuenta}"/>
                    <s:submit value="Realizar Transferencia"/>
                </s:form> 

                <s:form method="POST" action="mostrarTransacciones">
                    <s:hidden name="numCuenta" value="%{#cuenta.numCuenta}"/>
                    <s:submit name="boton" value="Mostrar Transacciones"/>      
                </s:form>
                <hr>
            </table>
        </s:iterator>



    </body>
</html>
