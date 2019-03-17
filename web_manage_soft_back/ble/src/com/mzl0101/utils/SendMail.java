package com.mzl0101.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mzl0101.entity.Users;
public class SendMail extends Thread{
	private String from = "15129243623@163.com";
	private String username = "15129243623@163.com";
	private String password = "maoliang123"; 
	private String host = "smtp.163.com";
	
	CreateEmail ce = new CreateEmail();
	private Users user;
	private String subjectStr;
	private String info;
	private String user_email;
    public SendMail(Users user, String subjectStr ,String info){  
        this.user = user; 
        this.subjectStr = subjectStr;
        this.info = info;
        this.user_email = null;
        
    }  
    public SendMail(String user_email,String subjectStr ,String info){  
        this.user = null;
        this.user_email = user_email;
        this.subjectStr = subjectStr;
        this.info = info;
        
    }  
    /* 重写run方法的实现，在run方法中发送邮件给指定的用户 
     * @see java.lang.Thread#run() 
     */  
    @Override  
    public void run() {  
        try{  
        	Properties prop = new Properties();  
            prop.setProperty("mail.host", host);  
            prop.setProperty("mail.transport.protocol", "smtp");  
            prop.setProperty("mail.smtp.auth", "true");  
            
            Session session = Session.getInstance(prop); 
            Message message = null;
            session.setDebug(true);  
            Transport ts = session.getTransport();  
            ts.connect(host, username, password);  
            if(this.user==null)
            {
            	message = ce.createEmail(session,user_email,subjectStr,info); 
            }
            else if(this.user_email == null)
            {
            	message = ce.createEmail(session,user,subjectStr,info);  
            }
			ts.sendMessage(message, message.getAllRecipients());  
            ts.close();  
         
        }catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
   

}
