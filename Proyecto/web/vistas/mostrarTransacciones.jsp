<%-- 
    Document   : mostrarTransacciones
    Created on : 31-may-2023, 19:25:24
    Author     : Iván
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mostrarTransacciones.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Transacciones Cuenta <s:property value="%{#session.numCuenta}"/></h1>
        
        <s:form action="/vistas/accesoPersonal.jsp">
            <s:submit value="Volver"/>
        </s:form>  
        
         <s:form action="imprimirTransacciones">
            <s:submit value="Guardar Transacciones en PDF"/>
        </s:form> 
        

        <s:iterator value="#session.listaTransicciones" var="transacciones">
            <table>
                <tr>
                    <th>ID Transaccion:</th>
                    <td><s:property value="#transacciones.iDTransaccion"/></td>
                </tr>
                
                <tr>              
                    <th>Num Cuenta Destino:</th>
                    <td><s:property value="#transacciones.numcuentadestino"/></td>
                </tr>
                
                <tr>              
                    <th>Cantidad:</th>
                    <td><s:property value="#transacciones.cantidad"/> €</td>
                </tr>
                
                <tr>              
                    <th>Fecha:</th>
                    <td><s:property value="#transacciones.fecha"/></td>
                </tr>
                
                <tr>              
                    <th>Descripción:</th>
                    <td><s:property value="#transacciones.descripción"/></td>
                </tr>

                <hr>
            </table>
        </s:iterator>


    </body>
</html>
