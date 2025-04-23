package org.techhub.com.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.techhub.com.Model.AdminInfo;

@Repository("adminRepo")
public class AdminRepositoryImpl implements AdminRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public AdminInfo loginAdmin(String email, String adminpass) {
		 List<AdminInfo> list = jdbcTemplate.query(
			        "SELECT * FROM admin WHERE email=? AND adminpass=?",
			        new Object[]{email, adminpass},
			        new RowMapper<AdminInfo>() {
			            @Override
			            public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			                AdminInfo admin = new AdminInfo();
			                admin.setAdmin_id(rs.getInt("admin_id")); // or rs.getInt(1)
			                admin.setAdmin_name(rs.getString("admin_name"));
			                admin.setEmail(rs.getString("email"));
			                admin.setAdminpass(rs.getString("adminpass"));
			                admin.setCreated(rs.getString("created"));
			                return admin;
			            }
			        }
			    );
			    return list.isEmpty() ? null : list.get(0);
	}

}
