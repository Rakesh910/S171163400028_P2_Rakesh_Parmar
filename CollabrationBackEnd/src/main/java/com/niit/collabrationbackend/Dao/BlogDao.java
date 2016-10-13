package com.niit.collabrationbackend.Dao;

import java.util.List;
import com.niit.collabrationbackend.Model.Blog;

public interface BlogDao {
	
	public boolean blogSaveOrUpdate(Blog blog);
	
	public boolean removeBlog(String blogId);
	
	public List<Blog> blogListByUserId(String userId);
	
	public List<Blog> blogListForApproval();
	
	public Blog getBlogById(String blogId);

}
