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
public class Blog extends BaseDomain{
	@Id
	private String blogId;
	
	private String userId;
	
	@NotBlank(message="Please Enter Blog Title")
	private String blogTitle;
	
	@NotBlank(message="Please Enter Blog Description")
	private String blogDescription;
	
	private String blogCreatedAt;
	
	private String blogModifiedAt;
	
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

	public String getBlogCreatedAt() {
		return blogCreatedAt;
	}

	public void setBlogCreatedAt(String blogCreatedAt) {
		this.blogCreatedAt = blogCreatedAt;
	}

	public String getBlogModifiedAt() {
		return blogModifiedAt;
	}

	public void setBlogModifiedAt(String blogModifiedAt) {
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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Blog() {
		this.blogId = "BLG" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}

}
