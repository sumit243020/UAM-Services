package  com.auth.uam.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "PERMISSION")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long permissionId;

	private String code;

	private String status;

	private String createdBy;

	private LocalDateTime creationDate;

	private Long lastUpdateLogin;

	private String lastUpdatedBy;

	private LocalDateTime lastUpdatedDate;

	private String isDefault;

	private Boolean isDeleted;

	private String discription;
	

}