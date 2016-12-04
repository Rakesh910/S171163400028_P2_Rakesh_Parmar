'use strict';

app.factory('AdminService',[
           '$http',
           '$q',
           '$rootScope',
           function($http,$q,$rootScope){
        	   console.log("Admin Service...");
        	   
        	   var BASE_URL = 'http://localhost:8080/CollabrationBackEnd'
        	   return{

        		   fetchAllPendingBlogs: function(){
        			   return $http.get(BASE_URL+'/BlogPages/PendingBlogList/')
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
        	   
        	   approveBlog: function(blogId,status){
    			   return $http.get(BASE_URL+'/BlogPages/ApproveBlog/'+blogId+'/'+status)
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
        	
    		   approveUser: function(userId,status){
        			   return $http.get(BASE_URL+'/UserPages/ApproveUser/'+userId+'/'+status)
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Fatching Pending BlogList.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   }
         }
}]);