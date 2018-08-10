package GroupProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Rename
 */
@WebServlet("/databases/Rename")
public class Rename extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rename() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
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
		response.sendRedirect("FileManagerDB");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
