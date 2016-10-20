package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import plalashop.controller.PlalashopController;
import plalashop.utils.Utils;

public class ProductType {
	private Long productTypeId;
	private String productTypeName;
	private String fileName;
	private String formName;
	
	
	public ProductType(ResultSet resultSet){
		if(resultSet != null){
			try {
				productTypeId = resultSet.getLong("product_type_id");
				productTypeName = resultSet.getString("product_type_name");
				fileName = resultSet.getString("file_name");
				formName = resultSet.getString("form_name");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ProductType(){
		
	}
	
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
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
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	
}
