package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	
	private Long customerId;
	private String fullName;
	private String address;
	private String zipcode;
	private Long userId;
	
	
	
	public Customer(ResultSet resultSet){
		if(resultSet != null){
			try {
				customerId = resultSet.getLong("customerId");
				fullName = resultSet.getString("fullName");
				address = resultSet.getString("address");
				zipcode = resultSet.getString("zipcode");
				userId = resultSet.getLong("userId");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
