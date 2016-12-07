package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.Forum;
import com.niit.collabrationbackend.Model.ForumComment;

public interface ForumDao {
	
	public boolean saveForum(Forum forum);
	
	public List<Forum> getAllForums();
	
	public Forum getForumById(String forumId);
	
	public boolean forumlikes(String id);
	
	public boolean forumdislikes(String id);
	
	public boolean saveForumComment(ForumComment forumComment);
	
	public List<ForumComment> getAllForumComment(String forumId);

/*	public boolean removeForum(String forumId);
	
	
	
	public List<Forum> forumListByUserId(String userId);
	

	

	
	public boolean saveForumComment(ForumComment forumComment);
	
	public List<ForumComment> getAllCommentByForumId(String forumId);*/
}
