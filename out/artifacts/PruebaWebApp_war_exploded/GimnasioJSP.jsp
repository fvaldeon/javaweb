<%--
  Created by IntelliJ IDEA.
  User: Fer
  Date: 27/01/2020
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*"%>
<html>
<head>
    <link rel="stylesheet" href="css/servletstyle.css" type="text/css">
    <title>JSP Gimnasio</title>
</head>
<body style="text-align: center">
<h3>Listar Usuarios con JSP</h3>
<br>
<table id="tabla-estilo">
    <tr>
        <th>id</th>
        <th>nif</th>
        <th>nombre</th>
        <th>apellidos</th>
        <th>telefono</th>
        <th>taquilla</th>
        <th></th>
    </tr>
<%
    try {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/enformacion", "root", "");
        Statement stmt = conexion.createStatement();
        ResultSet resultado = stmt.executeQuery("SELECT * FROM usuarios");

        while(resultado.next()){
%>


    <tr>
        <td><%=resultado.getObject(1)%></td>
        <td><%=resultado.getObject(2)%></td>
        <td><%=resultado.getObject(3)%></td>
        <td><%=resultado.getObject(4)%></td>
        <td><%=resultado.getObject(5)%></td>
        <td><%=resultado.getObject(6)%></td>
        <td>
            <a href="eliminarUsuarioJSP.jsp?id=<%=resultado.getObject(1)%>"><input type="button" value="Eliminar"></a>
        </td>
    </tr>
    <% }

        conexion.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }


%>

    <caption>Listado de usuarios</caption>
</table>
<br>
<hr>
<form action="agregarUsuarioJSP.jsp" method="post">
    <fieldset style="text-align: left">
        <legend>Datos de usuario</legend>
        <table>
            <tr><td><label for="nombre">Nombre</label></td>
                <td><input type="text" id="nombre" name="nombre" placeholder="Introduzca nombre" required></td>
            </tr>
            <tr><td><label for="apellidos">Apellidos</label></td>
                <td><input type="text" id="apellidos" name="apellidos" placeholder="Introduzca apellidos" required></td></tr>
            <tr><td><label for="nif">Nif</label></td>
                <td><input type="text" id="nif" name="nif" placeholder="Introduzca nif" required></td></tr>
            <tr><td><label for="telefono">Teléfono</label></td>
                <td><input type="text" id="telefono" name="telefono" placeholder="Introduzca telefono" required></td></tr>
            <tr><td><label for="taquilla">Taquilla</label></td>
                <td><input type="text" id="taquilla" name="taquilla" placeholder="Introduzca taquilla" required></td></tr>
            <tr><td><input type="submit" value="Registrar"></td></tr>
        </table>
    </fieldset>


</form>
</body>
</html>
