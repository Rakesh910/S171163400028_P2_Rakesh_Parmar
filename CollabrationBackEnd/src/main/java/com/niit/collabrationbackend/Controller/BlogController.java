package com.niit.collabrationbackend.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabrationbackend.Dao.BlogDao;
import com.niit.collabrationbackend.Model.Blog;

@RestController
public class BlogController {
	
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	BlogDao blogDao;
	
	//http://localhost:8080/CollabrationBackEnd/BlogPages/BlogList/
	@RequestMapping(value = "/BlogPages/BlogList/", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlogs(){
		log.debug("**********Starting of Method listAllBlogs**********");
		List<Blog> blogList = blogDao.getAllBlogs();
		if(blogList.isEmpty()){
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+blogList.size()+"**********");
			log.debug("**********Ending of Method listAllBlogs**********");
			return new ResponseEntity<List<Blog>>(HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/BlogPages/PendingBlogList/
	@RequestMapping(value = "/BlogPages/PendingBlogList/", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listPendingBlogs(){
		log.debug("**********Starting of Method listPendingBlogs**********");
		List<Blog> blogList = blogDao.blogListForApproval();
		if(blogList.isEmpty()){
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+blogList.size()+"**********");
			log.debug("**********Ending of Method listPendingBlogs**********");
			return new ResponseEntity<List<Blog>>(HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/BlogPages/CreateBlog/
	@RequestMapping(value = "/BlogPages/CreateBlog/", method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		log.debug("**********Starting of Method createUser**********");
		if(blogDao.getBlogById(blog.getBlogId()) == null){
			blogDao.saveBlog(blog);
			log.debug("**********New Blog Created Successfully**********");
			return new ResponseEntity<Blog>(blog , HttpStatus.OK);
		}
		log.debug("**********Blog already Exist with ID :-"+blog.getBlogId()+" **********");
		blog.setErrorMessage("Blog Already Exist With ID:-"+blog.getBlogId());
		return new ResponseEntity<Blog>(blog , HttpStatus.OK);
	}
	
	//http://localhost:8080/CollabrationBackEnd/BlogPages/UpdateBlog/{id}
	@RequestMapping(value = "/BlogPages/UpdateBlog/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") String blogId,@RequestBody Blog blog){
		log.debug("**********Starting of Method updateBlog**********" + blogId);
		if(blogDao.getBlogById(blogId) == null){
			log.debug("**********Blog Does not Exist with this ID :-"+blogId+"**********");
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog Does not Exist with this ID :-"+blogId);
			return new ResponseEntity<Blog>(blog , HttpStatus.NOT_FOUND);
		}else{
			blog.setBlogId(blogId);
			blogDao.updateBlog(blog);
			log.debug("**********Blog Updated Successfully WITH ID:- "+blogId+"**********");
			return new ResponseEntity<Blog>(blog , HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/UserPages/RemoveBlog/{id}
	@RequestMapping(value = "/UserPages/RemoveBlog/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> removeBlog(@PathVariable("id") String blogId){
		log.debug("**********Starting of Method removeUser**********");
		Blog blog = blogDao.getBlogById(blogId);
		if(blog == null){
			log.debug("**********Blog Does not Exist with this ID :-"+blogId+"**********");
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog Does not Exist with this ID :-"+ blogId);
			return new ResponseEntity<Blog>(blog , HttpStatus.NOT_FOUND);
		}else{
			blogDao.removeBlog(blogId);
			log.debug("**********Blog Deleted Successfully WITH ID:- "+blogId+"**********");
			return new ResponseEntity<Blog>(blog , HttpStatus.OK);
		}
	}
}
