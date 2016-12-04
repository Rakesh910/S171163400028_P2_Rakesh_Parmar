'use strict';

app.factory('FriendService',[
           '$http',
           '$q',
           '$rootScope',
           function($http,$q,$rootScope){
        	   console.log("Friend Service...");
        	   
        	   var BASE_URL = 'http://localhost:8080/CollabrationBackEnd'
        	   return{
        		  
        		   fetchAllUsers:function(){
          			 return $http.get(BASE_URL+'/UserPages/Users/')  
          			 .then(
   			   				function(response){
   			   					return response.data;
   			   				},
   			   				function(errResponse){
   			   					console.error('Error While Fatching Friend.');
   			   					return $q.reject(errResponse);
   			   				});
          		   },
          		   
        		   sendFriendRequest: function(friendId){
        			   return $http.get(BASE_URL+'/Friend/AddFriend/'+friendId)
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Creating Friend.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   },
        		   
        		   getMyFriends:function(){
          			 return $http.get(BASE_URL+'/Friend/MyFriends/')  
          			 .then(
   			   				function(response){
   			   					return response.data;
   			   				},
   			   				function(errResponse){
   			   					console.error('Error While Fatching Friend.');
   			   					return $q.reject(errResponse);
   			   				});
          		   },
          		   
          		 approveFriend: function(id,userId){
        			   return $http.get(BASE_URL+'/Friend/AcceptFriend/'+id+'/'+userId)
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Fatching Pending BlogList.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   },
        		   
        		   rejectFriend: function(id,userId){
          			   return $http.get(BASE_URL+'/Friend/RejectFriend/'+id+'/'+userId)
          			   		.then(
          			   				function(response){
          			   					return response.data;
          			   				},
          			   				function(errResponse){
          			   					console.error('Error While Fatching Pending BlogList.');
          			   					return $q.reject(errResponse);
          			   				}
          			   		);
          			   
          		   },        		   
        	   }
}]);