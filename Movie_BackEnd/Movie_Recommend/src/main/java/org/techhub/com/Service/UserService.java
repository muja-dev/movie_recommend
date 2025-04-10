package org.techhub.com.Service;

import org.techhub.com.Model.UserInfo;

public interface UserService {
	
	public boolean addUser(UserInfo user);
	public boolean loginUser(String name,String password);
	public boolean updateUserProfile(UserInfo user);


}
