

import java.io.IOException;

import java.sql.Connection;

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
		/*Necessary to connect to the mySQL database*/
		
		String dbName="questionapp";//name of mySQL database used by this application

			try{
			
				Connection c=SQLHandler.getSQLConnection(dbName);//connections to the db
				Statement s=c.createStatement();
				/*creates a result set to find the number of questions in the database to properly size the arrays for the 
				 * questions to the extracted and the answers from the user
				 */
				
				ResultSet rs1=s.executeQuery("select count(*) from interviewq");
				rs1.next();
				int i=rs1.getInt("count(*)");
				
				/*create an array for the questions and the answers of the size found by the count SQL statement
				 * 
				 */
				
				String questions[]=new String[i];
				String answers[]=new String[i];
				
				/*Second SQL statement that extracts all the questions from the database and populates the question array
				 * 
				 */
				ResultSet rs2=s.executeQuery("SELECT * from interviewq");
				i=0;//resets the counter to 0
				while(rs2.next()){
					questions[i]=rs2.getString("question");
					i++;
				}
				 
			       
				 /*for loop that gets all the answers submitted, if there is an error reading the answer or it was left blank,
				  * sets a session variable so error text will be shown on QuestionDashboard.jsp. If there are no mistakes, 
				  * populates the answer array
				  */
				 for(i=0;i<answers.length;i++){
			    	  
					 if(request.getParameter("answer"+Integer.toString(i+1))==null||request.getParameter("answer"+Integer.toString(i+1)).equals("")){
						 request.getSession().setAttribute("err", "yes");
						 response.sendRedirect("QuestionDashboard.jsp");
						 return;//leaves loop as soon as one field is found empty
					 }else{
						 answers[i]=request.getParameter("answer"+Integer.toString(i+1));
					 }
				 }	 

			     compileEmail(questions,answers);//sends the questions and answers array to compile them together to be emailed
				c.close();//close the connection to the db
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SubmitSuccesful.html");
				dispatcher.forward(request,response);
				
			}catch(SQLException e){
				e.printStackTrace();
				}
			/*After all the code in the try block was executed, it will make sure to forward the user to the 
			 * successful submission page
			 */
			
			
			
			
			}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void compileEmail(String [] questions ,String [] answers){ //receives array of questions and the answers
	/*Necessary information to send the email*/ 
		String fromAdress="haHAA666imcool@gmail.com";
		String fromPW="skadoosh123";//password for the from email
		String toAddress="eric.turriff@gmail.com";
		String subject="Interview";
	 String message="Hello, here are the results of the interview:\n";
	 /*Loops through both the questions and answers array and concatenates the strings from both the arrays with the following
	  * format
	  * Q1:....
	  * A1:....
	  * etc
	  */
	 for(int i=0;i<questions.length;i++){
		 message+=("Question "+(i+1)+": "+questions[i]+"\n");
		message+=("Answer "+(i+1)+": "+answers[i]+"\n");
	 }
	 /*sends the email information and the message to the static send method in the Mailer class
	  * 
	  */
	 
	Mailer.send(fromAdress, fromPW, toAddress, subject, message);
		
	}

}
