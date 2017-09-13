

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
		/*Necessary to connect to the mySQL database*/
		
		String dbName="questionapp";//name of mySQL database used by this application
		String firstName="";
		String lastName="";

			try{
			
				Connection c=SQLHandler.getSQLConnection(dbName);//connections to the db
				Statement s=c.createStatement();
				/*creates a result set to find the number of questions in the database to properly size the arrays for the 
				 * questions to the extracted and the answers from the user
				 */
				
			
				
				/*creates arraylists for the questions and the answers 
				 * 
				 */
				List<String> questions=new ArrayList<String>();
				List<String> answers=new ArrayList<String>();
		
				
				/*Second SQL statement that extracts all the questions from the database and populates the question array
				 * 
				 */
				ResultSet rs2=s.executeQuery("SELECT * from interviewq");
				
				while(rs2.next()){
					questions.add(rs2.getString("question"));
					
				}			       
				 /* 
				  * Loops through the size of the question array since # of questions is the same as the # of answers
				  */
				 for(int i=0;i<questions.size();i++){
						 answers.add(request.getParameter("answer"+Integer.toString(i+1)));
					 
				 }	 
				 firstName=request.getParameter("firstName");//gets the persons first name from the form
				 lastName=request.getParameter("lastName");//gets the persons last name from the form

			     compileEmail(questions,answers,firstName,lastName);/**sends the questions, answers and the person's 
			    personal information to compile them together to be emailed**/
				c.close();//close the connection to the db
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SubmitSuccesful.html");
				dispatcher.forward(request,response);
				
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
	public void compileEmail(List<String>questions ,List<String>answers,String firstName,String lastName){ //receives array of questions and the answers
	/*Necessary information to send the email*/ 
		String fromAdress="questionapp613@gmail.com";
		String fromPW="crypticpassword123";//password for the from email
		String toAddress="eric.turriff@gmail.com";
		String subject=("Interview for "+firstName+" "+lastName);
	 String message=("Hello, here are the results of the interview of "+firstName+" "+lastName+":\n");
	 /*Loops through both the questions and answers array and concatenates the strings from both the arrays with the following
	  * format
	  * Q1:....
	  * A1:....
	  * etc
	  */
	 for(int i=0;i<questions.size();i++){
		 message+=("Question "+(i+1)+": "+questions.get(i)+"\n");
		message+=("Answer "+(i+1)+": "+answers.get(i)+"\n");
	 }
	 /*sends the email information and the message to the static send method in the Mailer class
	  * 
	  */
	 
	Mailer.send(fromAdress, fromPW, toAddress, subject, message);
		
	}

}
