package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import plalashop.controller.PlalashopController;
import plalashop.utils.Utils;

public class GroupProduct {
	private Long groupProductId;
	private String groupName;
	private String fileName;
	private String free1;
	private String free2;
	private String free3;
	
	
	public GroupProduct(ResultSet resultSet) {
		if(resultSet != null){
			try {
				groupProductId = resultSet.getLong("group_product_id");
				groupName = resultSet.getString("group_name");
				fileName = resultSet.getString("file_name");
				free1 = resultSet.getString("free1");
				free2 = resultSet.getString("free2");
				free3 = resultSet.getString("free3");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public GroupProduct() {
		// TODO Auto-generated constructor stub
	}

	public Long getGroupProductId() {
		return groupProductId;
	}


	public void setGroupProductId(Long groupProductId) {
		this.groupProductId = groupProductId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getFileName() {
		return Utils.convertImageToBase64(PlalashopController.UPLOAD_DIRECTORY+fileName);
	}
	public String getFileName(int i) {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFree1() {
		return free1;
	}


	public void setFree1(String free1) {
		this.free1 = free1;
	}


	public String getFree2() {
		return free2;
	}


	public void setFree2(String free2) {
		this.free2 = free2;
	}


	public String getFree3() {
		return free3;
	}


	public void setFree3(String free3) {
		this.free3 = free3;
	}
	
	
	
}
