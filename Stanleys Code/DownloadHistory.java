package Project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Project/DownloadHistory")
public class DownloadHistory extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	    public DownloadHistory()
	    {
	        super();
	    }


	 public String getFilePath(Integer id) throws ServletException {
		 
	    Connection c = null;
	    String filePath = "";
	    String fileName = "";
	    
	    try
	    {
        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cs3220stu07";
            String password = "CP2JUy!V";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM history WHERE id='" + id + "'");
	        
	        while (rs.next()) {
	        	filePath = rs.getString("filePath");
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

		        ResultSet rs = stmt.executeQuery("SELECT * FROM history WHERE id='" + id + "'");
		        
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
        String fileName = getFileName(id);
    	
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
 
        //Replace "/images/" with any folder name within "www" on CS3.
        try(InputStream in = request.getServletContext().getResourceAsStream("/history/" + fileName);
          
        	OutputStream out = response.getOutputStream()) {
 
        	byte[] buffer = new byte[2048];
         
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
            
        }

        response.sendRedirect("FileManagerDB");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
	public String convertFileName(String fileName) {
        String name = "";
        
        int lastIndexOfDot = fileName.lastIndexOf(".");
        
        for (int i = 0; i < fileName.length(); i++ ) {
        	if (i == lastIndexOfDot) {
        		name += "-";
        	}
        	else {
        		name += fileName.charAt(i);
        	}
        }
        
        return name;
        
    }

}