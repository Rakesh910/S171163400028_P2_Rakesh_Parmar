package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.UserDetail;

public interface UserDetailDao {
	
	public boolean saveOrUpdateUserDetail(UserDetail userDetail);
	
	public boolean removeUserDetail(String userId);
	
	public UserDetail userGetById(String userId);
	
	public UserDetail userGetByEmail(String email);
	
	public UserDetail isValidUser(String email,String password);
	
	public List<UserDetail> getUserListForApproval();

}
