
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginHandler
 */
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginHandler() {
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

		/*
		 * local variables for the username and password to be retrieved by the
		 * form
		 */
		String username = "";
		String password = "";
		/* Necessary information to access mySQL database */
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbName = "jdbc:mysql://localhost:3306/questions?useSSL=false";
		String dbUser = "root";
		String dbPw = "skadoosh";
		PrintWriter out = response
				.getWriter();/* printwriter object, might be replace */

		try {
			/*
			 * If the username or password field is left blank or was not read
			 * properly forwards back to the login form with an error message
			 */

			if (request.getParameter("Username") == null || request.getParameter("Username").equals("")
					|| request.getParameter("Password") == null || request.getParameter("Password").equals("")) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.html");
				out.print("<font color=red>Please enter both your username and password.</font>");
				dispatcher.include(request, response);
			} else {
				/*fetches the username and password from the form since they are not null
				 * 
				 */
				username=request.getParameter("Username");
				password=request.getParameter("Password");
				Class.forName(jdbcDriver);// loads the mySQL driver
				Connection c = DriverManager.getConnection(dbName, dbUser, dbPw);// connects
																					// to
																					// the
																					// mySQL
																					// database
				Statement s = c.createStatement();// creates the statement,
													// change to
													// preparedstatement
				ResultSet rs = s.executeQuery("SELECT*FROM users");

				/*
				 * Loops through all elements in the users table. If the
				 * username and password supplied matches the ones in the
				 * database it will set a session attribute to yes, used by
				 * AddQ.jsp, AddDel.jsp and DelQ.jsp to assure the user logged
				 * in before accessing these pages. Afterwards forwards the user
				 * to the page where they can choose to either add or delete
				 * questions
				 */

				while (rs.next()) {
					if (rs.getString("id").equals(username) && rs.getString("password").equals(password)) {

						request.getSession().setAttribute("loggedIn", "yes");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddDel.jsp");
						dispatcher.forward(request, response);
					
					
					}
				} // end of while loop

				/*
				 * If the while loop for logging in completes because the
				 * credentials did not match any users in the database, the user
				 * will be redirected back to the login page with an error
				 * message
				 */
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.html");
				out.print("<font color=red>Either user name or password is wrong.</font>");
				dispatcher.include(request, response);
				/* close connection and printwriter to avoid memory leaks */
				c.close();
				out.close();

			} // end of else statement

		} catch (SQLException | ClassNotFoundException e) {
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
