package com.uamservice.email.utils;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

  @Autowired
  private JavaMailSender javaMailSender;
  
 

  public void sendOtpEmail(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Verify OTP");
    mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">Reset Your Password</a>
        </div>
        """.formatted(email, otp), true);

    javaMailSender.send(mimeMessage);
  }
  
  public void sendPasswordEmail(String email,String token) throws MessagingException {
//	  UUID token = UUID.randomUUID();
	    String setNewPasswordLink = "http://localhost:8080/uam-service/email/set-password?email="+ email +"&newPassword=";
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
	    mimeMessageHelper.setTo(email);
	    mimeMessageHelper.setSubject("Set Password");
//	    mimeMessageHelper.setText("""
//	        <div>
//	          <a href="http://localhost:8080/uam-service/email/set-password?email=&newPassword=" target="_blank">Set your New Password</a>
//	        </div>
//	        """.formatted(email), true);
	    String emailContent = String.format("""
	    	    <!DOCTYPE html>
	    	    <html>
	    	    <head>
	    	        <meta charset="UTF-8">
	    	        <title>Set Your New Password</title>
	    	        <style>
	    	            body {
	    	                font-family: Arial, sans-serif;
	    	                line-height: 1.6;
	    	                margin: 0;
	    	                padding: 0;
	    	            }
	    	            .container {
	    	                max-width: 600px;
	    	                margin: 20px auto;
	    	                padding: 20px;
	    	                background-color: #f4f4f4;
	    	            }
	    	            h1 {
	    	                color: #444;
	    	            }
	    	            a {
	    	                display: inline-block;
	    	                background-color: #007bff;
	    	                color: #fff;
	    	                text-decoration: none;
	    	                padding: 10px 20px;
	    	                border-radius: 5px;
	    	            }
	    	            a:hover {
	    	                background-color: #0056b3;
	    	            }
	    	        </style>
	    	    </head>
	    	    <body>
	    	        <div class="container">
	    	            <h1>Set Your New Password</h1>
	    	            <p>Hello,</p>
	    	            <p>We received a request to reset your password. To set your new password, please click the link below:</p>
	    	            <p>
	    	                <a href="%s" target="_blank">Set your New Password</a>
	    	            </p>
	    	            <p>If you did not request this change, you can safely ignore this email.</p>
	    	            <p>Best regards,</p>
	    	            <p>Your Company Name</p>
	    	        </div>
	    	    </body>
	    	    </html>
	    	    """, setNewPasswordLink);
	    mimeMessageHelper.setText(emailContent, true);

	    javaMailSender.send(mimeMessage);
	  }
  
}
