package Project;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Project/Delete")
public class Delete extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	 public String getFilePath(Integer id) throws ServletException {
		 
		    Connection c = null;
		    String filePath = "";
		    
		    try
		    {
	        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	            String username = "cs3220stu07";
	            String password = "CP2JUy!V";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();

		        ResultSet rs = stmt.executeQuery("SELECT * FROM uploads WHERE id='" + id + "'");
		        
		        while (rs.next()) {
		        	filePath = rs.getString("filePath");
		        }
		    }
		    catch(SQLException e)
		    {
		        throw new ServletException(e);
		    }
		    finally
		    {
		        try
		        {
		            if(c != null) c.close();
		        }
		        catch(SQLException e)
		        {
		            throw new ServletException(e);
		        }
		    }
		    
		    if (filePath == "" || filePath.isEmpty()) {
		    	return null;
		    }
		
		    return filePath;
		}
	 
	 public String getFileName(Integer id) throws ServletException {
		 
		    Connection c = null;
		    String fileName = "";
		    
		    try
		    {
	        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	            String username = "cs3220stu07";
	            String password = "CP2JUy!V";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();

		        ResultSet rs = stmt.executeQuery("SELECT * FROM uploads WHERE id='" + id + "'");
		        
		        while (rs.next()) {
		        	fileName = rs.getString("fileName");
		        }
		    }
		    catch(SQLException e)
		    {
		        throw new ServletException(e);
		    }
		    finally
		    {
		        try
		        {
		            if(c != null) c.close();
		        }
		        catch(SQLException e)
		        {
		            throw new ServletException(e);
		        }
		    }
		    
		    if (fileName == "" || fileName.isEmpty()) {
		    	return null;
		    }
		
		    return fileName;
		}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String path = getFilePath(id);
        String fileName = getFileName(id);

        Connection c = null;
        
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cs3220stu07";
            String password = "CP2JUy!V";

            String sql = "DELETE FROM uploads WHERE id = '" + id + "'";

            c = DriverManager.getConnection(url, username, password);
            
            PreparedStatement pstmt = c.prepareStatement(sql);
            
            pstmt.executeUpdate();
            
            File deleteThis = new File(path + "/" + fileName);
            
            deleteThis.delete();
             
            System.out.println("Deletion successful.");
            
            
        }
        catch(SQLException e)
        {
            throw new ServletException(e);
        }
        finally
        {
            try
            {
                if(c != null) c.close();
            }
            catch(SQLException e)
            {
                throw new ServletException(e);
            }
        }

        response.sendRedirect("FileManagerDB");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

}
