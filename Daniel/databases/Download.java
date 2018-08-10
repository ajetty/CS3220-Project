package databases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

@WebServlet("/databases/Download")
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
			String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu06?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "cs3220stu06";
			String password = "wzlZ8.5p";
	
	        c = DriverManager.getConnection(url, username, password);
	        Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM uploads where id =" + id+";");
	        
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 Integer id = Integer.parseInt(request.getParameter("id"));
    	 String path = getFilePath(id);
         String fileName = path;
         String fileType = "";
         File my_file = new File(fileName);
         
         // Find this file id in database to get file name, and file type

         // You must tell the browser the file type you are going to send
         // for example application/pdf, text/plain, text/html, image/jpg
         response.setContentType("png");

         // Make sure to show the download dialog
         //response.setHeader("Content-disposition","attachment; filename=yourcustomfilename.pdf");
         response.setHeader( "Content-Disposition", String.format("attachment; filename=\"%s\"", my_file.getName()));

         
         // Assume file name is retrieved from database
         // For example D:\\file\\test.pdf

         // This should send the file to browser
         OutputStream out = response.getOutputStream();
         FileInputStream in = new FileInputStream(my_file);
         byte[] buffer = new byte[4096];
         int length;
         while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
         }
         in.close();
         out.flush();

        response.sendRedirect("/databases/FileManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

}