package plalashop.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import plalashop.controller.PlalashopController;
import plalashop.utils.Utils;

public class ImgMapping {
	private Long imgId;
	private Long productId;
	private String fileName;
	
	public ImgMapping() {
		
	}
	public ImgMapping(ResultSet resultSet) {
		if(resultSet != null){
			try {
				imgId = resultSet.getLong("imageId");
				productId = resultSet.getLong("product_id");
				fileName = resultSet.getString("file_name");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getFileName() throws IOException {
		return Utils.convertImageToBase64(PlalashopController.UPLOAD_DIRECTORY+fileName);
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
