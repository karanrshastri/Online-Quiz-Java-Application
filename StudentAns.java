import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
        super();
        // TODO Auto-generated constructor stub
      
        }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//super.init(config);
	
	      
	       
			}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
{
	doPost(request,response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		 final String URL = "jdbc:mysql://localhost:3306/onlinequiz?user=root&password=root";
	        String qry = "select * from question";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
     	Connection con=DriverManager.getConnection(URL);
     	PreparedStatement pst = con.prepareStatement(qry);
     	ResultSet rs=pst.executeQuery();
     	String htmloutput = "<html><body>Question retrieved </body></html>";
     		while(rs.next()){
     			String question =rs.getString("q");
     			String ch1=rs.getString("choice1");
     			String ch2=rs.getString("choice2");
     			String ch3=rs.getString("choice3");
     			
     			htmloutput=htmloutput+question+"<form><br><input type=\"radio\">"  + ch1 +"<br>" + "<form><input type=\"radio\">"+ ch2+"<br>" +"<form><input type=\"radio\">"+ ch3+"<br>";
     		}
			
     		
			
		
			PrintWriter out = response.getWriter();
			out.println(htmloutput);
			out.flush();
			out.close();	
		} catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}