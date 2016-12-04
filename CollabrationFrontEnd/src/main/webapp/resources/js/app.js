'use strict';

var app = angular.module('myApp', [ 'ngRoute', 'ngCookies', 'ngFileUpload' ]);

app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'UserPages/home.html',
		controller : 'HomeController'
	})

	.when('/home', {
		templateUrl : 'UserPages/home.html',
		controller : 'UserController'
	})
	
/*	.when('/home', {
		templateUrl : 'UserPages/myPage.html',
		controller : 'UserController'
	})*/

	.when('/register', {
		templateUrl : 'UserPages/Registration.html',
		controller : 'UserController'
	})
	
	.when('/manageUser', {
		templateUrl : 'Admin/manageUser.html',
		controller : 'AdminController'
	})

	/*Jobs*/
	.when('/create_job', {
		templateUrl : 'Job_Opportunities/PostNewJob.html',
		controller : 'JobController'
	})

	.when('/listOfJobs', {
		templateUrl : 'Job_Opportunities/ListOfJobs.html',
		controller : 'JobController'
	})
	
	.when('/listOfMyJobs', {
		templateUrl : 'Job_Opportunities/MyAppliedJobs.html',
		controller : 'JobController'
	})
	
	.when('/remove_job', {
		templateUrl : 'Job_Opportunities/manageJobs.html',
		controller : 'JobController'
	})
	
	.when('/create_Blog', {
		templateUrl : 'Blog/create_blog.html',
		controller : 'BlogController'
	})

	.when('/manageBlog', {
		templateUrl : 'Admin/manageBlogs.html',
		controller : 'AdminController'
	})
	
	.when('/blog_list', {
		templateUrl : 'Blog/blog_list.html',
		controller : 'BlogController'
	})
	
	.when('/view_blog', {
		templateUrl : 'Blog/view_blog.html',
		controller : 'BlogController'
	})

	.when('/myBlogList', {
		templateUrl : 'Blog/MyBlogList.html',
		controller : 'BlogController'
	})
	
	.when('/myPendingBlogList', {
		templateUrl : 'Blog/pendingBlog.html',
		controller : 'BlogController'
	})
	
	.when('/myRejectedBlogList', {
		templateUrl : 'Blog/MyRejectedBlog.html',
		controller : 'BlogController'
	})

	/*Friend*/
	.when('/showUsersList', {
		templateUrl : 'Friend/UserList.html',
		controller : 'FriendController'
	})
	
	.when('/myFriendList', {
		templateUrl : 'Friend/myfriends.html',
		controller : 'FriendController'
	})
	
	.when('/newFriendRequest', {
		templateUrl : 'Friend/newFriendsRequest.html',
		controller : 'FriendController'
	})	
	
	//Events
	.when('/create_event', {
		templateUrl : 'Event/PostNewEvent.html',
		controller : 'EventController'
	})
	
	.when('/listOfEvents', {
		templateUrl : 'Event/ListOfEvents.html',
		controller : 'EventController'
	})

	.when('/chat_forum', {
		templateUrl : 'Chat_Forum/chat_forum.html',
		controller : 'ChatController'
	})
	
	.when('/chat', {
		templateUrl : 'Chat/chat.html',
		controller : 'ChatController'
	})

	.when('/about_us', {
		templateUrl : 'About_us/about_us.html',
		controller : 'AboutController'
	})

	.when('/login', {
		templateUrl : 'UserPages/home.html',
		controller : 'UserController'
	})

	.otherwise({
		redirectTo : '/'
	});
});

app.run(function($rootScope, $location, $cookieStore, $http) {
	$rootScope.$on('$locationChangeStart',
			function(event, next, current) {
				console.log("$locationChangeStart")
				// redirect to login page if not logged in and trying to access
				// a restricted page.
				var restrictedPage = $.inArray($location.path(),
						[ '/create_Blog']) === 0;

				console.log("RestrictedPage :-" + restrictedPage)
				var loggedIn = $rootScope.currentUser.userId;
				console.log("LoggedIn :-" + loggedIn)
				if (restrictedPage && !loggedIn) {
					console.log("Navigating to login Page :")
					$location.path('/login');
				}
			});

	// keep user logged in after page refresh
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic'
				+ $rootScope.currentUser;
	}
	
	$rootScope.selectedBlog = $cookieStore.get('selectedBlog') || {};
	if ($rootScope.selectedBlog) {
		$http.defaults.headers.common['Authorization'] = 'Basic'
				+ $rootScope.selectedBlog;
	}
});