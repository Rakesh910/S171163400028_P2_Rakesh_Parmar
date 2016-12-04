'use strict';

app.factory('BlogService',[
           '$http',
           '$q',
           '$rootScope',
           function($http,$q,$rootScope){
        	   console.log("Blog Service...");
        	   
        	   var BASE_URL = 'http://localhost:8080/CollabrationBackEnd'
        	   return{
        		   
        		   fetchAllBlogs: function(){
        			   return $http.get(BASE_URL+'/BlogPages/BlogList/')
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Fatching BlogList.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   },
        		   
        		   getMyBlogList: function(){
        			   return $http.get(BASE_URL+'/BlogPages/getMyBlogList/')
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Fatching BlogList.');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        			   
        		   },
        		   
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
        		   createBlog: function(blog){
        			   return $http.post(BASE_URL+'/BlogPages/CreateBlog/',blog)
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Creating User');
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        		   },
        		   
        		   getBlog: function(blogId,status){
        			   return $http.get(BASE_URL+'/BlogPages/GetBlogById/'+blogId+'/'+status)
        			   		.then(
        			   				function(response){
        			   					return response.data;
        			   				},
        			   				function(errResponse){
        			   					console.error('Error While Getting Blog By Id :-'+blogId);
        			   					return $q.reject(errResponse);
        			   				}
        			   		);
        		   },
        	   }
}]);