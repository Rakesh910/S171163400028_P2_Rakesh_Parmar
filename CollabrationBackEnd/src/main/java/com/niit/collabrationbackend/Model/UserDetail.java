package com.niit.collabrationbackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class UserDetail {

	@Id
	private String userId;
	
	@NotBlank(message="Please Enter First Name")
	private String firstName;
	
	@NotBlank(message="Please Enter Last Name")
	private String lastName;
	
	@NotBlank(message="Please Enter Email Address")
	@Email(message="Please Enter Valid Email Address")
	private String emailId;
	
	@Pattern(regexp="(^$|[0-9]{10})",message="Enter a 10 digit valid phone no")
	@NotBlank(message="Please Enter Contact Number")
	private String contactNo;
	
	@NotBlank(message="Password should be filled")
	@Length(min=8,message="Password should have minimum 8 characters")
	private String password;
	
	private char approveStatus;
	
	private char accountStatus;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(char approveStatus) {
		this.approveStatus = approveStatus;
	}

	public char getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(char accountStatus) {
		this.accountStatus = accountStatus;
	}

	public UserDetail() {
		this.userId = "USR" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
	
}
