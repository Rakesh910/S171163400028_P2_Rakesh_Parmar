package com.niit.collabrationbackend.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabrationbackend.Dao.FriendDao;
import com.niit.collabrationbackend.Model.Friend;
import com.niit.collabrationbackend.Model.UserDetail;

@RestController
public class FriendController {
	private static final Logger log = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDao friendDao;
	
	@Autowired
	Friend friend;
	
	//http://localhost:8080/CollabrationBackEnd/Friend/MyFriends/
		@RequestMapping(value = "/Friend/MyFriends/", method = RequestMethod.GET)
		public ResponseEntity<List<Friend>> getMyFriends(HttpSession session){
			log.debug("**********Calling of Method getMyFriends**********");
			UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
			System.out.println("LOG IN USER ID :-"+loggedInUser.getUserId());
			List<Friend> myFriends = friendDao.getMyFriends(loggedInUser.getUserId());
			if(myFriends.isEmpty()){
				log.debug("**********No Any Friend Found**********");
				/*friend.setErrorCode("404");
				friend.setErrorMessage("You Doesn't have any Friend");
				myFriends.add(friend);*/
			}
				log.debug("**********Size found :- "+myFriends.size()+"**********");
				log.debug("**********Ending of Method getMyFriends**********");
				return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		}
		
		//http://localhost:8080/CollabrationBackEnd/Friend/AddFriend/{friendId}
		@RequestMapping(value = "/Friend/AddFriend/{friendId}", method = RequestMethod.GET)
		public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId,HttpSession session){
			log.debug("**********Starting of Method sendFriendRequest**********");
			UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
				Friend friend = new Friend();
				friend.setUserId(loggedInUser.getUserId());
				friend.setFriendId(friendId);
				friend.setFriendStatus('P');
				friendDao.save(friend);
				log.debug("**********sendFriendRequest Successfully**********");
			return new ResponseEntity<Friend>(friend , HttpStatus.OK);
		}
				
		//http://localhost:8080/CollabrationBackEnd/Friend/AcceptFriend/{id}/{userId}
		@RequestMapping(value = "/Friend/AcceptFriend/{id}/{userId}", method = RequestMethod.GET)
		public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("id") String Id,@PathVariable("userId") String userId,HttpSession session){
			log.debug("**********Starting of Method acceptFriendRequest**********");
			UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
				friend.setId(Id);
				friend.setFriendId(loggedInUser.getUserId());
				friend.setUserId(userId);
				friend.setFriendStatus('A');
				friendDao.update(friend);
				log.debug("**********acceptFriendRequest Successfully**********");
			return new ResponseEntity<Friend>(friend , HttpStatus.OK);
		}
		
		//http://localhost:8080/CollabrationBackEnd/Friend/RejectFriend/{id}/{userId}
		@RequestMapping(value = "/Friend/RejectFriend/{id}/{userId}", method = RequestMethod.GET)
		public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("id") String Id,@PathVariable("userId") String userId,HttpSession session){
			log.debug("**********Starting of Method RejectFriendRequest**********");
			UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
				friend.setId(Id);
				friend.setFriendId(loggedInUser.getUserId());
				friend.setUserId(userId);
				friend.setFriendStatus('R');
				friendDao.update(friend);
				log.debug("**********RejectFriendRequest Successfully**********");
			return new ResponseEntity<Friend>(friend , HttpStatus.OK);
		}
		
		//http://localhost:8080/CollabrationBackEnd/Friend/UpdateFriend/{friendId}
		@RequestMapping(value = "/Friend/UpdateFriend/{friendId}", method = RequestMethod.GET)
		public ResponseEntity<Friend> unFriend(@PathVariable("friendId") String friendId,HttpSession session){
			log.debug("**********Starting of Method unFriend**********");
			UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
				friend.setUserId(loggedInUser.getUserId());
				friend.setFriendId(friendId);
				friend.setFriendStatus('U');
				friendDao.update(friend);
				log.debug("**********unFriend Successfully**********");
			return new ResponseEntity<Friend>(friend , HttpStatus.OK);
		}
		
		
		//http://localhost:8080/CollabrationBackEnd/Friend/GetMyFriendRequest/
		@RequestMapping(value = "/Friend/GetMyFriendRequest/", method = RequestMethod.GET)
		public ResponseEntity<List<Friend>> getMyFriendRequest(HttpSession session){
			log.debug("**********Calling of Method getMyFriendRequest**********");
			UserDetail loggedInUser = (UserDetail) session.getAttribute("loggedInUser");
			List<Friend> myFriendrequest = friendDao.getNewFriendRequests(loggedInUser.getUserId());
			if(myFriendrequest.isEmpty()){
				log.debug("**********No Any New Friend Request Found**********");
				return new ResponseEntity<List<Friend>>(myFriendrequest,HttpStatus.NO_CONTENT);
			}else{
				log.debug("**********Size found :- "+myFriendrequest.size()+"**********");
				log.debug("**********Ending of Method getMyFriendRequest**********");
				return new ResponseEntity<List<Friend>>(myFriendrequest,HttpStatus.OK);
			}
		}
		
}
