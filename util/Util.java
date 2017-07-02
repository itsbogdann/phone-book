
package util;

import java.io.*;
import javax.servlet.http.*;

import java.util.*;

public class Util {
    HttpServletResponse response;
    HttpServletRequest request;
    PrintWriter out;

    public Util(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.request = request;
            this.response = response;
            this.out = this.response.getWriter();
        }
        catch (Exception e) {
        }
    }

    public void outputHeader(String title) {
        response.setContentType("text/html;charset=UTF-8");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("<link rel='stylesheet' type='text/css' href='style.css' media='screen' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='header'>");
        out.println("<h1>Agenda</h1>");
        out.println("<div id='menu'>");
        out.println("<ul id='nav'>");
        out.println("<li><a href='list'>Lista</a></li>");
        out.println("<li><a href='add'>Adauga</a></li>");
        out.println("<li><a href='search'>Cautare</a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div id='content'>");
        out.println("<div id='right'>");
        out.println("<h2>" + title + "</h2>");
    }

    public void outputIntrare(Persoana p) {
        out.println("<h2>" + p.nume + " " + p.prenume + " " + p.telmobil + " " + p.telfix + " " + p.email + " " + p.adresa + " " + p.judet + " " + p.oras + " " + p.cod + "</h2>" );
        out.println("<p>Operatii: <a href='edit?id=" + p.id + "'>Modifica</a> | <a href='delete?id=" + p.id + "'>Sterge</a></p>");
    }

    public void outputFooter() {
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    public void output(String html) {
        out.println(html);
    }

    public void validateEmpty(String nume, Hashtable valori, Hashtable erori) {
        String valoare = request.getParameter(nume);
        if (!valoare.isEmpty()) {
            valori.put(nume, valoare);
        }
        else {
            erori.put(nume, "Acest camp trebuie completat. ");
        }
    }
    public void validateEmptymodif(String nume, Hashtable valori, Hashtable erori) {
        String valoare = request.getParameter(nume);
        if (!valoare.isEmpty()) {
            valori.put(nume, valoare);
        }
        else {
            erori.put(nume, "! Acest camp nu poate ramane necompletat. ");
        }
    }

    public void validateNone(String nume, Hashtable valori) {
        String valoare = request.getParameter(nume);
        if (!valoare.isEmpty()) {
            valori.put(nume, valoare);
        }
        else {
            valori.put(nume, "");
        }
    }
}
