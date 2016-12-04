'use strict';

app.controller('BlogController',[
	'$scope',
	'BlogService',
	'UserService',
	'$location',
    '$rootScope',
    '$cookieStore',
    '$http',
	function($scope,BlogService,UserService,$location,$rootScope,$cookieStore,$http){
    	console.log("Blog Controller")
    	
    	var self = this;               
    	
    	self.blog = {
    			blogTitle : '',
    			blogDescription : ''
    	};
    	
    	self.blogs = [];  
    	self.myblogs = [];
    	
    	self.getSelectedBlog = getBlog
    	
    	function getBlog(blogId,status){
    		console.log("->->Getting Blog with ID :-"+blogId+"AND Status :-"+status);
    		BlogService.getBlog(blogId,status)
    			.then(
    					function(d){
    						/*self.blog = d;*/
    						$rootScope.selectedBlog = d;
        					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.selectedBlog;
        					$cookieStore.put('selectedBlog',$rootScope.selectedBlog);
    						console.log("Blog found with Id :-"+blogId+"Navigate to View Blog Page."+self.blog.blogTitle)
    						$location.path('/view_blog');
    					},
    					function(errResponse){
    						console.error('Error While fetching Blogs.');
    					});
    	};
    	
    	self.fetchAllBlogs = function(){
    		BlogService.fetchAllBlogs()
    			.then(function(d){
    						self.blogs = d;
    				},function(errResponse){
    					console.error('Error While Fatching Blogs.');
    			});
    	};
    	
    	self.fetchAllUsers = function(){
    		console.log("FetchAllUsers");
    		UserService.fetchAllUsers()
    			.then(function(d){
    				self.users = d;
    			},function(errResponse){
    				console.error('Error while fetching Users');
    			});
    	};
    	
    	self.getMyBlogList = function(){
    		BlogService.getMyBlogList()
    		.then(function(d){
    			self.myblogs = d;
    		},function(errResponse){
    			console.error('Error While getMyBlogList.');
    		});
    	}
    	    	
    	self.createBlog = function(blog){
    		BlogService.createBlog(blog)
    			.then(
    					function(d){
    				self.blog = d;
    			},
    					function(errResponse){
    					console.error('Error While Creating Blog.');
    			});
    	};
    	
/*    	self.updateBlog = function(blog,blogId){
    		BlogService.updateBlog(blog,blogId)
    			.then(
    					self.fetchAllBlogs,
    					function(errResponse){
    					console.error('Error While Updating Blog.');
    			});
    	};
    	
    	self.deleteBlog = function(blogId){
    		BlogService.deleteBlog(blogId)
    			.then(
    					self.fetchAllBlogs,
    					function(errResponse){
    					console.error('Error While Deleting Blog.');
    			});
    	};*/
    	
    	//calling the Method
    	self.fetchAllBlogs();
    	self.getMyBlogList();
    	self.fetchAllUsers();
    	
    	self.submit = function(){
    		{
    			console.log('Saving New Blog',self.blog);
    			self.blog.userID = $rootScope.currentUser.userId;
    			self.createBlog(self.blog);           			
    		}
    		self.reset();
    	}
    	
    	self.reset = function(){
    		self.blog  = {
    				blogId : '',
        			blogTitle : '',
        			blogDescription : '',
        			blogCreatedAt : '',
        			blogModifiedAt : '',
        			approvalStatus : '',
        			blogStatus : '',
        			errorCode : '',
        			errorMessage : ''
    		};
    		$scope.form.$setPristine();
    	}
}]);