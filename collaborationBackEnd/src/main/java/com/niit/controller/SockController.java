package com.niit.controller;


import java.security.Principal;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;


import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.server.ServerHttpRequest;

import org.springframework.http.server.ServletServerHttpRequest;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.messaging.simp.annotation.SendToUser;

//import org.springframework.messaging.simp.annotation.SubscribeEvent;

import org.springframework.messaging.simp.annotation.SubscribeMapping;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.socket.WebSocketHandler;

import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.springframework.web.socket.server.support.DefaultHandshakeHandler;


import com.niit.dao.UserDao;
import com.niit.model.Chat;

import com.niit.model.User;



@Controller
public 
class SockController {
	
	
private static final Logger log = LoggerFactory.getLogger(SockController.class);

	
private final SimpMessagingTemplate messagingTemplate;
	
private List<String> users = new ArrayList<String>();
	
@Autowired
	
private UserDao userDao;

	
@Autowired
	
public SockController( SimpMessagingTemplate messagingTemplate) {
		
this.messagingTemplate = messagingTemplate;
	
}
		
	
@SubscribeMapping("/join")
	
public List<String> join() {
		
log.debug("inside join method in sock controller");
	
String username=null;
		
//System.out.println(username);
	
List<String> onlineUsers=	userDao.getOnlineUsers();
	
log.debug("users " + users);

log.debug("online users " + onlineUsers );
		
for(String s : onlineUsers)
		{
			
if(!users.contains(s)){
				
username=s;
				
users.add(s);
			
}
		
}
		
for(String u:users){
			
if(!onlineUsers.contains(u)){
			
	users.remove(u);
			
}
		
}
		
log.debug("====JOIN==== " + username );
		
// notify all subscribers of new user
		
messagingTemplate.convertAndSend("/topic/join",username);
		
		
return users;
	
}
	
	
	
	
public void remove(String username){
		
users.remove(username);
	
}

	
@MessageMapping(value="/chat")
	
public void chatReveived(Chat chat) {
		
		
System.out.println("From value in chatreceived in chat object " + chat.getFrom());
				
System.out.println(chat.getFrom());
		
if("all".equals(chat.getTo())) {
			
System.out.println("IN CHAT REVEIVED " + chat.getMessage() + " " + chat.getFrom() + " to " + chat.getTo());
			
messagingTemplate.convertAndSend("/queue/chats", chat);
		
}
		
else {
			
System.out.println("CHAT TO " + chat.getTo() + " From " + chat.getFrom() + " Message " + chat.getMessage());
		
			
messagingTemplate.convertAndSend( "/queue/chats/"+chat.getTo(),chat);
			
messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(),chat);
		
}
		
	
}
	
	
	


}
