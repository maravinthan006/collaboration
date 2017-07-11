package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.User;
import com.niit.model.Error;
@RestController
public class UserController {
@Autowired
private UserDao userDao;	

@RequestMapping(value="/register",method=RequestMethod.POST)
public ResponseEntity<?> registerUser(@RequestBody User user){
	
	//client will send only username, password, email, role 
	try{
	
	user.setEnabled(true);
	user.setOnline(false);
	User savedUser=userDao.registerUser(user);
	
	return new ResponseEntity<User>(savedUser,HttpStatus.OK);
	}catch(Exception e){
		e.printStackTrace();
		Error error=new Error(2,"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
		return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	
	User validUser=userDao.login(user);
	if(validUser==null){
		Error error=new Error(3,"Invalid credentials.. please enter valid username and password");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	else{
		session.setAttribute("user", validUser);
		validUser.setOnline(true);
		userDao.updateUser(validUser);
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
	}
}
@RequestMapping(value="/logout",method=RequestMethod.PUT)
public ResponseEntity<?> logout(HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null){//Unauthorized user
		Error error =new Error(3,"Unauthorized user.. Please Login..");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	else{
		user.setOnline(false);
		userDao.updateUser(user);
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

@RequestMapping(value="/getuser",method=RequestMethod.GET)
public ResponseEntity<?> getUser(HttpSession session){
	//ONLY FOR AUTHENTICATION
          User user=(User)session.getAttribute("user");
          if(user==null){
        	 Error error=new Error(3,"Unauthorized user..");
        	 return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
          }
          else
          {
        	  user=userDao.getUser(user.getId());
        	  return new ResponseEntity<User>(user,HttpStatus.OK);
          }
}
@RequestMapping(value="/updateuser",method=RequestMethod.PUT)
public ResponseEntity<?> updateUser(@RequestBody User updatedUserDetails,HttpSession session){
	User user=(User)session.getAttribute("user");
	if(user==null){
		Error error=new Error(3,"Unauthorized user..");
   	 return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	else{
		//firstname = John, lastname=Smith
		userDao.updateUser(updatedUserDetails);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
}