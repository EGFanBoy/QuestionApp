

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("Username");
		String password=request.getParameter("Password");
		String jdbcDriver="com.mysql.jdbc.Driver";
		String dbName="jdbc:mysql://localhost:3306/questions?useSSL=false";
		String dbUser="root";
		String dbPw="skadoosh";
		PrintWriter out=response.getWriter();
		try{
			Class.forName(jdbcDriver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		try{
			Connection c=DriverManager.getConnection(dbName,dbUser,dbPw);
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT*FROM users");
			while(rs.next()){
				if(rs.getString("id").equals(username)&&rs.getString("password").equals(password)){
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AddDel.html");
					dispatcher.forward(request,response);
				}
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.html");
			out.print("<font color=red>Either user name or password is wrong.</font>");
			dispatcher.include(request,response);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
