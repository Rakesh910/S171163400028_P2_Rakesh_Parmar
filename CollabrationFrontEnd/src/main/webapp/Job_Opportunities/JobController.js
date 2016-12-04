'use strict';

app.controller('JobController',[
	'$scope',
	'JobService',
	'$location',
    '$rootScope',
    '$cookieStore',
    '$http',
	function($scope,JobService,$location,$rootScope,$cookieStore,$http){
    	console.log("Job Controller")
    	
    	var self = this;               
    	
    	self.job = {
    			jobTitle : '',
    			jobDescription : '',
    			jobLocation : '',
    			jobRole : '',
    			contact : '',
    			email : ''
    	};
    	
    	self.jobs = [];  
    	self.myjobs = [];  
    	
    	self.fetchAllJobs = function(){
    		JobService.fetchAllJobs()
    			.then(function(d){
    						self.jobs = d;
    				},function(errResponse){
    					console.error('Error While Fatching Jobs.');
    			});
    	};
    	
    	self.fetchMyJobs = function(){
    		JobService.fetchMyJobs()
    			.then(function(d){
    						self.myjobs = d;
    						console.log('Get list of myjobs'+self.myjobs.length)
    				},function(errResponse){
    					console.error('Error While Fatching MyJobs.');
    			});
    	};
    	    	    	
    	self.createJob = function(job){
    		JobService.createJob(job)
    			.then(
    					function(d){
    				self.job = d;
    			},
    					function(errResponse){
    					console.error('Error While Creating Job.');
    			});
    	};

    	self.ApplyForJob = applyForJob
    	
    	function applyForJob(jobId){
    		console.log("->->applyForJob with JobID :-"+jobId);
    		JobService.applyForJob(jobId)
    			.then(
    					self.fetchAllJobs,
    					function(errResponse){
    						console.error('Error While applyForJob .');
    					});
    	};
    	//calling the Method
    	self.fetchAllJobs();
    	self.fetchMyJobs();
    	
    	self.submit = function(){
    		{
    			console.log('Post a New Job',self.job);
    			self.createJob(self.job);           			
    		}
    		self.reset();
    	}
    	
    	self.reset = function(){
    		self.job  = {
    				jobId : '',
        			jobTitle : '',
        			jobDescription : '',
        			jobLocation : '',
        			contact : '',
        			email : '',
        			jobStatus : '',
        			errorCode : '',
        			errorMessage : ''
    		};
    		$scope.form.$setPristine();
    	}
}]);