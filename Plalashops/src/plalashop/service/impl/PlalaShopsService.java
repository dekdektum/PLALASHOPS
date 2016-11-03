package plalashop.service.impl;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import javax.xml.soap.Detail;

import org.springframework.core.annotation.Order;

import plalashop.domain.Advertise;
import plalashop.domain.Customer;
import plalashop.domain.GroupProduct;
import plalashop.domain.ImgMapping;
import plalashop.domain.Orders;
import plalashop.domain.Product;
import plalashop.domain.ProductType;
import plalashop.domain.User;
import plalashop.utils.Utils;


public class PlalaShopsService{
	static String host = "jdbc:mysql://localhost:3306/plalashops";
	static String userDb = "root";
	static String pass = "root";
	
	
	public static List<User> getUserByUserObj(User user) throws Exception {
		StringBuilder sql = new StringBuilder("select * from user u where 1=1 ");
		sql.append(Utils.whereInjection(user.getUserLogin(), "user_login"));
		sql.append(Utils.whereInjection(user.getPassword(), "password"));
		sql.append(Utils.whereInjection(user.getEmail(), "email"));
		sql.append(Utils.whereInjection(user.getRole(), "role"));
		sql.append(Utils.whereInjection(user.getUserType(), "user_type"));
		sql.append(Utils.whereInjection(user.getPhone(), "mobile_no"));
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<User> userList = new ArrayList<User>();
		while (resultSet.next()) {
			userList.add(new User(resultSet));
		}
		connection.close();
		return userList;
	}
	
	
	public static void insertProductType(String productTypeName ,String fromName,Long groupProductId) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO product_type (product_type_name,form_name,group_product_id)VALUES(?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, productTypeName);
		statement.setString(2, fromName);
		statement.setLong(3, groupProductId);
		statement.executeUpdate();
	}
	
	public static List<ProductType> getProductTypeByProductTypeObj(ProductType productType) throws Exception{
		StringBuilder sql = new StringBuilder("select * from product_type p where 1 = 1 ");
		sql.append(Utils.whereInjection(productType.getProductTypeId(), "product_type_id"));
		sql.append(Utils.whereInjection(productType.getProductTypeName(), "product_type_name"));
		sql.append(Utils.whereInjection(productType.getFormName(), "form_name"));
		sql.append(Utils.whereInjection(productType.getGroupProductId(), "group_product_id"));
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<ProductType> productTypeList = new ArrayList<ProductType>();
		while (resultSet.next()) {
			productTypeList.add(new ProductType(resultSet));
		}
		connection.close();
		return productTypeList;
	} 
	
	
	public static void deleteProductType(String productTypeName) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="delete from product_type where product_type_id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productTypeName);
		statement.executeUpdate();
	}
	
	
	public static void insertProducts(Product product) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO products(product_no,product_name,product_type,price,sale_price,description,sex,special_class)VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getProductNo());
		statement.setString(2, product.getProductName());
		statement.setString(3, product.getProductType());
		statement.setDouble(4, product.getPrice());
		statement.setDouble(5, product.getSalePrice());
		statement.setString(6, product.getDescription());
		statement.setString(7, product.getSex());
		statement.setString(8, product.getSpecialClass());
		statement.executeUpdate();
	}
	
	public static List<Product> getProductsByProductsObj(Product product) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT *, (SELECT m.file_name FROM img_maping m WHERE m.product_id = p.product_id LIMIT 1) file_name FROM products p where 1 = 1 ");
		sql.append(Utils.whereInjection(product.getProductId(), "product_id"));
		sql.append(Utils.whereInjection(product.getProductNo(), "product_no"));
		sql.append(Utils.whereInjection(product.getProductType(), "product_type"));
		sql.append(Utils.whereInjection(product.getProductName(), "product_name"));
		sql.append(Utils.whereInjection(product.getPrice(), "price"));
		sql.append(Utils.whereInjection(product.getSalePrice(), "sale_price"));
		sql.append(Utils.whereInjection(product.getDescription(), "description"));
		sql.append(Utils.whereInjection(product.getSex(), "sex"));
		sql.append(Utils.whereInjection(product.getSpecialClass(), "special_class"));
		sql.append(" order by product_no ");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<Product> productList = new ArrayList<Product>();
		while (resultSet.next()) {
			productList.add(new Product(resultSet));
		}
		connection.close();
		return productList;
	}
	public static List<Product> getNewProduct() throws Exception{
		StringBuilder sql = new StringBuilder("SELECT p.* ,(SELECT m.file_name FROM img_maping m WHERE m.product_id = p.product_id LIMIT 1) file_name FROM products p ORDER BY p.product_id DESC LIMIT 10");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<Product> productList = new ArrayList<Product>();
		while (resultSet.next()) {
			productList.add(new Product(resultSet));
		}
		connection.close();
		return productList;
	}
	
	public static void deleteProducts(String productId) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql =" Delete from products where product_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productId);
		statement.executeUpdate();
	}
	public static void updateProducts(Product product) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE products ");
		sql.append(" SET product_name = ?,      ");
		sql.append("   product_type = ?,        ");
		sql.append("   price = ?,               ");
		sql.append("   sale_price = ?,          ");
		sql.append("   description = ?,         ");
		sql.append("   sex = ?,                  ");
		sql.append("   special_class = ?         ");
		sql.append(" WHERE product_id = ?		");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getProductType());
		statement.setDouble(3, product.getPrice());
		statement.setDouble(4, product.getSalePrice());
		statement.setString(5, product.getDescription());
		statement.setString(6, product.getSex());
		statement.setString(7, product.getSpecialClass());
		statement.setLong(8, product.getProductId());
		
		statement.executeUpdate();
	}
	

	public static void insertImgMapping(String productId ,String fileName) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO img_maping(product_id,file_name)VALUES (?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productId);
		statement.setString(2, fileName);
		statement.executeUpdate();
	}
	public static void deleteImgMapping(String imageId) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="DELETE FROM img_maping WHERE imageId = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, imageId);
		statement.executeUpdate();
	}
	
	public static List<ImgMapping> getImgMapping(String productId) throws Exception{
		StringBuilder sql = new StringBuilder("select * from img_maping p where 1 = 1 ");
		sql.append(Utils.whereInjection(productId, "product_id"));
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<ImgMapping> imgList = new ArrayList<ImgMapping>();
		while (resultSet.next()) {
			imgList.add(new ImgMapping(resultSet));
		}
		connection.close();
		return imgList;
	}
	
	
	public static List<Advertise> getAdvertise(Advertise advertise) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT * FROM  advertise WHERE 1 = 1 ");
		sql.append(Utils.whereInjection(advertise.getAdvertiseId(), "advertise_id"));
		sql.append(Utils.whereInjection(advertise.getAdvertiseOwner(), "advertise_owner"));
