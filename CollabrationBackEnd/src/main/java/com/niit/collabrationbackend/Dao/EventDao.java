package com.niit.collabrationbackend.Dao;

import java.util.List;

import com.niit.collabrationbackend.Model.Event;

public interface EventDao {
	
	public boolean saveEvent(Event event);
	
	public boolean updateEvent(Event event);
	
	public boolean removeEvent(String eventId);
	
	public List<Event> getAllEvents();
	
	public Event getEventById(String eventId);

}
