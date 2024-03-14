package com.rt.pot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pot_contact_details")
public class Contact {

	@Id
	@SequenceGenerator(name = "contact_id", sequenceName = "contact_id", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(generator = "contact_id", strategy = GenerationType.SEQUENCE)
	private Integer contactId;

	@Email(message = "Only Accept Email Type")
	@Column(name = "email_id",unique = true,nullable = false)
	private String emailId;

	@Column(name = "mobile_number",unique = true,nullable = false)
	private Long mobileNumber;

	@OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
	private Contractor contractor;

}
