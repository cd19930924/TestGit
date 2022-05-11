package com.mail.model.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.mail.model.vo.SendVerifyVO;
import com.member.model.vo.MemberRegistVO;


@Service
public class SendRegistMailService {

    public String user;

    public String getRegistUrl(String name) {
    	String url = "http://localhost:8081/CFA104G6/front-end/member/member-verify?member="+name ;
        return url;
    }
   
    //send email to the user email
    public boolean sendEmail(MemberRegistVO user) {
        boolean test = false;

        String toEmail = user.getMemEmail();
        String fromEmail = "javatestmail12345@gmail.com";
        String password = "uylpngkzrgaoqhrf";
        String url  = getRegistUrl(user.getMemAcct());
        try {
        
            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

    		//set from email address
            mess.setFrom(new InternetAddress(fromEmail));
    		//set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		
    		//set email subject
            mess.setSubject("User Email Verification");
    		//set message text
            mess.setText("Registered successfully.Please verify your account using this url: " + url);
            //send the message
            Transport.send(mess);
            
            test=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}
