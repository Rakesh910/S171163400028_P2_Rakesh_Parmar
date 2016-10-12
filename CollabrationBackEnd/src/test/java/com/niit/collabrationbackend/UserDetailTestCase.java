package com.niit.collabrationbackend;

import static org.junit.Assert.assertEquals;

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
			userDetail.setFirstName("Admin");
			userDetail.setLastName("Admin");
			userDetail.setEmailId("admin@admin.com");
			userDetail.setContactNo("9033562209");
			userDetail.setPassword("Admin123");
			userDetail.setApproveStatus('Y');
			userDetail.setAccountStatus('1');
			assertEquals("addUpdateUserDetail",userDetailDao.saveOrUpdateUserDetail(userDetail),true);
		}
}
