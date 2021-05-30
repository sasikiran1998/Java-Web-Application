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
public class student extends HttpServlet {
public static Connection conn;
   

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
                    PreparedStatement st = conn 
                   .prepareStatement("insert into registration values(?,?,?,?,?,?)");
                    st.setString(1, request.getParameter("name")); 
                    st.setInt(2, Integer.valueOf(request.getParameter("roll")));
                    st.setInt(3, Integer.valueOf(request.getParameter("mark")));
                    st.setInt(4, Integer.valueOf(request.getParameter("mobile")));
                    st.setInt(5, Integer.valueOf(request.getParameter("credit")));
                    st.setString(6, request.getParameter("mail"));
                                
                     st.executeUpdate(); 
  
            // Close all the connections 
            
            Statement stmt = conn.createStatement();
         String sql;
         sql = "SELECT mark from registration where mark>50";
         ResultSet rs = stmt.executeQuery(sql);
         int rank=0;
         while(rs.next()){
            //Retrieve by column name
            int mark  = rs.getInt("mark");
            
            if(mark>50){
                out.println("<html><body><b>REACHED CUTOFF"
                        + "</b></body></html>"); 
                
            }
            else{
            out.println("<html><body><b>CUTOFF NOT REACHED"
                        + "</b></body></html>"); 
           }
            rank++;
            out.println("<html><body><b><i></br></br>Your rank is :: "+rank+"</i></b></body></html>");
         }
          out.println("</body></html>");
         // Clean-up environment
         rs.close();
         stmt.close();
         st.close(); 
            conn.close(); 
  
            // Get a writer pointer  
            // to display the successful result 
            //PrintWriter outer = response.getWriter(); 
            //outer.println("<html><body><b>Successfully Inserted"
                        //+ "</b></body></html>"); 

                }
                    // Execute SQL query
                    /*
         Statement stmt = conn.createStatement();
         String sql;
         sql = "SELECT * from student where roll=roll";
         ResultSet rs = stmt.executeQuery(sql);
         

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            String name1 = rs.getString("name");
            String rolls  = rs.getString("roll");
            int mobile  = rs.getInt("mobile");
            int credit = rs.getInt("credit");
            String mail = rs.getString("mail");
           if(rolls.equals(r)){
            out.print("<br>Fetching Details From Database"+"<br><br>");
            out.println("NAME ::    " + name1 + "<br><br>");
            out.println("ROLL ::    " + rolls + "<br><br>");
            out.println("MOBILE ::    " + mobile + "<br><br>");
            out.println("CREDITS ::    "+credit+"<br><br>");
            out.println("E-MAIL ID ::    "+mail+"<br><br>");
            }
         
         }
          out.println("</body></html>");
         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
	        } */
	        catch (SQLException e) 
	        {
	            System.out.println("Connection Failed! Check output console");
	        } catch (ClassNotFoundException ex) {
        Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
    }
     }

}
