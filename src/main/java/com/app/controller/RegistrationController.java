package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.RegistrationService;

@RestController
public class RegistrationController {
	
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins="http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception
	{
		String tempEmailId = user.getEmailId();
		if(tempEmailId != null && !"".equals(tempEmailId))
        {
	     User userobj=service.fetchUserByEmailId(tempEmailId);
	     if(userobj != null) {
	    	 throw new Exception("user with" +tempEmailId + "id already exist");
	     }
        }
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;	
	}
	
	
	
	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:4200")
	public User loginUser(@RequestBody User user)throws Exception
	{
		String tempEmailId = user.getEmailId();
		String tempPass = user.getPassword();
		User userobj = null;
		if(tempEmailId != null && tempPass != null)
		{
			userobj =service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		if(userobj ==null) 
		{
			throw new Exception("Bad credentials");
		}
		
		return userobj;
	}
	
	

}
