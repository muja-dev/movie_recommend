package org.techhub.com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techhub.com.Model.AdminInfo;
import org.techhub.com.Repository.AdminRepositoryImpl;

@Repository("adminService")
public class AdminServieImpl implements AdminService {
	
	@Autowired
	private AdminRepositoryImpl adminRepo;

	@Override
	public AdminInfo loginAdmin(String email, String adminpass) {
		// TODO Auto-generated method stub
		return adminRepo.loginAdmin(email, adminpass);
	}

}
