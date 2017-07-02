/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import util.*;
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adelina
 */
@WebServlet(name="ActualServlet", urlPatterns={"/edit"})
public class ActualServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
Util util;
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        util = new Util(request, response);
        util.outputHeader("Modifica intrare");
        String id = (String)request.getParameter("id");
        //util.output("id:"+id);
        int idd = Integer.parseInt(id);
       Persoana a=new Persoana();
        a.load(idd);
        formmodifica(a,null, null);

        util.outputFooter();
    }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      util = new Util(request, response);
       // util.outputHeader("Modifica");
        String id = (String)request.getParameter("id");
       // util.output("id:"+id);
        int idd = Integer.parseInt(id);
        Persoana a=new Persoana();
        a.load(idd);
        

        Hashtable valori = new Hashtable();
        Hashtable erori = new Hashtable();

        util = new Util(request, response);

        util.validateEmptymodif("nume", valori, erori);
        util.validateEmptymodif("prenume", valori, erori);
        util.validateEmptymodif("telmobil", valori, erori);
        util.validateEmptymodif("email", valori, erori);
        util.validateNone("adresa", valori);
        util.validateNone("oras", valori);
        util.validateNone("judet", valori);
        util.validateNone("cod", valori);
        util.validateNone("telfix", valori);


        if (erori.size() > 0) {
            // avem erori
            util.outputHeader("Adauga intrare in agenda");
            formmodifica(a,valori,erori);
            util.outputFooter();
        }
        else {
            // adaugam intrarea in baza de date

            a.nume=(String)valori.get("nume");
            a.prenume=(String)valori.get("prenume");
            a.telmobil=(String)valori.get("telmobil");
            a.telfix=(String)valori.get("telfix");
            a.email=(String)valori.get("email");
            a.adresa=(String)valori.get("adresa");
            a.judet=(String)valori.get("judet");
            a.oras=(String)valori.get("oras");
            a.cod=(String)valori.get("cod");

            a.modifica();
            response.sendRedirect(response.encodeRedirectURL("list"));
        }
    
       // a.load(idd);
        //a.modifica();
        util.outputFooter();
 }
     
  public void formmodifica(Persoana p, Hashtable valori, Hashtable erori) {
        String valoare, eroare;
           if (valori == null) {
            valori = new Hashtable();
        }
        if (erori == null) {
            erori = new Hashtable();
        }
        

        util.output("<form method='post' action=''>");

        valoare = (String) valori.get("nume");
        eroare = (String) erori.get("nume");
        util.output("<label for='nume'>Nume:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='nume' value='"+ p.nume + "' />");
        util.output("<br />");

        valoare = (String) valori.get("prenume");
        eroare = (String) erori.get("prenume");
        util.output("<label for='prenume'>Prenume:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='prenume' value='" + p.prenume + "' />");
        util.output("<br />");

        valoare = (String) valori.get("telmobil");
        eroare = (String) erori.get("telmobil");
        util.output("<label for='telmobil'>Telefon mobil:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='telmobil' value='" + p.telmobil + "' />");
        util.output("<br />");

        valoare = (String) valori.get("telfix");
        util.output("<label for='telfix'>Telefon fix:</label>");
        util.output("<br />");
        util.output("<input type='text' name='telfix' value='" + p.telfix + "' />");
        util.output("<br />");

        valoare = (String) valori.get("email");
        eroare = (String) erori.get("email");
        util.output("<label for='email'>Email:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='email' value='" + p.email + "' />");
        util.output("<br />");

        valoare = (String) valori.get("adresa");
        eroare = (String) erori.get("adresa");
        util.output("<label for='adresa'>Adresa:</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<textarea name='adresa'>" + p.adresa + "</textarea>");
        util.output("<br />");

        valoare = (String) valori.get("oras");
        eroare = (String) erori.get("oras");
        util.output("<label for='oras'>Oras:</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='oras' value='" + p.oras + "' />");
        util.output("<br />");

        valoare = (String) valori.get("judet");
        eroare = (String) erori.get("judet");
        util.output("<label for='judet'>Judet:</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='judet' value='" + p.judet + "' />");
        util.output("<br />");

        valoare = (String) valori.get("cod");
        eroare = (String) erori.get("cod");
        util.output("<label for='cod'>Cod postal:</label>");
        util.output("<br />");
        util.output("<input type='text' name='cod' value='" + p.cod + "' />");
        util.output("<br />");

        util.output("<input type='submit' value='Modifica intrare' />");

        util.output("</form>");
    }

}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

