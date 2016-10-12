package com.niit.collabrationbackend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabrationbackend.Dao.UserRoleDao;
import com.niit.collabrationbackend.Model.UserRole;

public class RoleTestCase {
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Autowired
	UserRole userRole;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabrationbackend");
		context.refresh();
		userRoleDao = (UserRoleDao) context.getBean("userRoleDao");
		userRole = (UserRole) context.getBean("userRole");
	}
	
	@Test
	public void addUpdateUserRoleTestCase(){
		userRole.setRoleName("Admin");
		userRole.setStatus('1');
		assertEquals("addUpdateUserRoleTestCase",userRoleDao.userRoleSaveOrUpdate(userRole),true);
	}
	
	@Test
	public void GeUserRoleListTestCase(){
		List<UserRole> usrrole = userRoleDao.getAllUserRoles();
		int noOfRows = usrrole.size();
		assertEquals("GeUserRoleListTestCase",noOfRows,6);
	}
	
	@Test
	public void RemoveUserRoleTestCase(){
		userRole.setRoleId(5);
		assertEquals("RemoveUserRoleTestCase",userRoleDao.removeUserRole(userRole.getRoleId()),true);
	}

}
