package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import plalashop.controller.PlalashopController;
import plalashop.service.impl.PlalaShopsService;
import plalashop.utils.Utils;

public class Product {
	private Long productId;
	private String productNo;
	private String productName;
	private String productType;
	private Double price;
	private Double salePrice;
	private String description;
	private String sex;
	private String fileImage;
	private String specialClass;
	
	
	public Product(ResultSet resultSet){
		if(resultSet != null){
			try {
				productId = resultSet.getLong("product_id");
				productNo = resultSet.getString("product_no");
				productName = resultSet.getString("product_name");
				productType = resultSet.getString("product_type");
				price = resultSet.getDouble("price");
				salePrice = resultSet.getDouble("sale_price");
				description = resultSet.getString("description");
				sex = resultSet.getString("sex");
				fileImage = resultSet.getString("file_name");
				specialClass = resultSet.getString("special_class");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Product() {

	}
	
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFileImage() throws Exception {
		return Utils.convertImageToBase64(PlalashopController.UPLOAD_DIRECTORY+fileImage);
	}

	public void setFileImage(String fileImage) {
		this.fileImage = fileImage;
	}
	
	public int getDisCount(){
		return (int) ((int)100-(salePrice * 100 / price));
	}

	public String getSpecialClass() {
		return specialClass;
	}

	public void setSpecialClass(String specialClass) {
		this.specialClass = specialClass;
	}
	
	
	
	
}
