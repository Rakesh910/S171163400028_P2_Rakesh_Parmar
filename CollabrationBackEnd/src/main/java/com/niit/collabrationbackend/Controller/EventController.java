package com.niit.collabrationbackend.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabrationbackend.Dao.EventDao;
import com.niit.collabrationbackend.Model.Event;

@RestController
public class EventController {
	
	private static final Logger log = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	EventDao eventDao;
	
	//http://localhost:8080/CollabrationBackEnd/EventPages/EventList/
	@RequestMapping(value = "/EventPages/EventList/", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> listAllEvents(){
		log.debug("**********Starting of Method listAllEvents**********");
		List<Event> eventList = eventDao.getAllEvents();
		if(eventList.isEmpty()){
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}else{
			log.debug("**********Size found :- "+eventList.size()+"**********");
			log.debug("**********Ending of Method listAllEvents**********");
			return new ResponseEntity<List<Event>>(HttpStatus.OK);
		}
	}
		
	//http://localhost:8080/CollabrationBackEnd/EventPages/CreateEvent/
	@RequestMapping(value = "/EventPages/CreateEvent/", method = RequestMethod.POST)
	public ResponseEntity<Event> createEvent(@RequestBody Event event){
		log.debug("**********Starting of Method createUser**********");
		if(eventDao.getEventById(event.getEventId()) == null){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String eventDate = (dateFormat.format(date));
            event.setEventDate(eventDate);
            event.setEventStatus('1');
			eventDao.saveEvent(event);
			event = new Event();
			event.setErrorMessage("New Event Posted Successfully..!!!");
			log.debug("**********New Event Created Successfully**********");
			return new ResponseEntity<Event>(event , HttpStatus.OK);
		}
		log.debug("**********Event already Exist with ID :-"+event.getEventId()+" **********");
		event.setErrorMessage("Event Already Exist With ID:-"+event.getEventId());
		return new ResponseEntity<Event>(event , HttpStatus.OK);
	}
	
	//http://localhost:8080/CollabrationBackEnd/EventPages/UpdateEvent/{id}
	@RequestMapping(value = "/EventPages/UpdateEvent/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Event> updateEvent(@PathVariable("id") String eventId,@RequestBody Event event){
		log.debug("**********Starting of Method updateEvent**********" + eventId);
		if(eventDao.getEventById(eventId) == null){
			log.debug("**********Event Does not Exist with this ID :-"+eventId+"**********");
			event = new Event();
			event.setErrorCode("404");
			event.setErrorMessage("Event Does not Exist with this ID :-"+eventId);
			return new ResponseEntity<Event>(event , HttpStatus.NOT_FOUND);
		}else{
			event.setEventId(eventId);
			eventDao.updateEvent(event);
			log.debug("**********Event Updated Successfully WITH ID:- "+eventId+"**********");
			return new ResponseEntity<Event>(event , HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/CollabrationBackEnd/UserPages/RemoveEvent/{id}
	@RequestMapping(value = "/UserPages/RemoveEvent/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Event> removeEvent(@PathVariable("id") String eventId){
		log.debug("**********Starting of Method removeUser**********");
		Event event = eventDao.getEventById(eventId);
		if(event == null){
			log.debug("**********Event Does not Exist with this ID :-"+eventId+"**********");
			event = new Event();
			event.setErrorCode("404");
			event.setErrorMessage("Event Does not Exist with this ID :-"+ eventId);
			return new ResponseEntity<Event>(event , HttpStatus.NOT_FOUND);
		}else{
			eventDao.removeEvent(eventId);
			log.debug("**********Event Deleted Successfully WITH ID:- "+eventId+"**********");
			return new ResponseEntity<Event>(event , HttpStatus.OK);
		}
	}
}
