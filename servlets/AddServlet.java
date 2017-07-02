/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import util.*;

/**
 *
 * @author andu
 */
@WebServlet(name="AddServlet", urlPatterns={"/add"})
public class AddServlet extends HttpServlet {
    Util util;
    
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        util = new Util(request, response);
        util.outputHeader("Adauga intrare in agenda");

        formular(null, null);

        util.outputFooter();
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Hashtable valori = new Hashtable();
        Hashtable erori = new Hashtable();

        util = new Util(request, response);

        util.validateEmpty("nume", valori, erori);
        util.validateEmpty("prenume", valori, erori);
        util.validateEmpty("telmobil", valori, erori);
        util.validateEmpty("email", valori, erori);
        util.validateNone("adresa", valori);
        util.validateNone("oras", valori);
        util.validateNone("judet", valori);
        util.validateNone("cod", valori);
        util.validateNone("telfix", valori);


        if (erori.size() > 0) {
            // avem erori
            util.outputHeader("Adauga intrare in agenda");
            formular(valori, erori);
            util.outputFooter();
        }
        else {
            // adaugam intrarea in baza de date
            Persoana p = new Persoana(
                (String)valori.get("nume"),
                (String)valori.get("prenume"),
                (String)valori.get("telmobil"),
                (String)valori.get("telfix"),
                (String)valori.get("email"),
                (String)valori.get("adresa"),
                (String)valori.get("judet"),
                (String)valori.get("oras"),
                (String)valori.get("cod"));

            p.save();
            response.sendRedirect(response.encodeRedirectURL("list"));
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void formular(Hashtable valori, Hashtable erori) {
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
        util.output("<input type='text' name='nume' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("prenume");
        eroare = (String) erori.get("prenume");
        util.output("<label for='prenume'>Prenume:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='prenume' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("telmobil");
        eroare = (String) erori.get("telmobil");
        util.output("<label for='telmobil'>Telefon mobil:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='telmobil' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("telfix");
        util.output("<label for='telfix'>Telefon fix:</label>");
        util.output("<br />");
        util.output("<input type='text' name='telfix' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("email");
        eroare = (String) erori.get("email");
        util.output("<label for='email'>Email:*</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='email' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("adresa");
        eroare = (String) erori.get("adresa");
        util.output("<label for='adresa'>Adresa:</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<textarea name='adresa'>" + (valoare == null ? "" : valoare) + "</textarea>");
        util.output("<br />");

        valoare = (String) valori.get("oras");
        eroare = (String) erori.get("oras");
        util.output("<label for='oras'>Oras:</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='oras' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("judet");
        eroare = (String) erori.get("judet");
        util.output("<label for='judet'>Judet:</label>");
        util.output("<br />");
        if (eroare != null) {
            util.output("<span class='eroare'>" + eroare + "</span>");
            util.output("<br />");
        }
        util.output("<input type='text' name='judet' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        valoare = (String) valori.get("cod");
        eroare = (String) erori.get("cod");
        util.output("<label for='cod'>Cod postal:</label>");
        util.output("<br />");
        util.output("<input type='text' name='cod' value='" + (valoare == null ? "" : valoare) + "' />");
        util.output("<br />");

        util.output("<input type='submit' value='Adauga intrare' />");

        util.output("</form>");
    }

}
