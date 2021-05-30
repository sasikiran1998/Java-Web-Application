/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author sasi
 */
public class studetails extends HttpServlet {
public static Connection conn;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet studetails</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet studetails at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String r=request.getParameter("roll");
        String n=request.getParameter("name");
        String c=request.getParameter("credit");
        String m=request.getParameter("mobile");
       
      String url = "jdbc:postgresql://localhost:5432/testdb";
			String username = "postgres";
			String password = "psql";
                        String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      
      out.println(docType +
         "<html>\n" +
         "<head><title></title></head>\n" +
         "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\"></h1>\n");
	        try 
	        {
	            Class.forName("org.postgresql.Driver");
                    conn= (Connection) DriverManager.getConnection(url, username, password);
	            System.out.println("Connected Successfully!");
                    // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql;
         sql = "insert into student values("+r+",'"+n+"',"+c+",'"+m+"')";
         ResultSet rs = stmt.executeQuery(sql);

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            
           out.print("iserted successfully");
         
         }
          out.println("</body></html>");
         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
	        } 
	        catch (SQLException e) 
	        {
	            System.out.println("Connection Failed! Check output console");
	        } catch (ClassNotFoundException ex) {
        Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
    }
     }

    }

  
