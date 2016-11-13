import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
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
	        String qry = "select * from account where username=? and password=?";
		String username=request.getParameter("un");
		String password=request.getParameter("pw");
		try {
			Class.forName("com.mysql.jdbc.Driver");
     	Connection con=DriverManager.getConnection(URL);
     	PreparedStatement pst = con.prepareStatement(qry);
     	String htmloutput = "<html><body>";

			pst.setString(1,username);
			pst.setString(2,password);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				htmloutput=htmloutput+"Correct Details<br>";
				String type = rs.getString("act_type");
				if(type.equals("teacher"))
				{
					//htmloutput=htmloutput + "Welcome to the Portal<br>Here you can do the following with Teachers account:<br>- create quizzes for your students <br>- create questions with its multiple choices <br>- publishing the quiz with a start and end time for your students to see and attempt";
			      response.sendRedirect("question.html");
				}
				if(type.equals("student"))
				{
					//htmloutput=htmloutput+ "Here you can do the following with Student account:<br>- find the activated quizzes and attempt them<br>- submit answers online within a time limit<br>- ability to see how much time left in realtime<br>- ability to flag questions in case of error";
					response.sendRedirect("/OnlineQuiz/Student");
				}
			}
			else
			{
				htmloutput = htmloutput+"Incorrect Login Details";
			}
			htmloutput = htmloutput+ "</body></html>";
			PrintWriter out = response.getWriter();
			//out.println(htmloutput);
			//out.flush();
			//out.close();
		} catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}