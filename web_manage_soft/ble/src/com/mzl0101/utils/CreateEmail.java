package com.mzl0101.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mzl0101.entity.Users;

public class CreateEmail {
	 private String from = "15129243623@163.com";
	 public Message createEmail(Session session,Users user,String subjectStr ,String info) {  
         
         MimeMessage message = new MimeMessage(session);  
         try {
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setSubject(subjectStr);
			message.setContent(info, "text/html;charset=UTF-8");
			message.saveChanges();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return message;  
     }  
	 
	public Message createEmail(Session session,String user_email,String subjectStr ,String info) {  
	         
	         MimeMessage message = new MimeMessage(session);  
	         try {
				message.setFrom(new InternetAddress(from));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(user_email));
				message.setSubject(subjectStr);
				message.setContent(info, "text/html;charset=UTF-8");
				message.saveChanges();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return message;  
	     }  

}
