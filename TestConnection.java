// import java.io.*;
// import java.text.*;
// import java.util.*;
// import javax.servlet.*;
// import javax.servlet.http.*;

// public class TestConnection extends HttpServlet {

//     public void doGet(HttpServletRequest request, 
//                       HttpServletResponse response)
//     throws IOException, ServletException
//     {
//         response.setContentType("text/html");
//         PrintWriter out = response.getWriter();
//         out.println("<html>");
//         out.println("<head>");
//         out.println("<title>My Hello World!</title>");
//         out.println("</head>");
//         out.println("<body>");
//         out.println("<h1>My Hello World!</h1>");
//         out.println("</body>");
//         out.println("</html>");
//     }
// }

// File: ShowBedrock.java

/* A servlet to display the contents of the MySQL Bedrock database */

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestConnection extends HttpServlet 
{
    public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        String loginUser = "irteam";
        String loginPasswd = "Dkfkq486!!";
        String loginUrl = "jdbc:mysql://192.168.0.65:13306/test";


        response.setContentType("text/html;charset=UTF-8");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>2018 Toast 인턴 직원 목록</TITLE></HEAD>");
        out.println("<BODY><H1>2018 인턴 목록</H1>");
        out.println("<BODY><H1>Version 2.0</H1>");

        // Load the mm.MySQL driver
        try
           {
              Class.forName("org.gjt.mm.mysql.Driver");
              Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
              // Declare our statement
              Statement statement = dbcon.createStatement();

              String query = "SELECT * FROM member;";

              // Perform the query
              ResultSet rs = statement.executeQuery(query);

              out.println("<TABLE border>");

              // Iterate through each row of rs
              while (rs.next())
              {
                  int m_num = rs.getInt("no");
                  String m_name = rs.getString("t_name");
                  String m_content = rs.getString("content");
                  out.println("<tr>" +
                              "<td>" + m_num + "</td>" +
                              "<td>" + m_name + "</td>" +
                              "<td>" + m_content + "</td>" +
                              "</tr>");
              }

              out.println("</TABLE>");

              rs.close();
              statement.close();
              dbcon.close();
            }
        catch (SQLException ex) {
                out.println("<HTML>" +
                            "<HEAD><TITLE>" +
                            "Toast: Error" +
                            "</TITLE></HEAD>\n<BODY>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></BODY></HTML>");
                return;
            }  // end catch SQLException

        catch(java.lang.Exception ex)
            {
                out.println("<HTML>" +
                            "<HEAD><TITLE>" +
                            "Toast: Error" +
                            "</TITLE></HEAD>\n<BODY>" +
                            "<P>SQL error in doGet: " +
                            ex.getMessage() + "</P></BODY></HTML>");
                return;
            }
         out.close();
    }
}