package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductType {
	private Long productTypeId;
	private String productTypeName;
	
	
	
	public ProductType(ResultSet resultSet){
		if(resultSet != null){
			try {
				productTypeId = resultSet.getLong("product_type_id");
				productTypeName = resultSet.getString("product_type_name");
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
	
	
}
