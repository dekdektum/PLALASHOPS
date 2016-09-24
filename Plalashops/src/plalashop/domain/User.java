package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private Long userId;
	private String userLogin;
	private String password;
	private String email;
	private String role;
	private String userType;
	
	
	public User(ResultSet resultSet){
		if(resultSet != null){
			try {
				userId = resultSet.getLong("user_id");
				userLogin = resultSet.getString("user_login");
				password = resultSet.getString("password");
				email = resultSet.getString("email");
				role = resultSet.getString("role");
				userType = resultSet.getString("user_type");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public User(){
		
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
