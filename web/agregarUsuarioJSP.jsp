<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Fer
  Date: 27/01/2020
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar usuario JSP</title>
</head>
<body>
<%
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/enformacion", "root", "");
        PreparedStatement stmt = conexion.prepareStatement("INSERT INTO usuarios (nombre, apellidos, nif, telefono, id_taquilla) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, request.getParameter("nombre"));
        stmt.setString(2, request.getParameter("apellidos"));
        stmt.setString(3, request.getParameter("nif"));
        stmt.setString(4, request.getParameter("telefono"));
        stmt.setString(5, request.getParameter("taquilla"));
        if(stmt.executeUpdate() == 1){
%>
            <br> Usuario agregado <br>
<%
        } else {
%>
            <br> No se ha podido agregar <br>
<%
        }
        stmt.close();
        conexion.close();
    } catch (SQLException  e) {
        e.printStackTrace();
    } catch ( ClassNotFoundException e1 ){
        e1.printStackTrace();
    }

%>
<a href="GimnasioJSP.jsp"><button>Volver</button></a>
</body>
</html>
