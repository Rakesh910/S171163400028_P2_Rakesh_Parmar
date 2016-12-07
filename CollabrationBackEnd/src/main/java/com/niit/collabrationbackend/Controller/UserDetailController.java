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
				log.debug("**********Ending of Method listAllUsers**********");
				return new ResponseEntity<List<UserDetail>>(userslist,HttpStatus.OK);
			}
		}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/PendingUsers/
				@RequestMapping(value = "/UserPages/PendingUsers/", method = RequestMethod.GET)
				public ResponseEntity<List<UserDetail>> listPendingUsers(){
					log.debug("**********Starting of Method listPendingUsers**********");
					List<UserDetail> userslist = userDetailDao.getAllUsersForApproval();
					if(userslist.isEmpty()){
						return new ResponseEntity<List<UserDetail>>(userslist,HttpStatus.NO_CONTENT);
					}else{
						log.debug("**********Size found :- "+userslist.size()+"**********");
						log.debug("**********Ending of Method listPendingUsers**********");
						return new ResponseEntity<List<UserDetail>>(userslist,HttpStatus.OK);
					}
				}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/CreateUser/
		@RequestMapping(value = "/UserPages/CreateUser/", method = RequestMethod.POST)
		public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetail){
			log.debug("**********Starting of Method createUser**********");
			if(userDetailDao.userGetById(userDetail.getUserId()) == null){
				userDetail.setAccountStatus('0');
				userDetail.setApproveStatus('P');
				userDetail.setIs_Online('N');
				userDetailDao.saveUser(userDetail);
				log.debug("**********New User Created Successfully**********");
				userDetail = new UserDetail();
				userDetail.setErrorMessage("User Registration Successful. Wait for the Admin Approval.");
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.OK);
			}
			log.debug("**********UserDetail already Exist with ID :-"+userDetail.getUserId()+" **********");
			userDetail.setErrorMessage("UserDetail Already Exist With ID:-"+userDetail.getUserId());
			return new ResponseEntity<UserDetail>(userDetail , HttpStatus.OK);
		}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/UpdateUserDetail/{id}
		@RequestMapping(value = "/UserPages/UpdateUserDetail/", method = RequestMethod.PUT)
		public ResponseEntity<UserDetail> updateUser(@RequestBody UserDetail userDetail){
			log.debug("**********Starting of Method updateUser**********" );
			if(userDetailDao.userGetById(userDetail.getUserId()) == null){
				log.debug("**********UserDetail Does not Exist with this ID :-**********");
				userDetail = new UserDetail();
				userDetail.setErrorCode("404");
				userDetail.setErrorMessage("UserDetail Does not Exist with this ID :-");
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.NOT_FOUND);
			}else{
				userDetailDao.updateUser(userDetail);
				userDetail.setErrorMessage("Your Profile Updated Successfully.");
				log.debug("**********UserDetail Updated Successfully WITH ID:-**********");
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
		
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/GetUser/{id}
		@RequestMapping(value = "/UserPages/GetUser/{id}", method = RequestMethod.GET)
		public ResponseEntity<UserDetail> getUserById(@PathVariable("id") String userId){
			log.debug("**********Starting of Method getUserById**********");
			UserDetail userDetail = userDetailDao.userGetById(userId);
			if(userDetail == null){
				log.debug("**********UserDetail Does not Exist with this ID :-"+userId+"**********");
				userDetail = new UserDetail();
				userDetail.setErrorCode("404");
				userDetail.setErrorMessage("UserDetail Does not Exist with this ID :-"+ userId);
				return new ResponseEntity<UserDetail>(userDetail , HttpStatus.NOT_FOUND);
			}else{
				log.debug("**********UserDetail Found WITH ID:- "+userId+"**********");
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
				session.setAttribute("userName",user.getFirstName()+' '+user.getLastName());
				session.setAttribute("loggedInUserID", user.getUserId());
				userDetailDao.setOnLine(user.getUserId());
			}else{
				user = new UserDetail();
				user.setErrorCode("404");
				user.setErrorMessage("Invaid Credentials...!!!Please Enter Valid Username OR Password.");
			}
			return new ResponseEntity<UserDetail>(user , HttpStatus.OK);
		}	
		
		@RequestMapping(value = "/UserPages/Logout/",method = RequestMethod.GET)
		public ResponseEntity<UserDetail> logout(HttpSession session){
			log.debug("*********Calling Method Logout.");
			/*String loggedInUserID = (String) session.getAttribute("loggedInUserID");
			//SET OFFLINE IS PENDING.*/
			String userId = (String) session.getAttribute("loggedInUserID");
			userDetailDao.setOffLine(userId);
			session.invalidate();
			log.debug("You Successfully Loggouedout");
			return new ResponseEntity<UserDetail>(HttpStatus.OK);
			/*return("You Successfully Loggouedout");*/
		}
		
		//http://localhost:8080/CollabrationBackEnd/UserPages/ApproveUser/{userId}/{status}
		@RequestMapping(value = "/UserPages/ApproveUser/{userId}/{status}", method = RequestMethod.GET)
		public ResponseEntity<UserDetail> approveUser(@PathVariable("userId") String userId,@PathVariable("status") String status){
			log.debug("**********Starting of Method approveUser WITH USER_ID :-**********" + userId);
/*			UserDetail user = userDetailDao.userGetById(userId);
			if(user == null){
				log.debug("**********User Does not Exist with this ID :-"+userId+"**********");
				user = new UserDetail();
				user.setErrorCode("404");
				user.setErrorMessage("User Does not Exist with this ID :-"+userId);
				return new ResponseEntity<UserDetail>(user , HttpStatus.NOT_FOUND);
			}else{
				user.setUserId(userId);*/
				userDetailDao.approveUser(userId, status);
				log.debug("**********Blog Approved Successfully WITH ID:- "+userId+"**********");
				return new ResponseEntity<UserDetail>(HttpStatus.OK);
		//	}
		}
}
