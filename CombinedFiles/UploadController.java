package cloudServer;

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
import java.sql.SQLException;

@WebServlet("/cloudServer/UploadController")
@MultipartConfig
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Part filePart = request.getPart("fileUp");
		System.out.println(filePart);
		String name=request.getParameter("name");;
	    final String path = getServletContext().getRealPath("/CloudFiles"); 
		
		System.out.println(path);
 	    final String fileName = getFileName(filePart);
		System.out.println(fileName);
		
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
        	String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu02?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "cs3220stu02";
            String password = "5KF0!Z.3";

            String sql = "insert into Uploads (Name, Filepath, UploadDate) values (?, ?, CURRENT_DATE)";

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString( 1, name );
            pstmt.setString( 2, path +"/"+ fileName);
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


		response.sendRedirect("CloudMainPage");
	}

	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}