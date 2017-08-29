

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
 * Servlet implementation class QuestionApp
 */
@WebServlet("/QuestionApp")
public class QuestionApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionApp() {
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
	//	PrintWriter out=response.getWriter(); //USED DURING TESTING
		
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
				int i=rs1.getInt("count(*)");
				String q[]=new String[i]; //array of questions
				ResultSet rs2=s.executeQuery("SELECT * from questions");
				int l=0;
				while(rs2.next()){
					q[l]=rs2.getString("question");
					l++;
				}
				 String a[]=new String[i];//array of answers
			       
				 
				 for(int j=0;j<a.length;j++){
			    	  
					 if(request.getParameter("answer"+Integer.toString(j+1))==null||request.getParameter("answer"+Integer.toString(j+1)).equals("")){
						 request.getSession().setAttribute("err", "yes");
						 response.sendRedirect("QuestionDashboard.jsp");
						 return;//leaves loop as soon as one field is found empty
					 }else{
						 a[j]=request.getParameter("answer"+Integer.toString(j+1));
					 }
				 }	 

			     compileEmail(q,a);
				
			}catch(SQLException e){
				e.printStackTrace();
				}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SubmitSuccesful.html");
			dispatcher.forward(request,response);
			
			}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void compileEmail(String [] q ,String [] a){ //receives array of questions and the answers
	
		String fromAdress="haHAA666imcool@gmail.com";
		String fromPW="skadoosh123";//password for the from email
		String toAddress="eric.turriff@gmail.com";
		String subject="Interview";
	 String answers="Hello, here are the results of the interview:\n";
	 for(int i=0;i<q.length;i++){
		 answers+=("Question "+(i+1)+": "+q[i]+"\n");
		 answers+=("Answer "+(i+1)+": "+a[i]+"\n");
	 }
	
	Mailer.send(fromAdress, fromPW, toAddress, subject, answers);
	 
		
	}

}
