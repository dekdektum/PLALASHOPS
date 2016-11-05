package plalashop.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders {
	   private Long  OrderID;

	   private Long CustomerID;
	   private String PaymentID;
	   private String OrderDate;
	   private String LastUpdateDate;
	   private String UserLastUpdate;
	   private String EMS;
	
	   

	public Orders(ResultSet resultSet){
		if(resultSet != null){
			try {
				OrderID = resultSet.getLong("OrderID");
				CustomerID = resultSet.getLong("CustomerID");
				PaymentID = resultSet.getString("PaymentID");
				OrderDate = resultSet.getString("OrderDate");
				LastUpdateDate = resultSet.getString("LastUpdateDate");
				UserLastUpdate = resultSet.getString("UserLastUpdate");
				EMS = resultSet.getString("EMS");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Orders() {

	}

public Long getOrderID() {
	return OrderID;
}
public void setOrderID(Long orderID) {
	OrderID = orderID;
}

public Long getCustomerID() {
	return CustomerID;
}
public void setCustomerID(Long customerID) {
	CustomerID = customerID;
}
public String getPaymentID() {
	return PaymentID;
}
public void setPaymentID(String paymentID) {
	PaymentID = paymentID;
}
public String getOrderDate() {
	return OrderDate;
}
public void setOrderDate(String orderDate) {
	OrderDate = orderDate;
}
public String getLastUpdateDate() {
	return LastUpdateDate;
}
public void setLastUpdateDate(String lastUpdateDate) {
	LastUpdateDate = lastUpdateDate;
}
public String getUserLastUpdate() {
	return UserLastUpdate;
}
public void setUserLastUpdate(String userLastUpdate) {
	UserLastUpdate = userLastUpdate;
}
public String getEMS() {
	return EMS;
}
public void setEMS(String eMS) {
	EMS = eMS;
}
   
   
}
