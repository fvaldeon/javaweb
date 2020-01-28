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
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.Hashtable;

@WebServlet(name = "CrearSession")
public class CrearSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Elimino sesión previa
        HttpSession miSesion= request.getSession(false);
        if(miSesion != null) {
            miSesion.invalidate();
        }

        //Creo nueva sesion
        miSesion= request.getSession(true);
        PrintWriter pw= response.getWriter();
        pw.println("<html><body>");
        pw.println("Datos previos de sesion:<br>");

        Enumeration<String> enumeracion = miSesion.getAttributeNames();
        while(enumeracion.hasMoreElements()){
            String nombreAtributo= enumeracion.nextElement();
            Object valor = miSesion.getAttribute(nombreAtributo);
            pw.println("dato: " + nombreAtributo + ", valor: " + valor + "<br>");
        }
        pw.println("<br><br>");

        //Crear e introducir dato en sesion
        int entero = 20;
        double real = 20.20;
        String texto = "hola mundo";
        LocalDateTime fecha = LocalDateTime.now();
        Hashtable<String, String> semaforo = new Hashtable<>();
        semaforo.put("Fer", "123415R");
        semaforo.put("Juan", "24456G");
        Punto punto = new Punto(20, 50);

        miSesion.setAttribute("entero", entero);
        miSesion.setAttribute("real", real);
        miSesion.setAttribute("texto", texto);
        miSesion.setAttribute("fecha", fecha);
        miSesion.setAttribute("semaforo", semaforo);
        miSesion.setAttribute("punto",punto);

        pw.println("Datos actuales en sesion:<br>");

        enumeracion = miSesion.getAttributeNames();
        while(enumeracion.hasMoreElements()){
            String nombreAtributo= enumeracion.nextElement();
            Object valor = miSesion.getAttribute(nombreAtributo);
            pw.println("dato: " + nombreAtributo + ", valor: " + valor + "<br>");
        }
        pw.println("<br><br>");

        pw.println("<a href=\"VerSession\">Comprobar datos de session</a>");
        pw.println();
        pw.println("</body></html>");
        pw.close();
    }
}
