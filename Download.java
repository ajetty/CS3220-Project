package Project;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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

import org.apache.tomcat.util.http.fileupload.FileUtils;

@WebServlet("/Project/Download")
public class Download extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	    public Download()
	    {
	        super();
	    }


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
	            String password = "";

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
//        Integer id = Integer.valueOf(request.getParameter("id"));
//        
//        String path = getFilePath(id);
//        String name = getFileName(id);
        
//        URL url = new URL(path).openStream();
//        File file = new File(name);
        
//        try (BufferedInputStream in = new BufferedInputStream(new URL(path).openStream());
//        		  FileOutputStream fileOutputStream = new FileOutputStream(name)) {
//        		    byte dataBuffer[] = new byte[1024];
//        		    int bytesRead;
//        		    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//        		        fileOutputStream.write(dataBuffer, 0, bytesRead);
//        		    }
//        		    
//        		    in.close();
//        		} catch (IOException e) {
//        		    // handle exception
//        }

//        FileInputStream in = new FileInputStream(path);
//        OutputStream out = response.getOutputStream();
//
//        byte buffer[] = new byte[2048];
//        int bytesRead;
//        while( (bytesRead = in.read( buffer )) > 0 )
//            out.write( buffer, 0, bytesRead );
//
//        in.close();
    	
        Integer id = Integer.valueOf(request.getParameter("id"));
        String path = getFilePath(id);
        String fileName = getFileName(id);
    	
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".jpg");
 
        try(InputStream in = request.getServletContext().getResourceAsStream("/images/" + fileName + ".jpg");
          
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

}


