import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DataBaseServlet")
public class DataBaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String tipoconsulta = request.getParameter("tipoConsulta");
        PrintWriter escritor = response.getWriter();
        Connection conexion = conectar();
        String cssTag="<link rel='stylesheet' type='text/css' href='css/servletstyle.css'>";
        escritor.println("<html>");
        escritor.println("<head><title>Usuarios</title>"+cssTag+"</head>");
        if(tipoconsulta.equals("todos")){

            try {
                Statement stmt = conexion.createStatement();
                ResultSet resultado = stmt.executeQuery("SELECT * FROM usuarios");
                escritor.println("<body><table id='tabla-estilo'>");
                escritor.println("<tr><th>Id</th><th>Codigo</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Ciudad</th><th>Fecha</th><th>Descuento</th></tr>");
                while(resultado.next()){
                    escritor.println("<tr>");
                    escritor.println("<td>" + resultado.getObject(1) + "</td>");
                    escritor.println("<td>" + resultado.getObject(2) + "</td>");
                    escritor.println("<td>" + resultado.getObject(3) + "</td>");
                    escritor.println("<td>" + resultado.getObject(4) + "</td>");
                    escritor.println("<td>" + resultado.getObject(5) + "</td>");
                    escritor.println("<td>" + resultado.getObject(6) + "</td>");
                    escritor.println("<td>" + resultado.getObject(7) + "</td>");
                    escritor.println("<td>" + resultado.getObject(8) + "</td>");

                    escritor.println("</tr>");
                }
                escritor.println("</table></body>");

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            String nombre = request.getParameter("nombre");

            try {

                PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre = ?");

                stmt.setString(1, nombre);
                ResultSet resultado = stmt.executeQuery();

                escritor.println("<body><table id='tabla-estilo'>");
                escritor.println("<tr><th>Id</th><th>Codigo</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Ciudad</th><th>Fecha</th><th>Descuento</th></tr>");
                while(resultado.next()){
                    escritor.println("<tr>");
                    escritor.println("<td>" + resultado.getString(1) + "</td>");
                    escritor.println("<td>" + resultado.getString(2) + "</td>");
                    escritor.println("<td>" + resultado.getString(3) + "</td>");
                    escritor.println("<td>" + resultado.getString(4) + "</td>");
                    escritor.println("<td>" + resultado.getString(5) + "</td>");
                    escritor.println("<td>" + resultado.getString(6) + "</td>");
                    escritor.println("<td>" + resultado.getString(7) + "</td>");
                    escritor.println("<td>" + resultado.getString(8) + "</td>");

                    escritor.println("</tr>");
                }
                escritor.println("</table></body>");
                resultado.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        escritor.println("</html>");
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public Connection conectar(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas?zeroDateTimeBehavior=convertToNull", "root","");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conexion;
    }
}
