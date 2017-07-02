

package servlets;
import util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Persoana;
import util.Util;


@WebServlet(name="DelServlet", urlPatterns={"/delete"})
public class DelServlet extends HttpServlet {
   

    Util util;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       util = new Util(request, response);
        util.outputHeader("Sterge");
        String id = (String)request.getParameter("id");
        int idd = Integer.parseInt(id);
       // util.output("id:"+idd);
        Persoana a=new Persoana();
        a.load(idd);
        a.sterge();
        util.output("Utilizatorul a fost sters");
        util.outputFooter();
    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
