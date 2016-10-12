package com.niit.collabrationbackend.Dao;

import com.niit.collabrationbackend.Model.UserDetail;

public interface UserDetailDao {
	
	public boolean saveOrUpdateUserDetail(UserDetail userDetail);
	
	public boolean removeUserDetail(int id);
	
	public UserDetail userGetById(String userId);
	
	public UserDetail userGetByEmail(String email);
	
	public UserDetail isValidUser(String email,String password);

}
