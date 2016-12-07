package com.niit.collabrationbackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Event extends BaseDomain{
	
	@Id
	private String eventId;
	
	@NotBlank(message="Please Enter Event Title")
	private String eventTitle;
	
	@NotBlank(message="Please Enter Event Description")
	private String eventDescription;
	
	private String eventDate;
		
	@NotBlank(message="Please Enter Event Description")
	private String eventVenue;
	
	private char eventStatus;
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}

	public char getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(char eventStatus) {
		this.eventStatus = eventStatus;
	}

	public Event() {
		this.eventId = "EVT" + UUID.randomUUID().toString().substring(30).toUpperCase();
	}
}
