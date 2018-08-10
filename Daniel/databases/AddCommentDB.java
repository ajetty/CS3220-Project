package databases;

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

/**
 * Servlet implementation class AddCommentDB
 */
@WebServlet("/databases/AddComment")
public class AddCommentDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentDB() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
            request.getRequestDispatcher( "/WEB-INF/mvc/AddComment.jsp" ).forward(
                request, response );
        }

        protected void doPost( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
            // get the user input
            String name = request.getParameter( "name" );
            String message = request.getParameter( "message" );

            Connection c = null;
            try
            {
            	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu06?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String username = "cs3220stu06";
                String password = "wzlZ8.5p";

                String sql = "insert into guestbook (name, message) values (?, ?)";

                c = DriverManager.getConnection( url, username, password );
                PreparedStatement pstmt = c.prepareStatement( sql );
                pstmt.setString( 1, name );
                pstmt.setString( 2, message );
                pstmt.executeUpdate();
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

            // send the user back to the guest book page
            response.sendRedirect( "GuestBook" );
        }

}
