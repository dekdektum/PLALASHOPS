package plalashop.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import plalashop.domain.Product;
import plalashop.domain.ProductType;
import plalashop.domain.User;
import plalashop.service.impl.PlalaShopsService;
import plalashop.service.impl.ValidateService;
import plalashop.utils.Utils;

@Controller
public class PlalashopController {
	@RequestMapping(value = "/adminLogin")
	public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter(Utils.USERNAME);
		String password = request.getParameter(Utils.PASSWORD);
		String login = "adminLoginAgen";
		if (username != null && !"".equals(username) && password != null && !"".equals(password)) {
			try {
				User user = new User();
				user.setUserLogin(username);
				user.setPassword(password);
				List<User> userList = PlalaShopsService.getUserByUserObj(user);
				if (userList != null && userList.size() > 0) {
					user = userList.get(0);
					if ("admin".equals(user.getRole()) || "superAdmin".equals(user.getRole())) {
						request.getSession().setAttribute(Utils.SESSION_USER_KEY, user);
						request.setAttribute(Utils.MENU, Utils.getMenuByRole(user.getRole()));
						request.setAttribute(Utils.MENU_SELECT, "Home");
						login = "adminHome";
					} else {
						request.setAttribute("message", "You is not Role Admin !!!!");
						login = "adminLoginAgen";
					}
				} else {
					request.setAttribute("message", "Username or Password invalid");
					login = "adminLoginAgen";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("message", "Username & Password is Mandatory");
			login = "adminLoginAgen";
		}

		return login;
	}

	@RequestMapping(value = "/loginAdmin")
	public ModelAndView loginAdmin() {
		return new ModelAndView("loginAdmin", "message", "Username Or Password invalid");
	}

	@RequestMapping(value = "/example")
	public String example() {
		return "example";
	}

	@RequestMapping(value = "/adminHome")
	public String goToHome(HttpServletRequest request) {
		request.setAttribute(Utils.MENU_SELECT, "Home");
		return "adminHome";
	}

	@RequestMapping(value = "/adminProductType")
	public String goToProductType(HttpServletRequest request) {
		request.setAttribute(Utils.MENU_SELECT, "ProductType");
		return "adminProductType";
	}

	@RequestMapping(value="/insertProductType")
	public String insertProductType(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute(Utils.MENU_SELECT, "ProductType");
		String productTypeName = request.getParameter("productType");
		if(productTypeName != null && productTypeName.length() > 0){
			try {
				request.setAttribute(Utils.MENU_SELECT, "ProductType");
				ProductType productType = new ProductType();
				productType.setProductTypeName(productTypeName);
				List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(productType);
				if(productTypeList != null && productTypeList.size() == 0){
					PlalaShopsService.insertProductType(productTypeName);
					getProductTypeList(request);
					request.setAttribute("success", "Save \""+productTypeName+"\" in system success");
				}else{
					request.setAttribute("Fail", "\""+productTypeName+"\" is Duplicate!");
				}
				
				request.setAttribute("productTypeList", productTypeList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("Fail", "Please input Product Type");
		}
		return "adminProductType";
	}

	@RequestMapping(value = "/getProductTypeList")
	public String getProductTypeList(HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "ProductType");
			ProductType productType = new ProductType();
			productType.setProductTypeName(request.getParameter("productType"));
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(productType);
			request.setAttribute("productTypeList", productTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProductType";
	}
	
	@RequestMapping(value = "/deleteProductType")
	public String deleteProductType(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "ProductType");
			PlalaShopsService.deleteProductType(request.getParameter("productType"));
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("success", "Delete \""+request.getParameter("productType")+"\" success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProductType";
	}
	
	@RequestMapping(value = "/adminProduct")
	public String adminProduct(HttpServletRequest request) {
		List<ProductType> productTypeList;
		try {
			request.setCharacterEncoding("UTF-8");
			productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "init");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/adminShopAddProducts")
	public String adminShopAddProducts(HttpServletRequest request) {
		List<ProductType> productTypeList;
		try {
			request.setCharacterEncoding("UTF-8");
			productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "addProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/adminAddProductsAction")
	public String adminAddProductsAction(HttpServletRequest request) {
		List<ProductType> productTypeList;
		try {
			request.setCharacterEncoding("UTF-8");
			productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "addProduct");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String productType = request.getParameter("producrType");
		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String salePrice = request.getParameter("salePrice");
		String description = request.getParameter("description");
		String sex = request.getParameter("sex");
		String errorMsg = ValidateService.validateAddProuct(productType, productName, price, salePrice, description, sex);
		if(errorMsg.equals("")){
			try {
				Product product = new Product();
				product.setProductNo(Utils.genProductNo());
				product.setProductName(productName);
				product.setProductType(productType);
				product.setPrice(Double.parseDouble(price));
				product.setSalePrice(Double.parseDouble(salePrice));
				product.setDescription(description);
				product.setSex(sex);
				PlalaShopsService.insertProducts(product);
				request.setAttribute("success", "Save \""+productName+"\" in system success");
				adminSearchProducts(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("Fail", errorMsg);
		}
		return "adminProduct";
	}
	
	
	@RequestMapping(value = "/adminSearchProducts")
	public String adminSearchProducts(HttpServletRequest request) {
		List<ProductType> productTypeList;
		try {
			request.setCharacterEncoding("UTF-8");
			productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "searchProduct");
			String productType = request.getParameter("producrType");
			String productName = request.getParameter("productName");
			String productNo = request.getParameter("productNo");
			String sex = request.getParameter("sex");
			Product product = new Product();
			product.setProductType(productType);
			product.setProductName(productName);
			product.setProductNo(productNo);
			product.setSex(sex);
			List<Product> productList = PlalaShopsService.getProductsByProductsObj(product);
			request.setAttribute("productList", productList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/adminDeleteProducts")
	public String adminDeleteProducts(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			PlalaShopsService.deleteProducts(request.getParameter("productId"));
			request.setAttribute("success", "Delete \""+request.getParameter("productId")+"\" success");
			adminSearchProducts(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/adminShopEditProducts")
	public String adminShopEditProducts(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			Product product = new Product();
			product.setProductId(new Long(request.getParameter("productId")));
			product = PlalaShopsService.getProductsByProductsObj(product).get(0);
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "editProduct");
			request.setAttribute("editProduct",product);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminProduct";
	}
	@RequestMapping(value = "/adminShopEditProducts")
	public String adminShopEditProducts(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			Product product = new Product();
			product.setProductId(new Long(request.getParameter("productId")));
			product = PlalaShopsService.getProductsByProductsObj(product).get(0);
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "editProduct");
			request.setAttribute("editProduct",product);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminProduct";
	}
	

}

