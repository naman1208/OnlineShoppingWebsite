import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import javax.servlet.http.*;
@WebServlet(urlPatterns = {"/index"})
public class index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        try{
                Class.forName("org.gjt.mm.mysql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/DBShop","root","");
                PreparedStatement stmt;
                ResultSet rs;
                HttpSession hs=request.getSession(false);
                String ss="<a href='login'>Login</a>";
                if(hs!=null)
                {
                    ss="<a href='logout'>Logout</a>";
                }
                String reg="";
                if(hs==null)
                {
                    reg="<td><a href='register'>Register</a></td>";
                }
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body>");
                out.write("<img src='banner.jpg' width='100%' height='200px'><hr>");
                out.write("<table width='100%'><tr><td><a href='index'>Home</a></td><td><a href='categories'>Categories</a></td><td><a href='products'>Products</a></td><td><a href='help'>Contact Us/Help</a></td><td><a href='myaccount'>My Account</a></td>"+reg+"<td>"+ss+"</td><td><a href='basket'>Basket</a></td></tr></table><hr>");
                out.write("<img src='b1.jpg' width='300px'>");
                out.write("<img src='b2.jpg' width='300px'>");
                out.write("<img src='b3.jpg' width='300px'><br>");
                out.write("<img src='b4.jpg' width='300px'>");
                out.write("<img src='b5.jpg' width='300px'>");
                out.write("<img src='b6.jpg' width='300px'><br>");
                out.write("<img src='b7.jpg' width='450px'>");
                out.write("<img src='b8.jpg' width='450px'>");

                out.write("<hr><img src='footer.jpg' width='100%' height='150px'>");
                out.write("</body></html>");
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
