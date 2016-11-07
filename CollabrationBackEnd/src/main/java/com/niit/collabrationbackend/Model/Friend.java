package com.niit.collabrationbackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Friend {
	
	@Id
	private String id;
	
	private String friendId;
	
	private String userId;
	
	private char friendStatus;
	
	private char is_Online;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public char getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(char friendStatus) {
		this.friendStatus = friendStatus;
	}

	public char getIs_Online() {
		return is_Online;
	}

	public void setIs_Online(char is_Online) {
		this.is_Online = is_Online;
	}

	public Friend() {
		this.id = "FRN" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
}
