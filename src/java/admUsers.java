/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/admUsers"})
public class admUsers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        try{
             Class.forName("org.gjt.mm.mysql.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/DBShop","root","");
             PreparedStatement stmt;
             ResultSet rs;
            HttpSession hs=request.getSession(false);
            if(hs==null) 
                response.sendRedirect("login");
            else
            {
                Object a=hs.getAttribute("UTYPE");
                String s=a.toString();
                if(!s.equals("ADMIN"))
                {
                    response.sendRedirect("login");
                }
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.write("<table width='100%'><tr><td><a href='admCategories'>Categories</a></td><td><a href='admProducts'>Products</a></td><td><a href='admOrders'>Order Details</a></td><td><a href='admUsers'>User Details</a></td><td><a href='logout'>Logout</a></td></tr></table>");
            out.write("<hr>");
            out.write("<table align='center' border='1'>");
            out.write("<tr><th>Email</th><th>Name</th><th>Mobile</th><th>Address</th><th>State</th><th>City</th><th>Pin Code</th></tr>");
            stmt=con.prepareStatement("Select * from Members");
            rs=stmt.executeQuery();
            while(rs.next())
            {
                out.write("<tr>");
                out.write("<td>"+rs.getString(1)+"</td>");
                out.write("<td>"+rs.getString(2)+"</td>");
                out.write("<td>"+rs.getString(3)+"</td>");
                out.write("<td>"+rs.getString(4)+"</td>");
                out.write("<td>"+rs.getString(5)+"</td>");
                out.write("<td>"+rs.getString(6)+"</td>");
                out.write("<td>"+rs.getString(7)+"</td>");                
                out.write("</tr>");
            }
            out.write("</table>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception ee){}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
