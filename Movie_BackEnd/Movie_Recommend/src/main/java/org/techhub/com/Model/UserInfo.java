package org.techhub.com.Model;

import lombok.Data;

@Data
public class UserInfo {
	private int userId;
	private String username;
	private String email;
	private String password;
	private String createdAt;
	
}
