package servletsJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletJDBC extends HttpServlet  {
	private static final long serialVersionUID = 1L; 
	
	Connection con = null;
	PreparedStatement stmt = null;
	
	@Override
	public void init() {
		System.out.println("Started Execution");
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out;
		
		//name
		String name = request.getParameter("name");

		//email
		String email = request.getParameter("email");
		
		//how
		String how = request.getParameter("how");
		
		//subject
		String subject = request.getParameter("subject");
		
		//message
		String message = request.getParameter("message");
		
		
		try 
		{
		//registering Driver Class in RAM
			Class.forName("oracle.jdbc.OracleDriver");
			
		//getting SQL connection.
			con = DriverManager.getConnection("jdbc:oracle:thin:https://192.168.0.11:5500/em" , "scott" , "tiger");
			
		//creating a SQL Query
			String sqlQuery = "insert into Feedback values(?,?,?,?,?)";
	        
		//creating Prepared Statement
			stmt = con.prepareStatement(sqlQuery);
			
	        //Inserting Values into the table
			stmt.setString(1,name);
			stmt.setString(2,email);
			stmt.setString(3,how);
			stmt.setString(4,subject);
			stmt.setString(5,message);
		
		//Executing the Statement Prepared.		
			stmt.executeUpdate();
		
		// An Simple acknowledgement page, just to say everything is successful.
		
			out = response.getWriter();
			out.println("<html> <body>");
			out.println("<font color = 'red' size = '15' > ");
			out.println("<center> THANKS FOR THE FEEDBACK </center>");
			out.println("</body> </html> " );

			System.out.println("Data Entered Successfully");
		
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
