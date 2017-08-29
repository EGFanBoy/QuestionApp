

import java.io.IOException;

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
 * Servlet implementation class QuestionDeleter
 */
@WebServlet("/QuestionDeleter")
public class QuestionDeleter extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionDeleter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jdbcDriver="com.mysql.jdbc.Driver";
		String dbName="jdbc:mysql://localhost:3306/questions?useSSL=false";
		String dbUser="root";
		String dbPw="skadoosh";
		int i=0;
		int delQuantity=0;
	
		
		try{
			Class.forName(jdbcDriver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		try{
			Connection c=DriverManager.getConnection(dbName,dbUser,dbPw);
			Statement s=c.createStatement();
			ResultSet rs1=s.executeQuery("select count(*) from questions");
			rs1.next();
			i=rs1.getInt("count(*)");
			String questions[]=new String[i];
			ResultSet rs=s.executeQuery("SELECT * FROM questions");
			int j=0;
			while(rs.next()){
			questions[j]=rs.getString("question");
			j++;
			}
	
	for(int k=0;k<questions.length;k++){
		
		if(request.getParameter("check"+Integer.toString(k+1))!=null&&request.getParameter("check"+Integer.toString(k+1)).equals("checked")){
			s.executeUpdate("delete from questions where question="+"'"+questions[k]+"'");
		delQuantity++;
		}
		
		
	}
	if(delQuantity>0){
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DelSuccesful.jsp");
		dispatcher.forward(request,response);
	}else {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DelUnsuccesful.jsp");
		dispatcher.forward(request,response);	
	}
	
	
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
