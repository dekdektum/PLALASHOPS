package plalashop.service.impl;

import java.util.List;

import plalashop.domain.Product;
import plalashop.utils.Utils;


public class ValidateService {
	
	
	public static String validateAddProuct(String productType,String productName,String price,String salePrice,String description,String sex,String productNo){
		
		String errorMsg = validateAddProuct(productType, productName, price, salePrice, description, sex);
		if(!"".equals(errorMsg)){
			return errorMsg;
		}
		Product product = new Product();
		product.setProductNo(productNo);
		List<Product> products;
		try {
			products = PlalaShopsService.getProductsByProductsObj(product);
			if(products != null && products.size() > 0){
				return "Prodution No is Duplicate!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	public static String validateAddProuct(String productType,String productName,String price,String salePrice,String description,String sex){
		
		if(productType == null || productType.length() == 0){
			return "Product Type is empty!";
		}
		if(productName == null || productName.length() == 0){
			return "Product Name is empty!";
		}
		
		
		
		
		if(salePrice == null || salePrice.length() == 0){
			return "Sale Price is empty!";
		}else{
			if(!Utils.isDouble(salePrice)){
				return "Sale Price Invalit";
			}
		}
		if(description == null || description.length() == 0){
			return "Description is empty!";
		}
		
		
		return "";
	}
	
	public static boolean isDouble(String value){
		if(value != null){
			try {
				Double.parseDouble(value);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	
}
