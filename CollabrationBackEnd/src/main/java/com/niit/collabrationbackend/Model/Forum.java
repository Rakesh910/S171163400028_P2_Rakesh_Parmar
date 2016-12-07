package com.niit.collabrationbackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Forum extends BaseDomain{
	
	@Id
	private String forumId;
	
	private String forumTitle;
	
	private String forumContent;
	
	private String forumDate;
	
	private char forumStatus;
	
	private String userId;
	
	private int forumLike;
	
	private int forumDislike;
	
	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumContent() {
		return forumContent;
	}

	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}

	public String getForumDate() {
		return forumDate;
	}

	public void setForumDate(String forumDate) {
		this.forumDate = forumDate;
	}

	public char getForumStatus() {
		return forumStatus;
	}

	public void setForumStatus(char forumStatus) {
		this.forumStatus = forumStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getForumLike() {
		return forumLike;
	}

	public void setForumLike(int forumLike) {
		this.forumLike = forumLike;
	}

	public int getForumDislike() {
		return forumDislike;
	}

	public void setForumDislike(int forumDislike) {
		this.forumDislike = forumDislike;
	}

	public Forum() {
		this.forumId = "FRM" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
}
