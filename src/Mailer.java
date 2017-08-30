import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Mailer{  
    public static void send(String fromAddress,String fromPW,String toAddress,String subject,String message){  
          //Get properties object    
       
          Properties props = new Properties();//creates properties object
  		props.put("mail.smtp.auth", "true");//makes it so it code will try to authenticate the user
  		props.put("mail.smtp.starttls.enable", "true");//enable the tls
  		props.put("mail.smtp.host", "smtp.gmail.com");//sends the mail to the gmail SMTP server
  		props.put("mail.smtp.port", "587"); //gmail SMTP port 587
  
         
          //get Session and tries to authenticate user credentials
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(fromAddress,fromPW);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage mmessage = new MimeMessage(session); //create a MIME message   
           mmessage.addRecipient(Message.RecipientType.TO,new InternetAddress(toAddress));    
           mmessage.setSubject(subject);    
           mmessage.setText(message);    
           //send message  
           Transport.send(mmessage);    
         
          } catch (MessagingException e) {
        	  e.printStackTrace();}    
             
    }  
}  