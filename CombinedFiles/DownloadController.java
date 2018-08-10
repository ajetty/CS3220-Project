package cloudServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

@WebServlet("/cloudServer/DownloadController")
public class DownloadController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	    public DownloadController()
	    {
	        super();
	    }


	 public String getFilePath(Integer id) throws ServletException {
		 
	    Connection c = null;          
	    String filePath = "";
	    
	    try
	    {
			String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu02?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "cs3220stu02";
			String password = "5KF0!Z.3";
	
	        c = DriverManager.getConnection(url, username, password);
	        Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Uploads where UploadID =" + id+";");
	        
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
    	 System.out.println(path);
         String fileName = path;
         FileInputStream fileInputStream = null;
         OutputStream responseOutputStream = null;
         
         try
         {
             File file = new File(fileName);

             String mimeType = request.getServletContext().getMimeType(fileName);
             if (mimeType == null) {        
                 mimeType = "application/octet-stream";
             }
             response.setContentType(mimeType);
             response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
             response.setContentLength((int) file.length());

             fileInputStream = new FileInputStream(file);
             responseOutputStream = response.getOutputStream();
             int bytes;
             while ((bytes = fileInputStream.read()) != -1) {
                 responseOutputStream.write(bytes);
             }
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         finally
         {
             fileInputStream.close();
             responseOutputStream.close();
         }
         response.sendRedirect("/cloudServer/CloudMainPage");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

}