<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: Fer
  Date: 27/01/2020
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar Usuario JSP</title>
</head>
<body>
<%
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/enformacion", "root", "");
        PreparedStatement stmt = conexion.prepareStatement("DELETE FROM usuarios WHERE id = ?");
        stmt.setString(1, request.getParameter("id"));
        if(stmt.executeUpdate() == 1){
%>
            <br> Usuario eliminado <br>
<%
        } else {
%>
            <br> No se ha podido eliminar <br>
<%
        }
        stmt.close();
        conexion.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }


%>
<a href="GimnasioJSP.jsp"><button>Volver</button></a>
</body>
</html>
