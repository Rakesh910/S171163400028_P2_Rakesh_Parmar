'use strict';

app.controller('FriendController',[
            'UserService',
            '$scope',
            'FriendService',
            '$location',
            '$rootScope',
            '$cookieStore',
            '$interval',
            '$http',
            function(UserService,$scope,FriendService,$location,$rootScope,$cookieStore,$interval,$http){
            	console.log("Friend Controller");
            	
            	var self = this;
            	
            	self.friend = {
            			id : '',
            			userId : '',
            			friendId : '',
            			friendStatus : ''
            	};
            	
            	self.friends = [];
            	
            	self.user = {
            			userId : '',
            			firstName : '',
            			lastName : '',
            			emailId : '',
            			contactNo : '',
            			password : '',
            			userRole : '',
            			is_Online : '',
            			errorMessage : ''
            	};
            	
            	self.users = [];
            	
            	self.fetchAllUsers = function(){
            		console.log("FetchAllUsers");
            		UserService.fetchAllUsers()
            			.then(function(d){
            				self.users = d;
            			},function(errResponse){
            				console.error('Error while fetching Users');
            			});
            	};
            	
            	self.sendFriendRequest = sendFriendRequest
            	
            	function sendFriendRequest(friendId){
            		console.log("------>sendFriendRequest<------"+friendId);
            		FriendService.sendFriendRequest(friendId)
            			.then(function(d){
            				alert("Friend Request Sent..!!!");
            				self.friend = d;
            			},function(errResponse){
            				console.error('Error while Sending Friend Request');
            			});
            	}
            	
            	self.getMyFriends = function(){
            		console.log("------>Getting My Friends<------");
            		FriendService.getMyFriends()
            			.then(
            					function(d){
            						self.friends = d;
            						console.log("Got The Friend List")
            					},
            					function(errResponse){
            				console.error('Error While Fetching Friend.');
            			});
            	};
            	
            	self.approveSelectedFriend = approveFriend
            	
            	function approveFriend(id,userId){
            		console.log("->->approveFriend with ID :-"+id+"AND UserId :-"+userId);
            		FriendService.approveFriend(id,userId)
            			.then(
            					self.getMyFriends,
            					function(errResponse){
            						console.error('Error While approveFriend.');
            					});
            	};
            	
            	self.rejectSelectedFriend = rejectFriend
            	
            	function rejectFriend(id,userId){
            		console.log("->->rejectFriend with ID :-"+id+"AND UserId :-"+userId);
            		FriendService.rejectFriend(id,userId)
            			.then(
            					self.getMyFriends,
            					function(errResponse){
            						console.error('Error While rejectFriend.');
            					});
            	};
            	
            	self.fetchAllUsers();
            	self.getMyFriends();
            	
            	
            	var c=0;
                var timer=$interval(function(){
                    c++;
                    if(c===100)
                      {
                    	self.fetchAllUsers();
                    	self.getMyFriends();
                        c=0;
                      }
                  },100);
            	
            	
}]);