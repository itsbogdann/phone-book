
package servlets;
import util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet(name="SearchServlet", urlPatterns={"/search"})
public class SearchServlet extends HttpServlet {
   
  
    Util util;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        util = new Util(request, response);
        util.outputHeader("Cauta o persoana in agenda");

        formcautare(null, null);

        util.outputFooter();
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Hashtable valori = new Hashtable();
        Hashtable erori = new Hashtable();

        util = new Util(request, response);
       // response.getWriter().println("hello");
         Persoana p = new Persoana();
        ArrayList persoane =
                p.searchlist((String)request.getParameter("nume"),request.getParameter("prenume"),request.getParameter("adresa"));
        util.outputHeader("Cautare");
               
        
        for( int i=0; i< persoane.size(); i++) {
            util.outputIntrare((Persoana) persoane.get(i));
        }
        util.outputFooter();
     }
 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
public void formcautare(Hashtable valori, Hashtable erori){
    String valoare, eroare;
 if (valori == null) {
     valori = new Hashtable();
        }
 if (erori == null) {
     erori = new Hashtable();
        }

 util.output("<form method='post' action=''>");
 valoare = (String) valori.get("nume");
        //eroare = (String) erori.get("nume");
 util.output("<label for='nume'>Nume:</label>");
        util.output("<br />");
 util.output("<input type='text' name='nume' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");
  valoare = (String) valori.get("prenume");
  util.output("<label for='nume'>Prenume:</label>");
        util.output("<br />");
  util.output("<input type='text' name='prenume' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");
  valoare = (String) valori.get("adresa");
  util.output("<label for='nume'>Email:</label>");
        util.output("<br />");
  util.output("<input type='text' name='adresa' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

 util.output("<input type='submit' value='Cauta persoana' />");
 util.output("</form>");
}
}