//		sql.append(Utils.whereInjection(advertise.getFileName(), "file_name"));
		sql.append(Utils.whereInjection(advertise.getLinkAdvertise(), "link_advertise"));
		sql.append(Utils.whereInjection(advertise.getQueue(), "queue"));
		sql.append(" ORDER BY queue");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<Advertise> imgList = new ArrayList<Advertise>();
		while (resultSet.next()) {
			imgList.add(new Advertise(resultSet));
		}
		connection.close();
		return imgList;
	}
	
	public static void deleteAdvertise(String advertiseId) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="DELETE FROM advertise WHERE advertise_id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, advertiseId);
		statement.executeUpdate();
	}
	
	public static void insertAdvertise(Advertise advertise) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO advertise (advertise_owner,link_advertise,queue)VALUES(?,?,?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, advertise.getAdvertiseOwner());
		statement.setString(2, advertise.getLinkAdvertise());
		statement.setLong(3, advertise.getQueue() != null ? advertise.getQueue() : 999);
		statement.executeUpdate();
	}
	
	public static void updateAdvertise(Advertise advertise) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE advertise ");
		sql.append(" SET advertise_owner = ?,      ");
		sql.append("   file_name = ?,            ");
		sql.append("   link_advertise = ?,       ");
		sql.append("   queue = ?                 ");
		sql.append(" WHERE advertise_id = ?      ");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		statement.setString(1, advertise.getAdvertiseOwner());
		statement.setString(2, advertise.getFileName(1));
		statement.setString(3, advertise.getLinkAdvertise());
		statement.setLong(4, advertise.getQueue());
		statement.setLong(5, advertise.getAdvertiseId());
		statement.executeUpdate();
	}
	public static void updateProductType(ProductType productType) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE product_type SET			");
		sql.append(" product_type_name = ? ,  ");
		sql.append(" group_product_id = ? ,  ");
		sql.append(" file_name = ?                    ");
		sql.append(" WHERE product_type_id = ?  ");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		statement.setString(1, productType.getProductTypeName());
		statement.setLong(2, productType.getGroupProductId());
		statement.setString(3, productType.getFileName(1));
		statement.setLong(4, productType.getProductTypeId());
		statement.executeUpdate();
	}
	
	public static void insertUser(User user) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO user (user_login, PASSWORD, email, role, user_type)VALUES(?, ?, ?, ?, ?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getUserLogin());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getRole());
		statement.setString(5, user.getUserType());
		statement.executeUpdate();
	} 
	
	public static List<GroupProduct> getGroupProduct(GroupProduct groupProduct) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT * FROM group_product  WHERE 1 = 1 ");
		sql.append(Utils.whereInjection(groupProduct.getGroupProductId(), "group_product_id"));
		sql.append(Utils.whereInjection(groupProduct.getGroupName(), "group_name"));
		sql.append(Utils.whereInjection(groupProduct.getFree1(), "free1"));
		sql.append(Utils.whereInjection(groupProduct.getFree2(), "free2"));
		sql.append(Utils.whereInjection(groupProduct.getFree3(), "free3"));
		sql.append(" ORDER BY group_name");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<GroupProduct> groupProductList = new ArrayList<GroupProduct>();
		while (resultSet.next()) {
			groupProductList.add(new GroupProduct(resultSet));
		}
		connection.close();
		return groupProductList;
	}
	
	public static void insertGroupProduct(GroupProduct groupProduct) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO group_product(group_name,free1,free2,free3)VALUES(?,?,?,?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, groupProduct.getGroupName());
		statement.setString(2, groupProduct.getFree1());
		statement.setString(3, groupProduct.getFree2());
		statement.setString(4, groupProduct.getFree3());
		statement.executeUpdate();
	}
	
	
	public static void updateGroupProduct(GroupProduct groupProduct) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE group_product ");
		sql.append(" SET group_name = ? ,            ");
		sql.append(" file_name = ? ,                 ");
		sql.append(" free1 =  ?,                     ");
		sql.append(" free2 = ? ,                     ");
		sql.append(" free3 = ?                       ");
		sql.append(" WHERE                           ");
		sql.append(" group_product_id = ?            ");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		statement.setString(1, groupProduct.getGroupName());
		statement.setString(2, groupProduct.getFileName(1));
		statement.setString(3, groupProduct.getFree1());
		statement.setString(4, groupProduct.getFree2());
		statement.setString(5, groupProduct.getFree3());
		statement.setLong(6, groupProduct.getGroupProductId());
		statement.executeUpdate();
	}
	
	public static void deleteGroupProduct(String groupProductId) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="DELETE FROM group_product WHERE group_product_id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, groupProductId);
		statement.executeUpdate();
	}
	
	
	
	public static void insertOrder(Integer customerID,String PaymentID,String login ) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO orders(customerID,PaymentID,OrderDate,LastUpdateDate,UserLastUpdate)VALUES('"+customerID+"','"+PaymentID+"',DATE(NOW()),DATE(NOW()),'"+login+"')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.executeUpdate();
	}
	
	public static List<Orders> getPayDetail( Orders orders) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT * FROM orders WHERE CustomerID = 1 AND PaymentID ='1'");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql.toString());
		List<Orders> ortailList = new ArrayList<Orders>();
		while (resultSet.next()) {
			ortailList.add(new Orders(resultSet));
		}
		connection.close();
		return ortailList;
	}
	
	public static void insertOrderDetail(Long orderID,String productId,String qty,Double price) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO order_details(Order_ID,Product_ID,Quantity,Price,status)VALUES('"+orderID+"','"+productId+"','"+qty+"',"+price+",'buy')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.executeUpdate();
	}
	
	public static void insertCustomer(Customer customer) throws Exception{ 
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		Connection connection = DriverManager.getConnection(host, userDb, pass); 
		String sql =" INSERT INTO plalashops.customer (fullName, address,zipcode, userId)VALUES(?, ?, ?, ?) "; 
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, customer.getFullName()); 
		statement.setString(2, customer.getAddress()); 
		statement.setString(3, customer.getZipcode()); 
		statement.setLong(4, customer.getUserId()); 
		statement.executeUpdate(); 
		} 


		public static List<Customer> getCustomer(Customer customer) throws Exception{ 
		StringBuilder sql = new StringBuilder(" SELECT * FROM customer WHERE 1 = 1 "); 
		sql.append(Utils.whereInjection(customer.getCustomerId(), "customerId")); 
		sql.append(Utils.whereInjection(customer.getCustomerId(), "fullName")); 
		sql.append(Utils.whereInjection(customer.getCustomerId(), "address")); 
		sql.append(Utils.whereInjection(customer.getCustomerId(), "zipcode")); 
		sql.append(Utils.whereInjection(customer.getCustomerId(), "userId")); 
		sql.append(" ORDER BY customerId "); 
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		Connection connection = DriverManager.getConnection(host, userDb, pass); 
		Statement statement = connection.createStatement(); 
		ResultSet resultSet = statement.executeQuery(sql.toString()); 
		List<Customer> customerList = new ArrayList<Customer>(); 
		while (resultSet.next()) { 
		customerList.add(new Customer(resultSet)); 
		} 
		connection.close(); 
		return customerList; 
		} 

		public static void deleteCustomer(String customerId) throws Exception{ 
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		Connection connection = DriverManager.getConnection(host, userDb, pass); 
		String sql =" DELETE FROM customer WHERE customerId = ? "; 
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, customerId); 
		statement.executeUpdate(); 
		} 

		public static void updateCustomer(Customer customer) throws Exception{ 
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		Connection connection = DriverManager.getConnection(host, userDb, pass); 
		StringBuilder sql = new StringBuilder(); 
		sql.append(" UPDATE customer SET "); 
		sql.append(" fullName = ? , "); 
		sql.append(" address = ? , "); 
		sql.append(" zipcode = ? , "); 
		sql.append(" WHERE "); 
		sql.append(" customerId = ? "); 
		PreparedStatement statement = connection.prepareStatement(sql.toString()); 
		statement.setString(1, customer.getFullName()); 
		statement.setString(2, customer.getAddress()); 
		statement.setString(3, customer.getZipcode()); 
		statement.setLong(4, customer.getCustomerId()); 
		statement.executeUpdate(); 
		}

}

  
