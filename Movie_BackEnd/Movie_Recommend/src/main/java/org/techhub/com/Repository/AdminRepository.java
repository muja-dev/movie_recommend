package org.techhub.com.Repository;

import org.techhub.com.Model.AdminInfo;

public interface AdminRepository {
	
	public AdminInfo loginAdmin(String email,String adminpass);
}
