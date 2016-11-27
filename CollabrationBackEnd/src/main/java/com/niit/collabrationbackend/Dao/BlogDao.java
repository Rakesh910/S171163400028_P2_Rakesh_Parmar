package com.niit.collabrationbackend.Dao;

import java.util.List;
import com.niit.collabrationbackend.Model.Blog;

public interface BlogDao {
	
	public boolean saveBlog(Blog blog);
	
	public boolean updateBlog(Blog blog);
	
	public boolean removeBlog(String blogId);
	
	public List<Blog> getAllBlogs();
	
	public List<Blog> blogListByUserId(String userId);
	
	public List<Blog> blogListForApproval();
	
	public Blog getBlogById(String blogId,String status);
	
	public boolean approveBlog(String blogId , String status);
}
