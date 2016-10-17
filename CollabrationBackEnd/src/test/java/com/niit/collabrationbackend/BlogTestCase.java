package com.niit.collabrationbackend;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.collabrationbackend.Dao.BlogDao;
import com.niit.collabrationbackend.Model.Blog;

public class BlogTestCase {
	
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	Blog blog;
	
	AnnotationConfigApplicationContext context;
	
	/*@Before
	public void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabrationbackend");
		context.refresh();
		blogDao = (BlogDao) context.getBean("blogDao");
		blog = (Blog) context.getBean("blog");
	}

	@Test
	public void addUpdateBlog(){
		blog.setBlogTitle("TestBlog");
		blog.setBlogDescription("This is test Blog");
		
	
	}*/

}
