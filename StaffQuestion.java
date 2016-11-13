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
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question() {
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		 final String URL = "jdbc:mysql://localhost:3306/onlinequiz?user=root&password=root";
	        String qry = "insert into question values(?,?,?,?)";
		String q=request.getParameter("qu");
		String choice1=request.getParameter("ch1");
		String choice2=request.getParameter("ch2");
		String choice3=request.getParameter("ch3");
		try {
			Class.forName("com.mysql.jdbc.Driver");
     	Connection con=DriverManager.getConnection(URL);
     	PreparedStatement pst = con.prepareStatement(qry);
     	String htmloutput = "<html><body>Question entered</body></html>";

			pst.setString(1,q);
			pst.setString(2,choice1);
			pst.setString(3, choice2);
			pst.setString(4, choice3);
			pst.executeUpdate();
			
		
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