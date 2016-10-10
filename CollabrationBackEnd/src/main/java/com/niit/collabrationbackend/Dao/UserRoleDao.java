package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.UserRole;

public interface UserRoleDao {
	
	public List<UserRole> getAllUserRoles();
	
	public boolean userRoleSaveOrUpdate(UserRole userRole);
	
	public boolean removeUserRole(int id);

}
