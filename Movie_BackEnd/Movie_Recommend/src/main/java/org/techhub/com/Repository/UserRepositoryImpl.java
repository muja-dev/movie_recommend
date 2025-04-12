package org.techhub.com.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.techhub.com.Model.UserInfo;

@Repository("userRespo")
public class UserRepositoryImpl implements UserRespository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public boolean addUser(UserInfo user) {

		int value=jdbcTemplate.update("insert into users (username,email,password_hash) values(?,?,?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());
			}
			
		});
		return value>0?true:false;
	}
	@Override
	public boolean loginUser(String name, String password) {
		List<UserInfo> list=jdbcTemplate.query("select *from users where username=? and password_hash=?", new Object[] {name,password},new RowMapper<UserInfo>() {

			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo user =new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setCreatedAt(rs.getString(5));
				return user;
			}
			
		});
		return list.size()>0?true:false;
	}
	@Override
	public boolean updateUserProfile(UserInfo user) {
		int value=jdbcTemplate.update("update users set username=?,email=? where user_id=?",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getEmail());
				
				ps.setInt(3, user.getUserId());
				
			}
			
		});
		return value>0?true:false;
		
	}
	@Override
	public List<UserInfo> showAllUser() {
		List<UserInfo> list=jdbcTemplate.query("select user_id,username,email,created_at from users", new RowMapper<UserInfo>() {

			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo user=new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setCreatedAt(rs.getString(4));
				return user;
			}
			
		});
		return list.size()>0?list:null;
	}
	
	

}

