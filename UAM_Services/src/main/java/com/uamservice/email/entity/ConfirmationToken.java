package com.uamservice.email.entity;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.uamservice.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {

	public ConfirmationToken(User userEntity) {
		 this.confirmationToken = UUID.randomUUID().toString();

	        // Set the user for whom the token is generated
	        this.user = userEntity;
	        
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private Long tokenId;

	@Column(name = "confirmation_token")
	private String confirmationToken;

	private Date createdDate;
    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }
	
	@OneToOne
    @JoinColumn(name ="userId")
    private User user;

}
