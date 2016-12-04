'use strict';

app.controller('AdminController',[
	'$scope',
	'AdminService',
	'BlogService',
	'UserService',
	'$location',
	'$rootScope',
    '$cookieStore',
    '$http',
	function($scope,AdminService,BlogService,UserService,$location,$rootScope,$cookieStore,$http){
    	console.log("Admin Controller")
    	var self = this; 
    	
    	self.pendingBlogs = [];
    	self.users = [];
    	self.pendingUsers = [];

    	self.fetchAllPendingBlogs = function(){
    		AdminService.fetchAllPendingBlogs()
    			.then(
    					function(d){
    						self.pendingBlogs = d;
    					},
    					function(errResponse){
    						console.error('Error While Fatching Pending Blogs.');
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
    	
    	self.fetchAllPendingUsers = function(){
    		console.log("FetchAll Pending Users List.");
    		UserService.fetchAllPendingUsers()
    			.then(function(d){
    				self.pendingUsers = d;
    				self.nop = d.length / $rootScope.pageSize;
    			},function(errResponse){
    				console.error('Error while fetching Users');
    			});
    	};
    	
     	//calling the Method
    	self.fetchAllPendingBlogs();
    	self.fetchAllUsers();
    	self.fetchAllPendingUsers();
    	
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
    						$location.path('/view_blog');
    					},
    					function(errResponse){
    						console.error('Error While fetching Blogs.');
    					});
    	};
    	
    	
    	self.SelectedapproveBlog = approveBlog
    	
    	function approveBlog(blogId,status){
    		console.log("->->approveBlog Blog with ID :-"+blogId+" "+status);
    		AdminService.approveBlog(blogId,status)
    			.then(
    					self.fetchAllPendingBlogs,
    					function(errResponse){
    						console.error('Error While approve Blogs.');
    					});
    	};
    	
    	self.SelectedapproveUser = approveUser
    	
    	function approveUser(userId,status){
    		console.log("->->approveUser User with ID :-"+userId+" "+status);
    		AdminService.approveUser(userId,status)
    			.then(
    					self.fetchAllPendingUsers,
    					function(errResponse){
    						console.error('Error While approve Users.');
    					});
    	};

}]);