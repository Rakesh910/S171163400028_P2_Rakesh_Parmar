package com.niit.collabrationbackend.Model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Blog extends BaseDomain{
	@Id
	private String blogId;
	
	@NotBlank(message="Please Enter Blog Title")
	private String blogTitle;
	
	@NotBlank(message="Please Enter Blog Description")
	private String blogDescription;
	
	private Date blogCreatedAt;
	
	private Date blogModifiedAt;
	
	private char approvalStatus;
	
	private char blogStatus;
	
	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public Date getBlogCreatedAt() {
		return blogCreatedAt;
	}

	public void setBlogCreatedAt(Date blogCreatedAt) {
		this.blogCreatedAt = blogCreatedAt;
	}

	public Date getBlogModifiedAt() {
		return blogModifiedAt;
	}

	public void setBlogModifiedAt(Date blogModifiedAt) {
		this.blogModifiedAt = blogModifiedAt;
	}

	public char getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(char approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public char getBlogStatus() {
		return blogStatus;
	}

	public void setBlogStatus(char blogStatus) {
		this.blogStatus = blogStatus;
	}
	public Blog() {
		this.blogId = "BLG" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}

}
