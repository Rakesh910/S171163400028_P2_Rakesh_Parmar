package com.niit.collabrationbackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class AppliedJobs {
	
	@Id
	private String id;
	
	private String jobId;
	
	private String userId;
	
	private char jobStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public char getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(char jobStatus) {
		this.jobStatus = jobStatus;
	}

	public AppliedJobs() {
		this.id = "REF" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
}
