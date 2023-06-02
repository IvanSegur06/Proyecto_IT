<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h1>Registro de Usuario</h1>
              
        <s:form method="POST" action="nuevoRegistro">
            <s:textfield name="nombre" label="Nombre" />
            <s:textfield name="apellidos" label="Apellidos" />
            <s:textfield name="DNI" label="DNI" />
            <s:textfield name="email" label="Email" />
            <s:textfield name="telefono" label="Teléfono" />
            <s:password name="password" label="Contraseña" />
            <s:submit value="Confirmar Registro" />
        </s:form>
    </body>
</html>
