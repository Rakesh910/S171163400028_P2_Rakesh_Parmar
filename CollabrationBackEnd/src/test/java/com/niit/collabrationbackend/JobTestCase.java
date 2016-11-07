package com.niit.collabrationbackend;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.collabrationbackend.Dao.JobOpportunitiesDao;
import com.niit.collabrationbackend.Model.JobOpportunities;

public class JobTestCase {
	
	@Autowired
	JobOpportunitiesDao jobDao;
	
	@Autowired
	JobOpportunities job;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabrationbackend");
		context.refresh();
		jobDao = (JobOpportunitiesDao) context.getBean("jobDao");
		job = (JobOpportunities) context.getBean("job");
	}
}
