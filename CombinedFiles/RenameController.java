package cloudServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cloudServer/RenameController")
public class RenameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Integer id;
	
    public RenameController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		request.getRequestDispatcher( "/WEB-INF/CloudServerJSP/cloudRename.jsp" ).forward(
	            request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection c = null;
		String rename = request.getParameter("name");
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu06?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cs3220stu06";
            String password = "wzlZ8.5p";

            c = DriverManager.getConnection( url, username, password );
            
            String query = "UPDATE uploads SET fileName = ?  WHERE id = ? " ;
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString (1, rename);
            preparedStmt.setInt (2, id);
            preparedStmt.executeUpdate();
            
        }
            
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
            finally
            {
                try
                {
                    if( c != null ) c.close();
                }
                catch( SQLException e )
                {
                    throw new ServletException( e );
                }
            }
		response.sendRedirect("CloudMainPage");
	}

}