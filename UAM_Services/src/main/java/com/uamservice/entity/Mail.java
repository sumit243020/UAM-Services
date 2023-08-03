package com.uamservice.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@ToString
public class Mail {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mailId;
	
	private String emailTemplate;

	private String emailStatus;

	private String emailBody;

	private String emailSubject;

	private String sendTo;

	private String sendCc;

	@Column(name = "TRANSACTIONAL_ID")
	private String transactionId;

	private String attachmentPath;

	private Long failureCount;

	private LocalDateTime creationDate;

	private LocalDateTime lastUpdateDate;

	private String createdBy;

	private String lastUpdateLogin;

	private String lastUpdatedBy;

	private String sendBcc;

//	@OneToOne(cascade = CascadeType.MERGE)
//	@JoinColumn(name = "customerId"	)
//	private Customer customer;

}
