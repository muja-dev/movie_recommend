package org.techhub.com.Repository;

import org.techhub.com.Model.UserInfo;

public interface UserRespository {
	public boolean addUser(UserInfo user);
	
	public boolean loginUser(String name,String password);
	
	public boolean updateUserProfile(UserInfo user);
}
