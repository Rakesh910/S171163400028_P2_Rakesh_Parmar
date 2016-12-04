'use strict';

app.controller('HomeController',['$scope','UserService','$location','$rootScope','$cookieStore','$http',function($scope,UserService,$location,$rootScope,$cookieStore,$http){
	console.log("Home Controller")
	var self = this;
	
	self.getCurrentUser = function(){
		console.log("Loading current user if already logged in")
		console.log("Current User :- "+ $rootScope.currentUser)
		if(!$rootScope.currentUser){
			console.log("User not logged In")
			$rootScope.currentUser = "";
		}else{
			console.log("---------->USER IS LOGGED IN WITH ID:-"+$rootScope.currentUser.userId)
		}
		return $rootScope.currentUser;
	}
	self.getCurrentUser();
	
	
	self.logout = function(){
		$rootScope.currentUser = {};
		$cookieStore.put('currentUser');
		$cookieStore.remove('currentUser');
		console.log("Clear Cookies :- "+ $cookieStore.get('currentUser'));
		UserService.logout()
			.then(function(d){
				console.error('Logout And Navigate IndexPage.');
				$location.path('/');
				console.error('Not Logout And Navigate IndexPage.');
			},
			function(errResponse){
            	console.error('Error While Logout Users.');
            });
	}
}]);