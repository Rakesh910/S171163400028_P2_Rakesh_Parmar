package com.niit.collabrationbackend.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.collabrationbackend.Dao.ForumDao;
import com.niit.collabrationbackend.Model.Forum;
import com.niit.collabrationbackend.Model.ForumComment;
import com.niit.collabrationbackend.Model.UserDetail;

@RestController
public class ForumController {
	
	private static final Logger log = LoggerFactory.getLogger(ForumController.class);
	
	@Autowired
	ForumDao forumDao;
	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/ForumList/
	@RequestMapping(value = "/ForumPages/ForumList/", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listAllForums(){
		log.debug("**********Starting of Method listAllForums**********");
		List<Forum> forumList = forumDao.getAllForums();
		if(forumList.isEmpty()){
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+forumList.size()+"**********");
			log.debug("**********Ending of Method listAllForums**********");
			return new ResponseEntity<List<Forum>>(forumList,HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/CommentList/{forumId}
	@RequestMapping(value = "/ForumPages/CommentList/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> listForumComment(@PathVariable("forumId") String forumId){
		log.debug("**********Starting of Method listForumComment**********");
		List<ForumComment> forumList = forumDao.getAllForumComment(forumId);
		if(forumList.isEmpty()){
			return new ResponseEntity<List<ForumComment>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+forumList.size()+"**********");
			log.debug("**********Ending of Method listForumComment**********");
			return new ResponseEntity<List<ForumComment>>(forumList,HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/CreateForum/
		@RequestMapping(value = "/ForumPages/CreateForum/", method = RequestMethod.POST)
		public ResponseEntity<Forum> createForum(@RequestBody Forum forum,HttpSession session){
			log.debug("**********Starting of Method createUser**********");
			if(forumDao.getForumById(forum.getForumId()) == null){

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date date = new Date();
	            String forumCreatedAt = (dateFormat.format(date));
	            
	            UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
	            forum.setUserId(loggedInUser.getUserId());
	            forum.setForumDate(forumCreatedAt);
	            forum.setForumStatus('1');
	            
				forumDao.saveForum(forum);
				log.debug("**********New Forum Created Successfully**********");
				forum = new Forum();
				forum.setErrorMessage("Forum Created Successfully..!!!Wait for the Admin Approval.");
				return new ResponseEntity<Forum>(forum , HttpStatus.OK);
			}
			log.debug("**********Forum already Exist with ID :-"+forum.getForumId()+" **********");
			forum.setErrorMessage("Forum Already Exist With ID:-"+forum.getForumId());
			return new ResponseEntity<Forum>(forum , HttpStatus.OK);
		}
	
		
		//http://localhost:8080/CollabrationBackEnd/ForumPages/Like/{forumId}
		@RequestMapping(value = "/ForumPages/Like/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Forum> likeForum(@PathVariable("id") String forumId){
			log.debug("**********Starting of Method updateForum**********" + forumId);
			if(forumDao.getForumById(forumId) != null){
				forumDao.forumlikes(forumId);
				log.debug("**********Forum Updated Successfully WITH ID:- "+forumId+"**********");
				return new ResponseEntity<Forum>(HttpStatus.OK);
			}
			return null;
		}
		
		//http://localhost:8080/CollabrationBackEnd/ForumPages/DisLike/{forumId}
		@RequestMapping(value = "/ForumPages/DisLike/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Forum> disLikeForum(@PathVariable("id") String forumId){
			log.debug("**********Starting of Method updateForum**********" + forumId);
			if(forumDao.getForumById(forumId) != null){
				forumDao.forumdislikes(forumId);
				log.debug("**********Forum Updated Successfully WITH ID:- "+forumId+"**********");
				return new ResponseEntity<Forum>(HttpStatus.OK);
			}
			return null;
		}
		
		//http://localhost:8080/CollabrationBackEnd/ForumPages/DisLike/{forumId}
		@RequestMapping(value = "/ForumPages/GetForumById/{id}",method = RequestMethod.GET)
		public ResponseEntity<Forum> getForumById(@PathVariable("id") String forumId){
			log.debug("**********Starting of Method getForumById**********");
			Forum forum = forumDao.getForumById(forumId); //Send Status in URL .***
			if(forum == null){
				log.debug("**********Forum Does not Exist with this ID :-"+forumId+"**********");
				forum = new Forum();
				forum.setErrorCode("404");
				forum.setErrorMessage("Forum Does not Exist with this ID :-"+ forumId);
				return new ResponseEntity<Forum>(forum , HttpStatus.NOT_FOUND);
			}else{
				log.debug("**********Forum Found Successfully WITH ID:- "+forumId+"**********");
				return new ResponseEntity<Forum>(forum , HttpStatus.OK);
			}
		}
		
		//http://localhost:8080/CollabrationBackEnd/ForumPages/SaveForumComment/
		@RequestMapping(value = "/ForumPages/SaveForumComment/", method = RequestMethod.POST)
		public ResponseEntity<ForumComment> saveForumComment(@RequestBody ForumComment forumComment ,HttpSession session){
			log.debug("**********Starting of Method saveForumComment**********");
			ForumComment frmComment = new ForumComment();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String forumCreatedAt = (dateFormat.format(date));
            
            frmComment.setCommentDate(forumCreatedAt);
			String loggedInUser = (String) session.getAttribute("userName");
			frmComment.setUsername(loggedInUser);
			frmComment.setForumId(forumComment.getForumId());
			frmComment.setTextComment(forumComment.getTextComment());
			forumDao.saveForumComment(frmComment);
			log.debug("**********Ending of Method saveForumComment**********");
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}
		
	/*//http://localhost:8080/CollabrationBackEnd/ForumPages/getMyForumList/	
	@RequestMapping(value = "/ForumPages/getMyForumList/", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> getMyForumList(HttpSession session){
		log.debug("**********Starting of Method getMyForumList**********");
		UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
		List<Forum> forumList = forumDao.forumListByUserId(loggedInUser.getUserId());
		if(forumList.isEmpty()){
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+forumList.size()+"**********");
			log.debug("**********Ending of Method getMyForumList**********");
			return new ResponseEntity<List<Forum>>(forumList,HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/PendingForumList/
	@RequestMapping(value = "/ForumPages/PendingForumList/", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listPendingForums(){
		log.debug("**********Starting of Method listPendingForums**********");
		List<Forum> forumList = forumDao.forumListForApproval();
		if(forumList.isEmpty()){
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+forumList.size()+"**********");
			log.debug("**********Ending of Method listPendingForums**********");
			return new ResponseEntity<List<Forum>>(forumList,HttpStatus.OK);
		}
	}
	
	
	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/UpdateForum/{id}
	@RequestMapping(value = "/ForumPages/UpdateForum/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@PathVariable("id") String forumId,@RequestBody Forum forum){
		log.debug("**********Starting of Method updateForum**********" + forumId);
		if(forumDao.getForumById(forumId,"1") == null){
			log.debug("**********Forum Does not Exist with this ID :-"+forumId+"**********");
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum Does not Exist with this ID :-"+forumId);
			return new ResponseEntity<Forum>(forum , HttpStatus.NOT_FOUND);
		}else{
			forum.setForumId(forumId);
			forumDao.updateForum(forum);
			log.debug("**********Forum Updated Successfully WITH ID:- "+forumId+"**********");
			return new ResponseEntity<Forum>(forum , HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/RemoveForum/{id}
	@RequestMapping(value = "/ForumPages/RemoveForum/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Forum> removeForum(@PathVariable("id") String forumId){
		log.debug("**********Starting of Method removeUser**********");
		Forum forum = forumDao.getForumById(forumId,"1");
		if(forum == null){
			log.debug("**********Forum Does not Exist with this ID :-"+forumId+"**********");
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum Does not Exist with this ID :-"+ forumId);
			return new ResponseEntity<Forum>(forum , HttpStatus.NOT_FOUND);
		}else{
			forumDao.removeForum(forumId);
			log.debug("**********Forum Deleted Successfully WITH ID:- "+forumId+"**********");
			return new ResponseEntity<Forum>(forum , HttpStatus.OK);
		}
	}


	
	//http://localhost:8080/CollabrationBackEnd/ForumPages/UpdateForum/{id}
	@RequestMapping(value = "/ForumPages/ApproveForum/{forumId}/{status}", method = RequestMethod.GET)
	public ResponseEntity<Forum> approveForum(@PathVariable("forumId") String forumId,@PathVariable("status") String status){
		log.debug("**********Starting of Method approveForum WITH BLOG_ID :-**********" + forumId);
		Forum forum = forumDao.getForumById(forumId,"0");
		if(forum == null){
			log.debug("**********Forum Does not Exist with this ID :-"+forumId+"**********");
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum Does not Exist with this ID :-"+forumId);
			return new ResponseEntity<Forum>(forum , HttpStatus.NOT_FOUND);
		}else{
			forum.setForumId(forumId);
			forumDao.approveForum(forumId,status);
			log.debug("**********Forum Approved Successfully WITH ID:- "+forumId+"**********");
			return new ResponseEntity<Forum>(forum , HttpStatus.OK);
		}
	}
*/}
