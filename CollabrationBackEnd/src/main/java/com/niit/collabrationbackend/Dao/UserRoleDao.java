package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.UserRole;

public interface UserRoleDao {
	
	public List<UserRole> getAllUserRoles();
	
	public boolean saveUserRole(UserRole userRole);
	
	public boolean updateUserRole(UserRole userRole);
	
	public UserRole getRoleByRoleId(int id);
	
	public boolean removeUserRole(int id);

}
