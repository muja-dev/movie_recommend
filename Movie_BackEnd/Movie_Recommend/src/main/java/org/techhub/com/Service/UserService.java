package org.techhub.com.Service;

import java.util.List;

import org.techhub.com.Model.UserInfo;

public interface UserService {
	
	public boolean addUser(UserInfo user);
	public UserInfo loginUser(String name,String password);
	public boolean updateUserProfile(UserInfo user);

	public List<UserInfo> showAllUser();

}
