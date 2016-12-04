package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.Friend;

public interface FriendDao {
	
	public List<Friend> getMyFriends(String userId);
	
	public Friend get(String userId,String friendId);
	
	public boolean save(Friend friend);
	
	public boolean update(Friend friend);
	
	public boolean remove(String userId,String friendId);
	
	public List<Friend> getNewFriendRequests(String userId);
	


}
