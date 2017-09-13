

import java.io.IOException;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		/*Necessary info to access SQl db
		 * 
		 */

		String dbName="questionapp";
	
		int i=0;//integer counter
		int delQuantity=0;//counter used to detect if questions were actually deleted
		
		
		try{
		
			Connection c=SQLHandler.getSQLConnection(dbName);//connects to the db
			Statement s=c.createStatement();//creates statement object
			/*creates a result set to find the number of questions in the database to properly size the questions
			 * array
			 */
			
			/**ResultSet rs1=s.executeQuery("select count(*) from interviewq");
			rs1.next();
			i=rs1.getInt("count(*)");
			String questions[]=new String[i];**/
			List<String>questions=new ArrayList<String>();
			/*Second SQL statement that extracts all the questions from the database and populates the question array
			 * 
			 */
			
			ResultSet rs=s.executeQuery("SELECT * FROM interviewq");
		
			while(rs.next()){
			questions.add(rs.getString("question"));
		
			}
	
			/*for loops that loops through every question in the array. If the checkbox was checked by the user in the DelQ.jsp form
			 * it will execute the SQL statement to remove that question from the database. Also increments delQuantity counter
			 * to determine where to forward the user afterwards
			 */
	for(i=0;i<questions.size();i++){
		if(request.getParameter("check"+Integer.toString(i+1))!=null&&request.getParameter("check"+Integer.toString(i+1)).equals("checked")){
			s.executeUpdate("delete from interviewq where question="+"'"+questions.get(i)+"'");
		delQuantity++;
		}
		
		
	}
	/*If something was deleted, send user to the delSuccesful formm
	 * 
	 */
	if(delQuantity>0){
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/DelSuccesful.jsp");//change these to html forms
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
