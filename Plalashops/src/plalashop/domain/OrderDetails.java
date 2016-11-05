package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetails {

	private  Long Order_Detail_ID;
	private  Long Order_ID;
	private  Long Product_ID;
	private  Long Quantity;
	private  Long Price;
	private String status;
	
	
	public OrderDetails(ResultSet resultSet){
		if(resultSet != null){
			try {
				Order_Detail_ID = resultSet.getLong("Order_Detail_ID");
				Order_ID = resultSet.getLong("Order_ID");
				Product_ID = resultSet.getLong("Product_ID");
				Quantity = resultSet.getLong("Quantity");
				Price = resultSet.getLong("Price");

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public OrderDetails() {

	}

	
	public Long getOrder_Detail_ID() {
		return Order_Detail_ID;
	}
	public void setOrder_Detail_ID(Long order_Detail_ID) {
		Order_Detail_ID = order_Detail_ID;
	}
	public Long getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(Long order_ID) {
		Order_ID = order_ID;
	}
	public Long getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(Long product_ID) {
		Product_ID = product_ID;
	}
	public Long getQuantity() {
		return Quantity;
	}
	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}
	public Long getPrice() {
		return Price;
	}
	public void setPrice(Long price) {
		Price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
