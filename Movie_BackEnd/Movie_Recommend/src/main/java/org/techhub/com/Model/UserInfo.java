package org.techhub.com.Model;

import lombok.Data;

@Data
public class UserInfo {
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String createdAt;
	
}
