package com.niit.collabrationbackend.Controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.collabrationbackend.Model.Message;
import com.niit.collabrationbackend.Model.OutputMessage;

@Controller
public class ChatController {
	
	private static final Logger log = LoggerFactory.getLogger(ChatController.class);

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message){
		log.debug("Calling the method sendMessage");
		log.debug("Message :-",message.getMessage());
		log.debug("Message Id:-",message.getId());
		return new OutputMessage(message,new Date());
	}
}
