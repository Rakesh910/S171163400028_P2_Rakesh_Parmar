'use strict';

app.controller('UserController',[
            '$scope',
            'UserService',
            '$location',
            '$rootScope',
            '$cookieStore',
            '$http',
            function($scope,UserService,$location,$rootScope,$cookieStore,$http){
            	console.log("User Controller");
            	
            	var self = this;
            	
            	self.user = {
            			firstName : '',
            			lastName : '',
            			emailId : '',
            			contactNo : '',
            			password : '',
            			userRole : '',
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
            	
            	self.fetchAllUsers();
            	
            	self.createUser = function(user){
            		console.log("Create a new User");
            		UserService.createUser(user)
            			.then(function(d){
            				self.user = d;
            			},
            					//self.fetchAllUsers,
            					function(errResponse){
            				console.error('Error While Creating New User.');
            			});
            	};

            	self.authenticate = function(user){
            		console.log("In Authenticate...");
            		UserService.authenticate(user)
            			.then(function(d){
            				self.user = d;
            				/*console.log("Get Data from Service"+self.user);
            				if($rootScope.currentUser){
            					console.log("Valid Credentials. Navigating to home Page.")
            					$location.path('/register');
            				}else{
            					console.log("Invalid Credentials. Staying On the Same Page.")
            				}*/
            				
            				console.log("--->user.errorCode :- " + self.user.errorCode)
            				if(self.user.errorCode == "404"){
            					self.user.emailId = "";
            					self.user.password = "";
            				}else{
            					console.log("Valid Credentials. Navigating to Home Page.")
       			   				$rootScope.currentUser = {
   			   						firstName : self.user.firstName,
   			   						lastName : self.user.lastName,
   			   						userId : self.user.userId,
   			   						userRole : self.user.userRole
   			   				};
            					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
            					$cookieStore.put('currentUser',$rootScope.currentUser);
            					$location.path('/');
            				}
            			},
            			function(errResponse){
            				console.error('Error While Authenticate Users.');
            			});
            	};

                  
            	self.login = function(){
            		{
            			console.log('In Login Authenticate User.',self.user);
            			self.authenticate(self.user);
            		}
            		self.reset();
            	}
            	
            	self.submit = function(){
            		{
                		console.log('Saving a New User',self.user);
                		self.createUser(self.user);            			
            		}
            		self.reset();
            	}
            	
            	
            	self.reset = function(){
            		self.user  = {
            				userId : '',
            				firstName : '',
                			lastName : '',
                			emailId : '',
                			contactNo : '',
                			password : '',
                			cpassword : '',
                			userRole : '',
                			approveStatus : '',
                			accountStatus : '',
                			errorCode : '',
                			errorMessage : ''
            		};
            		$scope.form.$setPristine();
            	}
}]);