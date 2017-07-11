package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfileUploadDao;
import com.niit.model.ProfilePicture;
import com.niit.model.User;
import com.niit.model.Error;
@RestController
public class FileUploadController {
	@Autowired
	private ProfileUploadDao profileUploadDao;
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
public ResponseEntity<?> uploadProfilePic(
		HttpSession session,@RequestParam CommonsMultipartFile fileUpload){
		User user=(User)session.getAttribute("user");
		if(user==null){
			Error error=new Error(3,"Unauthorized user.. Please login");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setUsername(user.getUsername());
			profilePicture.setImage(fileUpload.getBytes());
			profileUploadDao.save(profilePicture);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	
}
	@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null)
			return null;
		else
		{
			ProfilePicture profilePic=profileUploadDao.getProfilePic(username);
			if(profilePic==null)
				return null;
			else
				return profilePic.getImage();
		}
		
		
		
	}
}