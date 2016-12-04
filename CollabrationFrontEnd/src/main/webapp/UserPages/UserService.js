'use strict';

app.factory('UserService',[
           '$http',
           '$q',
           '$rootScope',
           function($http,$q,$rootScope){
        	   console.log("User Service...");
        	   
        	   var BASE_URL = 'http://localhost:8080/CollabrationBackEnd'
        	   return{
        		   
        		   fetchAllUsers: function(){
        			   return $http.get(BASE_URL+'/UserPages/Users/')
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Fatching UserDetail.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   },
        		   
        		   fetchAllPendingUsers: function(){
        			   return $http.get(BASE_URL+'/UserPages/PendingUsers/')
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Fatching Pending UserList.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   },
        		   
        		   createUser: function(user){
        			   return $http.post(BASE_URL+'/UserPages/CreateUser/',user)
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Creating User');
        			   					return $q.reject(errResponse);
        			   				});
        		   },
        		   
        		   authenticate : function(user){
        			   return $http.post(BASE_URL+'/UserPages/Authentication/',user)
        			   		.then(function(response){
    			   				return response.data;
            			   	},
            			   	function(errResponse){
            			   		console.error('Error While Authentication User.');
            			   		return $q.reject(errResponse);
            			   	});

        		   },
        		   
        		   logout : function(){
        			   return $http.get(BASE_URL+'/UserPages/Logout/')
        			   	.then(function(response){
        			   				return response.data;
        			   	},
        			   	function(errResponse){
        			   		console.error('Error While Logging Out');
        			   		return $q.reject(errResponse);
        			   	});
        		   },
        		   
        	   }
}]);