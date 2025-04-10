package org.techhub.com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techhub.com.Model.UserInfo;
import org.techhub.com.Repository.UserRepositoryImpl;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepositoryImpl userRepo;
	@Override
	public boolean addUser(UserInfo user) {
		
		return userRepo.addUser(user);
	}
	@Override
	public boolean loginUser(String name, String password) {
		
		return userRepo.loginUser(name, password);
	}
	@Override
	public boolean updateUserProfile(UserInfo user) {
		
		return userRepo.updateUserProfile(user);
	}

}
