
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

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

		/* Information necessary to connect to SQL database */
	
		String dbName = "questionapp";
		
		PrintWriter out = response.getWriter(); /* Printwriter object, might
											 		replace later*/

		try {
			/*if the field is empty, it will forward back to AddQ.jsp and add an error message in red*/
			if (request.getParameter("question") == null || request.getParameter("question").equals("")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddQ.jsp");

				out.print("<font color=red>Field left empty. Please enter a question!</font>"); //might be replace
				dispatcher.include(request, response);
			} else {

				
				Connection c = SQLHandler.getSQLConnection(dbName);//create connection to mySQL database
				Statement s = c.createStatement();//creates SQL statement, change to preparedStatement
				
				/*Adds the questions extracted from the form to the database for future interviews.
				 Afterwards it forwards back to AddQ.jsp with a success message.*/
				s.executeUpdate(
						"INSERT into interviewq(question)" + "VALUES('" + request.getParameter("question") + "')");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddQ.jsp");

				out.print("<font color=green>Question successfully added. Feel free to add another.</font>");
				dispatcher.include(request, response);
				/*Close both the mySQl connection and Printwriter*/
				c.close();
				out.close();
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
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
