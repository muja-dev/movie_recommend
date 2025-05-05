package org.techhub.com.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Model.AdminInfo;
import org.techhub.com.Model.UserInfo;
import org.techhub.com.Service.AdminServieImpl;

@CrossOrigin(origins = "*")

@RestController
public class AdminController {
	
	@Autowired
	AdminServieImpl adminService;
	
	@PostMapping("/adminlogin")
	public ResponseEntity<?> login(@RequestBody AdminInfo request) {
	    AdminInfo admin = adminService.loginAdmin(request.getEmail(), request.getAdminpass());
	    if (admin != null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("token", "dummy-token-or-generate-jwt");
	        response.put("userId", admin.getAdmin_id());
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

}
