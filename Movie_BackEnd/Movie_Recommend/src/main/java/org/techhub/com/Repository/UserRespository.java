package org.techhub.com.Repository;

import org.techhub.com.Model.UserInfo;
import java.util.*;

public interface UserRespository {
	public boolean addUser(UserInfo user);
	
	public boolean loginUser(String name,String password);
	
	public boolean updateUserProfile(UserInfo user);
	
	public List<UserInfo> showAllUser();
}
