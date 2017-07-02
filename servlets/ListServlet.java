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
import util.Persoana;
import util.Util;

@WebServlet(name="ListServlet", urlPatterns={"/list"})
public class ListServlet extends HttpServlet {
    Util util;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        util = new Util(request, response);
        util.outputHeader("Lista intrarilor");

        Persoana p = new Persoana();
        ArrayList persoane = p.lista();

        for( int i=0; i< persoane.size(); i++) {
            util.outputIntrare((Persoana) persoane.get(i));
        }

        util.outputFooter();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
