package plalashop.service.impl;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import plalashop.domain.ImgMapping;
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
		sql.append(Utils.whereInjection(user.getRole(), "role"));
		sql.append(Utils.whereInjection(user.getUserType(), "user_type"));
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
	
	
	public static void insertProductType(String productTypeName) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO plalashops.product_type (product_type_name)VALUES(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, productTypeName);
		statement.executeUpdate();
	}
	
	public static List<ProductType> getProductTypeByProductTypeObj(ProductType productType) throws Exception{
		StringBuilder sql = new StringBuilder("select * from plalashops.product_type p where 1 = 1 ");
		sql.append(Utils.whereInjection(productType.getProductTypeId(), "product_type_id"));
		sql.append(Utils.whereInjection(productType.getProductTypeName(), "product_type_name"));
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
		String sql ="delete from plalashops.product_type where product_type_name = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productTypeName);
		statement.executeUpdate();
	}
	
	
	public static void insertProducts(Product product) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO plalashops.products(product_no,product_name,product_type,price,sale_price,description,sex)VALUES(?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getProductNo());
		statement.setString(2, product.getProductName());
		statement.setString(3, product.getProductType());
		statement.setDouble(4, product.getPrice());
		statement.setDouble(5, product.getSalePrice());
		statement.setString(6, product.getDescription());
		statement.setString(7, product.getSex());
		statement.executeUpdate();
	}
	
	public static List<Product> getProductsByProductsObj(Product product) throws Exception{
		StringBuilder sql = new StringBuilder("select * from plalashops.products p where 1 = 1 ");
		sql.append(Utils.whereInjection(product.getProductId(), "product_id"));
		sql.append(Utils.whereInjection(product.getProductNo(), "product_no"));
		sql.append(Utils.whereInjection(product.getProductType(), "product_type"));
		sql.append(Utils.whereInjection(product.getProductName(), "product_name"));
		sql.append(Utils.whereInjection(product.getPrice(), "price"));
		sql.append(Utils.whereInjection(product.getSalePrice(), "sale_price"));
		sql.append(Utils.whereInjection(product.getDescription(), "description"));
		sql.append(Utils.whereInjection(product.getSex(), "sex"));
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
		String sql =" Delete from plalashops.products where product_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productId);
		statement.executeUpdate();
	}
	public static void updateProducts(Product product) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE plalashops.products ");
		sql.append(" SET product_name = ?,      ");
		sql.append("   product_type = ?,        ");
		sql.append("   price = ?,               ");
		sql.append("   sale_price = ?,          ");
		sql.append("   description = ?,         ");
		sql.append("   sex = ?                  ");
		sql.append(" WHERE product_id = ?		");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getProductType());
		statement.setDouble(3, product.getPrice());
		statement.setDouble(4, product.getSalePrice());
		statement.setString(5, product.getDescription());
		statement.setString(6, product.getSex());
		statement.setLong(7, product.getProductId());
		
		statement.executeUpdate();
	}
	

	public static void insertImgMapping(String productId ,String fileName) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="INSERT INTO plalashops.img_maping(product_id,file_name)VALUES (?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, productId);
		statement.setString(2, fileName);
		statement.executeUpdate();
	}
	public static void deleteImgMapping(String imageId) throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = DriverManager.getConnection(host, userDb, pass);
		String sql ="DELETE FROM plalashops.img_maping WHERE imageId = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, imageId);
		statement.executeUpdate();
	}
	
	public static List<ImgMapping> getImgMapping(String productId) throws Exception{
		StringBuilder sql = new StringBuilder("select * from plalashops.img_maping p where 1 = 1 ");
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


}
