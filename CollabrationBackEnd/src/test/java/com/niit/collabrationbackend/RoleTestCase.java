package com.niit.collabrationbackend;

import static org.junit.Assert.*;
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
		userRole.setRoleId(5);
		userRole.setRoleName("Super");
		userRole.setStatus('Y');
		assertEquals("addUpdateUserRoleTestCase",userRoleDao.userRoleSaveOrUpdate(userRole),true);
	}
	

}
