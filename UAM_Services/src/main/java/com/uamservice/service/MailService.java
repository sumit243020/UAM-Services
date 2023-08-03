package com.uamservice.service;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.uamservice.entity.Mail;
import com.uamservice.projection.MailProjection;
import com.uamservice.repository.MailRepository;

@Service
public class MailService {

	@Autowired
	MailRepository mailRepository;
	
//	Logger logger = LoggerFactory.getLogger(MailService.class);
	
	public Page<MailProjection> getAllMails(Pageable pageable) {
		
//		Page<MailProjection> get = mailRepository.findBy(pageable);
////		logger.info(" pageable: "+pageable);
		return null;
	}
	
	public Mail saveMail(Mail mail) {
		
		return mailRepository.save(mail);
		
	}

}
