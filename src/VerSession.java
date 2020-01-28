import auxiliar.Punto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Hashtable;

@WebServlet(name = "VerSession")
public class VerSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession miSesion = (HttpSession) request.getSession();

        int entero = (int) miSesion.getAttribute("entero");
        double real = (double) miSesion.getAttribute("real");
        String texto = (String) miSesion.getAttribute("texto");
        LocalDateTime fecha = (LocalDateTime) miSesion.getAttribute("fecha");
        Hashtable<String, String> semaforo = (Hashtable<String, String>) miSesion.getAttribute("semaforo");
        Punto punto = (Punto) miSesion.getAttribute("punto");

        PrintWriter pw= response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='css/servletstyle.css'>";
        pw.println("<html>");
        pw.println("<head><title>VerSession</title>"+cssTag+"</head>");

        pw.println("<body><table id='tabla-estilo'>");

        pw.println("<tr>");
        pw.println("<th>entero</th>");
        pw.println("<td>" + entero + "</td>");
        pw.println("</tr>");

        pw.println("<tr>");
        pw.println("<th>real</th>");
        pw.println("<td>" + real + "</td>");
        pw.println("</tr>");

        pw.println("<tr>");
        pw.println("<th>texto</th>");
        pw.println("<td>" + texto + "</td>");
        pw.println("</tr>");


        pw.println("<tr>");
        pw.println("<th>fecha</th>");
        pw.println("<td>" + fecha + "</td>");
        pw.println("</tr>");

        pw.println("<tr>");
        pw.println("<th>semaforo</th>");
        pw.println("<td>");
        pw.println("Fer: " + semaforo.get("Fer"));
        pw.println("Juan: " + semaforo.get("Juan"));
        pw.println("</td>");
        pw.println("</tr>");

        pw.println("<tr>");
        pw.println("<th>punto</th>");
        pw.println("<td>x=" + punto.getX() + " y=" + punto.getY() + "</td>");
        pw.println("</tr>");

        pw.println("</table></body></html>");
        pw.close();
    }
}
