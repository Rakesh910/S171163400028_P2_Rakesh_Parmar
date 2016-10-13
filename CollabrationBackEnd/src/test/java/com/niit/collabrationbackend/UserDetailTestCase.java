package com.niit.collabrationbackend;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabrationbackend.Dao.UserDetailDao;
import com.niit.collabrationbackend.Model.UserDetail;

public class UserDetailTestCase {

		@Autowired
		UserDetailDao userDetailDao;
		
		@Autowired
		UserDetail userDetail;
		
		AnnotationConfigApplicationContext context;
		
		@Before
		public void init(){
			context = new AnnotationConfigApplicationContext();
			context.scan("com.niit.collabrationbackend");
			context.refresh();
			userDetailDao = (UserDetailDao) context.getBean("userDetailDao");
			userDetail = (UserDetail) context.getBean("userDetail");
		}
		
		@Test
		public void addUpdateUserDetail(){
			userDetail.setUserId("USR4D1356");
			userDetail.setFirstName("Admin");
			userDetail.setLastName("Admin");
			userDetail.setEmailId("admin@admin.com");
			userDetail.setContactNo("9033562209");
			userDetail.setPassword("Admin123");
			userDetail.setApproveStatus('Y');
			userDetail.setAccountStatus('1');
			assertEquals("addUpdateUserDetail",userDetailDao.saveOrUpdateUserDetail(userDetail),true);
		}
		
		@Test
		public void getUserByUserId(){
			userDetail.setUserId("USR4D1356");
			UserDetail u = userDetailDao.userGetById(userDetail.getUserId());
			System.out.println("User Object Found"+u.getUserId());
			assertEquals("getUserByUserId",userDetailDao.userGetById(userDetail.getUserId()).getUserId(),u.getUserId());
		}
		
		@Test
		public void getUserByemailId(){
			userDetail.setEmailId("admin@admin.com");
			UserDetail u = userDetailDao.userGetByEmail(userDetail.getEmailId());
			assertEquals("getUserByemailId",userDetailDao.userGetByEmail(userDetail.getEmailId()).getUserId(),u.getUserId());
		}
		
		@Test
		public void isValidUser(){
			userDetail.setEmailId("admin@admin.com");
			userDetail.setPassword("Admin123");
			UserDetail u = userDetailDao.isValidUser(userDetail.getEmailId(),userDetail.getPassword());
			assertEquals("isValidUser",userDetailDao.isValidUser(userDetail.getEmailId(),userDetail.getPassword()).getUserId(),u.getUserId());
		}
		
		@Test
		public void getUserListForApproval(){
			List<UserDetail> userDetailList = userDetailDao.getUserListForApproval();
			int noOfRows = userDetailList.size();
			assertEquals("getUserListForApproval",noOfRows,1);
		}

		@Test
		public void removeUserDetail(){
			userDetail.setUserId("USR4D1356");
			assertEquals("removeUserDetail",userDetailDao.removeUserDetail(userDetail.getUserId()),true);
		}
}
