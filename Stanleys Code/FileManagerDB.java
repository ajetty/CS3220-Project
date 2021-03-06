package Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Project/FileManagerDB", loadOnStartup = 1)
public class FileManagerDB extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public FileManagerDB()
    {
        super();
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        List<Upload> files = new ArrayList<Upload>();
        Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cs3220stu07";
            String password = "CP2JUy!V";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery( "select * from uploads" );

            while( rs.next() )
            {
                Upload file = new Upload( Integer.parseInt(rs.getString("id")), rs.getString( "fileName" ), rs.getString( "filePath" ), rs.getString("date") );
                files.add( file );
            }
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

        request.setAttribute( "files", files );
        RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/FileManager.jsp");
        dispatch.forward(request, response);
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        doGet( request, response );
    }

}