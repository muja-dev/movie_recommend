package org.techhub.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Model.UserInfo;
import org.techhub.com.Service.UserService;
import org.techhub.com.Service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public String addUser(@RequestBody UserInfo user) {
		boolean b=userService.addUser(user);
		if(b)
		{
			return "User Added";
		}
		else {
		return "Problem";
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserInfo user) {
	    boolean result = userService.loginUser(user.getEmail(), user.getPassword());
	    if (result) {
	        return ResponseEntity.ok("Login successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}
	
	@PutMapping("/updateProfile")
	public ResponseEntity<String> updateProfile(@RequestBody UserInfo user)
	{
		boolean b=userService.updateUserProfile(user);
		if(b)
		{
			return ResponseEntity.ok("User updated");
		}
		else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}
	
	@GetMapping("/showAllUsers")
	public List<UserInfo> showAllUsers(){
		List<UserInfo> list=userService.showAllUser();
		if(list.size()>0)
		{
			return list;
		}
		else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
