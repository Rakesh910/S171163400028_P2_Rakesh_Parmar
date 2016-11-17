package com.niit.collabrationbackend.Controller;

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

import com.niit.collabrationbackend.Dao.UserDetailDao;
import com.niit.collabrationbackend.Model.UserDetail;

@RestController
public class UserDetailController {
	private static final Logger log = LoggerFactory.getLogger(UserDetailController.class);
	
	@Autowired
	UserDetailDao userDetailDao;
	
	//http://localhost:8080/CollabrationBackEnd/UserPages/Users/
		@RequestMapping(value = "/UserPages/Users/", method = RequestMethod.GET)
		public ResponseEntity<List<UserDetail>> listAllUsers(){
			log.debug("**********Starting of Method listAllUsers**********");
			List<UserDetail> userslist = userDetailDao.getAllUsers();
			if(userslist.isEmpty()){
				return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);
			}else{
				log.debug("**********Size found :- "+userslist.size()+"**********");
				log.debug("**********Ending of Method listAllRoles**********");
				return new ResponseEntity<List<UserDetail>>(HttpStatus.OK);
			}
		}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/PendingUsers/
				@RequestMapping(value = "/UserPages/PendingUsers/", method = RequestMethod.GET)
				public ResponseEntity<List<UserDetail>> listPendingUsers(){
					log.debug("**********Starting of Method listPendingUsers**********");
					List<UserDetail> userslist = userDetailDao.getAllUsersForApproval();
					if(userslist.isEmpty()){
						return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);
					}else{
						log.debug("**********Size found :- "+userslist.size()+"**********");
						log.debug("**********Ending of Method listPendingUsers**********");
						return new ResponseEntity<List<UserDetail>>(HttpStatus.OK);
					}
				}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/CreateUser/
		@RequestMapping(value = "/UserPages/CreateUser/", method = RequestMethod.POST)
		public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetail){
			log.debug("**********Starting of Method createUser**********");
			if(userDetailDao.userGetById(userDetail.getUserId()) == null){
				userDetail.setAccountStatus('0');
				userDetail.setApproveStatus('P');
				userDetailDao.saveUser(userDetail);
				log.debug("**********New User Created Successfully**********");
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.OK);
			}
			log.debug("**********UserDetail already Exist with ID :-"+userDetail.getUserId()+" **********");
			userDetail.setErrorMessage("UserDetail Already Exist With ID:-"+userDetail.getUserId());
			return new ResponseEntity<UserDetail>(userDetail , HttpStatus.OK);
		}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/UpdateUserDetail/{id}
		@RequestMapping(value = "/UserPages/UpdateUserDetail/{id}", method = RequestMethod.PUT)
		public ResponseEntity<UserDetail> updateUser(@PathVariable("id") String userId,@RequestBody UserDetail userDetail){
			log.debug("**********Starting of Method updateUser**********" + userId);
			if(userDetailDao.userGetById(userId) == null){
				log.debug("**********UserDetail Does not Exist with this ID :-"+userId+"**********");
				userDetail = new UserDetail();
				userDetail.setErrorCode("404");
				userDetail.setErrorMessage("UserDetail Does not Exist with this ID :-"+userId);
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.NOT_FOUND);
			}else{
				userDetail.setUserId(userId);
				userDetailDao.updateUser(userDetail);
				log.debug("**********UserDetail Updated Successfully WITH ID:- "+userId+"**********");
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.OK);
			}
		}
		
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/RemoveAccount/{id}
		@RequestMapping(value = "/UserPages/RemoveAccount/{id}", method = RequestMethod.PUT)
		public ResponseEntity<UserDetail> removeUser(@PathVariable("id") String userId){
			log.debug("**********Starting of Method removeUser**********");
			UserDetail userDetail = userDetailDao.userGetById(userId);
			if(userDetail == null){
				log.debug("**********UserDetail Does not Exist with this ID :-"+userId+"**********");
				userDetail = new UserDetail();
				userDetail.setErrorCode("404");
				userDetail.setErrorMessage("UserDetail Does not Exist with this ID :-"+ userId);
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.NOT_FOUND);
			}else{
				userDetailDao.deleteUser(userId);
				log.debug("**********UserDetail Deleted Successfully WITH ID:- "+userId+"**********");
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.OK);
			}
		}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/Authentication/
		@RequestMapping(value = "/UserPages/Authentication/", method = RequestMethod.POST)
		public ResponseEntity<UserDetail> authentication(@RequestBody UserDetail userDetail,HttpSession session){
			System.out.println("***************************************"+userDetail.getEmailId());
			log.debug("**********Calling Method Authentication.**********");
			UserDetail user;
			
			user = userDetailDao.isValidUser(userDetail.getEmailId(), userDetail.getPassword());
			if(user != null){
				log.debug("**********User Exist With Given Credentials.**********");
				session.setAttribute("loggedInUser",user);
				session.setAttribute("loggedInUserID", user.getUserId());
			}else{
				user = new UserDetail();
				user.setErrorCode("404");
				user.setErrorMessage("Invaid Credentials");
			}
			return new ResponseEntity<UserDetail>(user , HttpStatus.OK);
		}	
		
		@RequestMapping(value = "/UserPages/Logout/",method = RequestMethod.GET)
		public ResponseEntity<UserDetail> logout(HttpSession session){
			log.debug("*********Calling Method Logout.");
			/*String loggedInUserID = (String) session.getAttribute("loggedInUserID");
			//SET OFFLINE IS PENDING.*/
			session.invalidate();
			log.debug("You Successfully Loggouedout");
			return new ResponseEntity<UserDetail>(HttpStatus.OK);
			/*return("You Successfully Loggouedout");*/
		}
}
