'use strict';

app.controller('EventController',[
	'$scope',
	'EventService',
	'$location',
    '$rootScope',
    '$cookieStore',
    '$http',
	function($scope,EventService,$location,$rootScope,$cookieStore,$http){
    	console.log("Event Controller")
    	
    	var self = this;               
    	
    	self.event = {
    			eventTitle : '',
    			eventDescription : '',
    			eventDate : '',
    			eventVenue : ''
    	};
    	
    	self.events = [];  
    	
    	self.fetchAllEvents = function(){
    		EventService.fetchAllEvents()
    			.then(function(d){
    						self.events = d;
    				},function(errResponse){
    					console.error('Error While Fatching Events.');
    			});
    	};
    	    	    	
    	self.createEvent = function(event){
    		EventService.createEvent(event)
    			.then(
    					function(d){
    				self.event = d;
    			},
    					function(errResponse){
    					console.error('Error While Creating Event.');
    			});
    	};
    	    	
    	//calling the Method
    	self.fetchAllEvents();
    	
    	self.submit = function(){
    		{
    			console.log('Post a New Event',self.event);
    			self.createEvent(self.event);           			
    		}
    		self.reset();
    	}
    	
    	self.reset = function(){
    		self.event  = {
    				eventId : '',
        			eventTitle : '',
        			eventDescription : '',
        			eventDate : '',
        			eventVenue : '',
        			eventStatus : '',
        			errorCode : '',
        			errorMessage : ''
    		};
    		$scope.form.$setPristine();
    	}
}]);