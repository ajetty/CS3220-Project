package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/Project/Uploader")
@MultipartConfig
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Uploader() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Part filePart = request.getPart("fileUp");
		
		final String requestedName = request.getParameter("name");
		
		String fileName = getFileName(filePart); // filePart.getSubmittedFileName();request.getParameter("name");
		
		int id = -1;
		
		if (!requestedName.isEmpty()) {
			String extension = "";
			int indexOfLastComma = fileName.lastIndexOf(".");
			
			for (int i = indexOfLastComma-1; i < fileName.length(); i++) {
				extension += fileName.charAt(i);
			}
			
			fileName = requestedName + extension;
		}
		
		//This finds the real path on the CS3 server of the "images" folder under "www" on CS3.
		final String path = getServletContext().getRealPath("/images");
		
		File file = new File(path + "/" + fileName);
		
		if (file.exists()) {
			file.delete();
			
			Connection d = null;
			try
	        {
	        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	            String username = "cs3220stu07";
	            String password = "CP2JUy!V";
	            
	            d = DriverManager.getConnection( url, username, password );
	            Statement stmt = d.createStatement();
	            
	            ResultSet rs = stmt.executeQuery( "SELECT id FROM uploads WHERE fileName='" + fileName + "';");

	            while( rs.next() )
	            {
	                id = Integer.parseInt(rs.getString("id"));
	            }
	            
	            String sql = "DELETE FROM uploads WHERE id = '" + id + "'";
	            
	            PreparedStatement pstmt = d.prepareStatement(sql);
	            
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
	                if( d != null ) d.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		}
		
		OutputStream out = null;
		InputStream filecontent = null;

		try {
			out = new FileOutputStream(new File(path + File.separator + fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

		} catch (FileNotFoundException fne) {
			System.out.println("file not uploaded");
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}

		}
		
		//upload data to database

        Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu07?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cs3220stu07";
            String password = "CP2JUy!V";

            c = DriverManager.getConnection( url, username, password );
            Statement pstmt = c.createStatement();

            pstmt.executeUpdate("INSERT INTO uploads (fileName, filePath) values ('" + fileName + "','" + path + "');");
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

	public String getFileName(final Part part ) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
