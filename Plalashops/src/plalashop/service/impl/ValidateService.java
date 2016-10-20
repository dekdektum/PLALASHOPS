package plalashop.service.impl;

import plalashop.utils.Utils;


public class ValidateService {
	
	
	public static String validateAddProuct(String productType,String productName,String price,String salePrice,String description,String sex){
		
		if(productType == null || productType.length() == 0){
			return "Product Type is empty!";
		}
		if(productName == null || productName.length() == 0){
			return "Product Name is empty!";
		}
		
		
		if(!Utils.isDouble(price)){
			return "Price Invalit";
		}
		
		if(salePrice == null || salePrice.length() == 0){
			return "Sale Price is empty!";
		}else{
			if(!Utils.isDouble(salePrice)){
				return "Sale Price Invalit";
			}
			if(Double.parseDouble(price) < Double.parseDouble(salePrice)){
				return "Sale Price < Price ";
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
