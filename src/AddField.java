
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddField
 */
@WebServlet("/AddField")
public class AddField extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddField() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbName = "jdbc:mysql://localhost:3306/questions?useSSL=false";
		String dbUser = "root";
		String dbPw = "skadoosh";
		PrintWriter out=response.getWriter();
		// PrintWriter out=response.getWriter(); //USED DURING TESTING

		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (request.getParameter("question") == null || request.getParameter("question").equals("")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddQ.html");
			
			out.print("<font color=red>Field left empty. Please enter a question!</font>");
			dispatcher.include(request,response);
		} else {

			try {
				Connection c = DriverManager.getConnection(dbName, dbUser, dbPw);
				Statement s = c.createStatement();
			s.executeUpdate("INSERT into questions(question)"+"VALUES('"+request.getParameter("question")+"')");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddQ.html");
			
			out.print("<font color=green>Question successfully added. Feel free to add another.</font>");
			dispatcher.include(request,response);
			
			c.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
