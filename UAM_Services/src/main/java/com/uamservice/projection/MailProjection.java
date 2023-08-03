package com.uamservice.projection;

public interface MailProjection {

	 Long getMailId();
	 
	 String getEmailSubject();
	 	
	 String getSendTo();

	 String getSendCc();
	 
	 String getSendBcc();
	 
	 String getEmailStatus();
	 
}
