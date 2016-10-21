package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.UserDetail;

public interface UserDetailDao {
		
	public List<UserDetail> getAllUsers();
	
	public List<UserDetail> getAllUsersForApproval();
	
	public boolean saveUser(UserDetail userDetail);
	
	public boolean updateUser(UserDetail userDetail);
	
	public boolean deleteUser(String userId);
	
	public UserDetail isValidUser(String email,String password);
	
	public UserDetail userGetById(String userId);

}
