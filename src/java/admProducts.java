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
@WebServlet(urlPatterns = {"/admProducts"})
public class admProducts extends HttpServlet {

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
            String ss=request.getParameter("b1");
            if(ss!=null && ss.equals("Insert"))
            {
                stmt=con.prepareStatement("Select count(*)+1 from Products");
                rs=stmt.executeQuery();
                String pid="";
                if(rs.next())
                {
                    pid=rs.getString(1);
                }
                stmt=con.prepareStatement("Insert into Products values(?,?,?,?,?,?,?)");
                stmt.setString(1,pid);
                stmt.setString(2,request.getParameter("t1"));
                stmt.setString(3,request.getParameter("t2"));
                stmt.setString(4,request.getParameter("t3"));
                stmt.setString(5,request.getParameter("t4"));
                stmt.setString(6,request.getParameter("t5"));
                stmt.setString(7,request.getParameter("t6"));
                stmt.executeUpdate();
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.write("<table width='100%'><tr><td><a href='admCategories'>Categories</a></td><td><a href='admProducts'>Products</a></td><td><a href='admOrders'>Order Details</a></td><td><a href='admUsers'>User Details</a></td><td><a href='logout'>Logout</a></td></tr></table>");
            out.write("<hr>");
            out.write("<form method='post'>");
            out.write("<table align='center'>");
            out.write("<tr><td>Category Name:</td><td><select name='t1'>");
            stmt=con.prepareStatement("Select * from categories");
            rs=stmt.executeQuery();
            while(rs.next())        
            {
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                out.write("<option value='"+s1+"'>"+s2+"</option>");
            }
            out.write("</select></td></tr>");
            out.write("<tr><td>Product Name:</td><td><input type='text' name='t2'></td></tr>");
            out.write("<tr><td>Details:</td><td><input type='text' name='t3'></td></tr>");
            out.write("<tr><td>Price:</td><td><input type='text' name='t4'></td></tr>");
            out.write("<tr><td>Qty:</td><td><input type='text' name='t5'></td></tr>");
            out.write("<tr><td>Photo:</td><td><input type='file' name='t6'></td></tr>");
            out.write("<tr><td></td><td><input type='submit' name='b1' value='Insert'></td></tr>");
            out.write("</table></form>");
            out.write("<hr>");
            out.write("<table align='center' border='1'>");
            out.write("<tr><th>PID</th><th>CID</th><th>Name</th><th>Description</th><th>Price</th><th>Qty</th><th>Photo</th></tr>");
            stmt=con.prepareStatement("Select * from Products");
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
                out.write("<td><img src='"+rs.getString(7)+"' width='50px'></td>");
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
