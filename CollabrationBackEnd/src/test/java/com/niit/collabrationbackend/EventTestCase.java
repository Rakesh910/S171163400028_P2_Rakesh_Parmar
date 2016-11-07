package com.niit.collabrationbackend;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.collabrationbackend.Dao.EventDao;
import com.niit.collabrationbackend.Model.Event;

public class EventTestCase {
	
	@Autowired
	EventDao eventDao;
	
	@Autowired
	Event event;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabrationbackend");
		context.refresh();
		eventDao = (EventDao) context.getBean("eventDao");
		event = (Event) context.getBean("event");
	}

	

}
