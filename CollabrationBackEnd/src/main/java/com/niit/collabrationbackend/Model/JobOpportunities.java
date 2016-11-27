package com.niit.collabrationbackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class JobOpportunities extends BaseDomain{
	
	@Id
	private String jobId;
	
	@NotBlank(message="Please Enter Job Title")
	private String jobTitle;
	
	@NotBlank(message="Please Enter JOB Description")
	private String jobDescription;
	
	private String jobRole;
	
	private String postedOn;
	
	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	private String jobLocation;
	
	private String contact;
	
	private String email;
	
	private char jobStatus;
	
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public char getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(char jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JobOpportunities() {
		this.jobId = "JOB" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}

}
