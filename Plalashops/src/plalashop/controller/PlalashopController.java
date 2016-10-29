package plalashop.controller;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import plalashop.domain.Advertise;
import plalashop.domain.GroupProduct;
import plalashop.domain.ImgMapping;
import plalashop.domain.Product;
import plalashop.domain.ProductType;
import plalashop.domain.User;
import plalashop.service.impl.PlalaShopsService;
import plalashop.service.impl.ValidateService;
import plalashop.utils.Utils;

@Controller
public class PlalashopController {
	
	public final static String UPLOAD_DIRECTORY = "D:/FilesPlalashops/";
	 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    
   
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
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		
		request.setAttribute(Utils.MENU_SELECT, "Home");
		return "adminHome";
	}

	@RequestMapping(value = "/adminProductType")
	public String goToProductType(HttpServletRequest request) throws Exception {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setAttribute(Utils.MENU_SELECT, "ProductType");
		List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
		request.setAttribute("groupProductList", groupProductList);
		return "adminProductType";
	}

	@RequestMapping(value="/insertProductType")
	public String insertProductType(HttpServletRequest request) throws Exception{
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute(Utils.MENU_SELECT, "ProductType");
		String productTypeName = request.getParameter("productType");
		String groupProduct = request.getParameter("groupProduct");
		String fromName = request.getParameter("fromName") != null && request.getParameter("fromName").toString().length() > 0 ? "Y" : "N";
		if(productTypeName != null && productTypeName.length() > 0 && groupProduct != null && groupProduct.length() > 0){
			try {
				Long groupProductId = Long.parseLong(groupProduct);
				request.setAttribute(Utils.MENU_SELECT, "ProductType");
				ProductType productType = new ProductType();
				productType.setProductTypeName(productTypeName);
				productType.setGroupProductId(groupProductId);
				List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(productType);
				if(productTypeList != null && productTypeList.size() == 0){
					PlalaShopsService.insertProductType(productTypeName,fromName,groupProductId);
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
			request.setAttribute("Fail", "Please input Group Product && Product Type");
		}
		List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
		request.setAttribute("groupProductList", groupProductList);
		return "adminProductType";
	}
	
	@RequestMapping(value = "/addImageProduct")
	public String addImageProduct(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
			request.setAttribute(Utils.MENU_SELECT, "ProductType");
			String id = request.getParameter("id");
			ProductType productType = new ProductType();
			productType.setProductTypeId(Long.parseLong(id));
			productType = PlalaShopsService.getProductTypeByProductTypeObj(productType).get(0);
			request.setAttribute("productType", productType);
			request.setAttribute("action", "uploadItem");
			request.getSession().setAttribute("id",id);
			request.setAttribute("groupProductList", groupProductList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProductType"; 
	}

	@RequestMapping(value = "/getProductTypeList")
	public String getProductTypeList(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "ProductType");
			ProductType productType = new ProductType();
			productType.setProductTypeName(request.getParameter("productType"));
			productType.setGroupProductId(request.getParameter("groupProduct"));
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(productType);
			List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("groupProductList", groupProductList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProductType";
	}
	
	@RequestMapping(value = "/deleteProductType")
	public String deleteProductType(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "ProductType");
			PlalaShopsService.deleteProductType(request.getParameter("productType"));
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("success", "Delete \""+request.getParameter("productType")+"\" success");
			request.setAttribute("groupProductList", groupProductList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProductType";
	}
	
	@RequestMapping(value = "/adminProduct")
	public String adminProduct(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
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
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
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
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
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
		String specialClass = request.getParameter("specialClass");
		String productNo = request.getParameter("productNo");
		String errorMsg = ValidateService.validateAddProuct(productType, productName, price, salePrice, description, sex,productNo);
		if(errorMsg.equals("")){
			try {
				Product product = new Product();
				product.setProductNo(productNo);
				product.setProductName(productName);
				product.setProductType(productType);
				product.setPrice(Double.parseDouble(price));
				product.setSalePrice(Double.parseDouble(salePrice));
				product.setDescription(description);
				product.setSex(sex);
				product.setSpecialClass(specialClass);
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
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
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
			String specialClass = request.getParameter("specialClass");
			Product product = new Product();
			product.setProductType(productType);
			product.setProductName(productName);
			product.setProductNo(productNo);
			product.setSex(sex);
			product.setSpecialClass(specialClass);
			List<Product> productList = PlalaShopsService.getProductsByProductsObj(product);
			request.setAttribute("productList", productList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/adminDeleteProducts")
	public String adminDeleteProducts(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
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
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			Product product = new Product();
			product.setProductId(new Long(request.getParameter("productId")));
			product = PlalaShopsService.getProductsByProductsObj(product).get(0);
			request.setAttribute("editProduct", product);

			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "editProduct");


		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	@RequestMapping(value = "/editProductsAction")
	public String editProductsAction(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			
			String productId = request.getParameter("productId");
			String productType = request.getParameter("producrType");
			String productName = request.getParameter("productName");
			String price = request.getParameter("price");
			String salePrice = request.getParameter("salePrice");
			String description = request.getParameter("description");
			String sex = request.getParameter("sex");
			String specialClass = request.getParameter("specialClass");
			String productNo = request.getParameter("productNo");
			String errorMsg = ValidateService.validateAddProuct(productType, productName, price, salePrice, description, sex);
			if(errorMsg.equals("")){
				Product product = new Product();
				product.setProductId(new Long(productId));
				product.setProductType(productType);
				product.setProductName(productName);
				product.setPrice(new Double(price));
				product.setSalePrice(new Double(salePrice));
				product.setDescription(description);
				product.setSex(sex);
				product.setSpecialClass(specialClass);
				PlalaShopsService.updateProducts(product);
				request.setAttribute("success", "Save \""+productName+"\" in system success");
				adminSearchProducts(request);
			}else{
				request.setAttribute("Fail", errorMsg);
				adminShopEditProducts(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/adminShopAddImageProducts")
	public String adminShopAddImageProducts(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.getSession().setAttribute("productId", request.getParameter("productId"));
			request.setCharacterEncoding("UTF-8");
			Product product = new Product();
			product.setProductId(new Long(request.getParameter("productId")));
			product = PlalaShopsService.getProductsByProductsObj(product).get(0);
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("addImageProduct", product);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "addImageProduct");
			List<ImgMapping> imageList = PlalaShopsService.getImgMapping(request.getParameter("productId"));
            request.setAttribute("imageList",imageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/uploadFileImage")
	public String uploadFileImage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setCharacterEncoding("UTF-8");
		String productId = (String) request.getSession().getAttribute("productId");
		try {
			Product product = new Product();
			product.setProductId(new Long(productId));
			product = PlalaShopsService.getProductsByProductsObj(product).get(0);
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("addImageProduct", product);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "addImageProduct");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        if (!ServletFileUpload.isMultipartContent(request)) {
        	request.setAttribute("Fail", "Please Select File!");
            return "adminProduct";
        }
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = "IMG_"+(new Date().getTime())+(new File(item.getName()).getName());
                        String extension = fileName.substring(fileName.lastIndexOf("."));
                        if(".png".equalsIgnoreCase(extension)){
	                        String filePath = uploadPath + fileName;
	                        File storeFile = new File(filePath);
	                        item.write(storeFile);
	                        PlalaShopsService.insertImgMapping(productId, fileName);
	                        request.setAttribute("success", "Upload image success");
                        }else{
                        	request.setAttribute("Fail", "File is not PNG");
                        }
                    }
                }
            }
            List<ImgMapping> imageList = PlalaShopsService.getImgMapping(productId);
            request.setAttribute("imageList",imageList);
        } catch (Exception ex) {
            request.setAttribute("Fail", "Please Select File!");
        }
        return "adminProduct";
    }

	@RequestMapping(value = "/deleteImageProduct")
	public String deleteImageProduct(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
//			request.getSession().setAttribute("imageId", request.getParameter("imageId"));
			request.setCharacterEncoding("UTF-8");
			Product product = new Product();
			String productId = (String) request.getSession().getAttribute("productId");
			String imageId = (String) request.getParameter("imageId");
			PlalaShopsService.deleteImgMapping(imageId);
			product.setProductId(new Long(productId));
			product = PlalaShopsService.getProductsByProductsObj(product).get(0);
			List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(new ProductType());
			request.setAttribute("productTypeList", productTypeList);
			request.setAttribute("addImageProduct", product);
			request.setAttribute(Utils.MENU_SELECT, "Products");
			request.setAttribute("action", "addImageProduct");
			List<ImgMapping> imageList = PlalaShopsService.getImgMapping(productId);
			request.setAttribute("imageList",imageList);
			request.setAttribute("success", "Delete image success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminProduct";
	}
	
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "register";
	}
	
	@RequestMapping(value = "/mobileHeader")
	public String mobileHeader(HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "mobileHeader";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "login";
	}
	
	@RequestMapping(value = "/forgot")
	public String forgot(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "forgot";
	}
	
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "home";
	}
	
	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "detail";
	}
	@RequestMapping(value = "/adminAdvertise")
	public String adminAdvertise(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setAttribute("action", "");
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			List<Advertise> advertiseList = PlalaShopsService.getAdvertise(new Advertise());
			request.setAttribute("advertiseList", advertiseList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminAdvertise";
	}
	@RequestMapping(value = "/addAdvertise")
	public String addAdvertise(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String advertiseOwner = request.getParameter("advertiseOwner");
			String linkAdvertise = request.getParameter("linkAdvertise");
			String queue = request.getParameter("queue");
			if(advertiseOwner != null && advertiseOwner.length() > 0 && linkAdvertise != null && linkAdvertise.length() > 0){
				Long queueLong = queue != null && queue.length() > 0 ? Long.parseLong(queue) : null;
				PlalaShopsService.insertAdvertise(new Advertise(advertiseOwner, linkAdvertise,queueLong));
				
			}else{
				request.setAttribute("Fail", "Advertise Owner And Link Advertise Is not empty");
			}
			List<Advertise> advertiseList = PlalaShopsService.getAdvertise(new Advertise());
			request.setAttribute("advertiseList", advertiseList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminAdvertise";
	}
	@RequestMapping(value = "/deleteAdvertise")
	public String deleteAdvertise(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String id = request.getParameter("id");
			PlalaShopsService.deleteAdvertise(id);
			List<Advertise> advertiseList = PlalaShopsService.getAdvertise(new Advertise());
			String linkAdvertise = request.getParameter("linkAdvertise");
			request.setAttribute("advertiseList", advertiseList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminAdvertise";
	}
	@RequestMapping(value = "/editAdvertise")
	public String editAdvertise(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String id = request.getParameter("id");
			Advertise advertise = new Advertise();
			advertise.setAdvertiseId(Long.parseLong(id));
			advertise = PlalaShopsService.getAdvertise(advertise).get(0);
			request.setAttribute("advertise", advertise);
			request.setAttribute("action", "edit");
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminAdvertise";
	}
	@RequestMapping(value = "/editAdvertiseAction")
	public String editAdvertiseAction(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String id = request.getParameter("id");
			String advertiseOwner = request.getParameter("advertiseOwner");
			String linkAdvertise = request.getParameter("linkAdvertise");
			String queue = request.getParameter("queue");
			Advertise advertise = new Advertise();
			advertise.setAdvertiseId(Long.parseLong(id));
			advertise = PlalaShopsService.getAdvertise(advertise).get(0);
			advertise.setAdvertiseOwner(advertiseOwner);
			advertise.setLinkAdvertise(linkAdvertise);
			Long queueLong = queue != null && queue.length() > 0 ? Long.parseLong(queue) : null;
			advertise.setQueue(queueLong);
			PlalaShopsService.updateAdvertise(advertise);
			adminAdvertise(request);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminAdvertise";
	}
	@RequestMapping(value = "/addImageAdvertise")
	public String addImageAdvertise(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String id = request.getParameter("id");
			Advertise advertise = new Advertise();
			advertise.setAdvertiseId(Long.parseLong(id));
			advertise = PlalaShopsService.getAdvertise(advertise).get(0);
			request.setAttribute("advertise", advertise);
			request.setAttribute("action", "uploadItem");
			request.getSession().setAttribute("id",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminAdvertise"; 
	}
	
	@RequestMapping(value = "/uploadFileAdvertise")
	public String uploadFileAdvertise(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setAttribute(Utils.MENU_SELECT, "Advertise");
		request.setCharacterEncoding("UTF-8");
		String id = (String) request.getSession().getAttribute("id");
		Advertise advertise = new  Advertise();
		advertise.setAdvertiseId(new Long(id));
		advertise = PlalaShopsService.getAdvertise(advertise).get(0);
		request.setAttribute("advertise", advertise);
		request.setAttribute("action", "uploadItem");
        if (!ServletFileUpload.isMultipartContent(request)) {
        	request.setAttribute("Fail", "Please Select File!");
            return "adminAdvertise";
        }
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = "IMG_"+(new Date().getTime())+(new File(item.getName()).getName());
                        String extension = fileName.substring(fileName.lastIndexOf("."));
                        if(".png".equalsIgnoreCase(extension)){
	                        String filePath = uploadPath + fileName;
	                        File storeFile = new File(filePath);
	                        item.write(storeFile);
	                        advertise.setFileName(fileName);
	                        PlalaShopsService.updateAdvertise(advertise);
	                        request.setAttribute("success", "Upload image success");
                        }else{
                        	request.setAttribute("Fail", "File is not PNG");
                        }
                    }
                }
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            request.setAttribute("Fail", "Please Select File!");
        }
        
        return adminAdvertise(request);
    }
	
	public String checkSession(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(Utils.SESSION_USER_KEY);
		if(user == null){
			request.setAttribute("Fail", "Session Time out !");
			request.setAttribute("message", "Session Time out !");
			return "error";
		}else{
			return "";
		}
	}
	
	
	@RequestMapping(value = "/loginApp")
	public String loginApp(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		List<User> userList = PlalaShopsService.getUserByUserObj(user);
		if(userList != null && userList.size() > 0){
			user = userList.get(0);
			request.getSession().setAttribute(Utils.SESSION_USER_KEY,user );
			request.setAttribute("islogin", "true");
			request.setAttribute("username", email);
			request.setAttribute("password", password);
			return appHome(request);
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value = "/registerAction")
	public String registerAction(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String phone = request.getParameter("phone");
		
		User user = new User();
		user.setUserLogin(email);
		List<User> userList = PlalaShopsService.getUserByUserObj(user);
		if(userList != null && userList.size() > 0){
			request.setAttribute("message", "E-mail is no longer available.");
			return "register";
		}
		user = new User();
		user.setPhone(phone);
		userList = PlalaShopsService.getUserByUserObj(user);
		if(userList != null && userList.size() > 0){
			request.setAttribute("message", "Mobile No is no longer available.");
			return "register";
		}
		
		user = new User();
		user.setUserLogin(email);
		user.setPhone(phone);
		user.setEmail(email);
		user.setRole("user");
		user.setPassword(password);
		user.setUserType(userType);
		PlalaShopsService.insertUser(user);
		return loginApp(request);
	}
	
	@RequestMapping(value = "/appHome")
	public String appHome(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("actionPage", "initHome");
		
		List<Advertise> advertiseList = PlalaShopsService.getAdvertise(new Advertise());
		request.setAttribute("advertiseList", advertiseList);
		
		
		Product product = new Product();
		product.setSpecialClass("promotion");
		List<Product> productList = PlalaShopsService.getProductsByProductsObj(product);
		request.setAttribute("promotionProductList", productList);
		
		product = new Product();
		product.setSpecialClass("hot");
		productList = PlalaShopsService.getProductsByProductsObj(product);
		request.setAttribute("hotProductList", productList);
		
		product = new Product();
		product.setSpecialClass("food");
		productList = PlalaShopsService.getProductsByProductsObj(product);
		request.setAttribute("foodProductList", productList);
		
		productList = PlalaShopsService.getNewProduct();
		request.setAttribute("newProductList", productList);
		
		
		request.setAttribute(Utils.MENU_SELECT, "Product");
		return "mobileHeader";
	}
	
	@RequestMapping(value = "/uploadFileProductType")
	public String uploadFileProductType(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setAttribute(Utils.MENU_SELECT, "ProductType");
		request.setCharacterEncoding("UTF-8");
		String id = (String) request.getSession().getAttribute("id");
		ProductType productType = new  ProductType();
		productType.setProductTypeId(new Long(id));
		productType = PlalaShopsService.getProductTypeByProductTypeObj(productType).get(0);
		request.setAttribute("productType", productType);
		request.setAttribute("action", "uploadItem");
		List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
		request.setAttribute("groupProductList", groupProductList);
        if (!ServletFileUpload.isMultipartContent(request)) {
        	request.setAttribute("Fail", "Please Select File!");
            return "adminProductType";
        }
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = "IMG_"+(new Date().getTime())+(new File(item.getName()).getName());
                        String extension = fileName.substring(fileName.lastIndexOf("."));
                        if(".png".equalsIgnoreCase(extension)){
	                        String filePath = uploadPath + fileName;
	                        File storeFile = new File(filePath);
	                        item.write(storeFile);
	                        productType.setFileName(fileName);
	                        PlalaShopsService.updateProductType(productType);
	                        List<ProductType> productTypeList = new ArrayList<ProductType>();
	                        productTypeList.add(productType);
	                        request.setAttribute("productTypeList", productTypeList);
	                        request.setAttribute("success", "Upload image success");
	                        request.setAttribute("action", "");
                        }else{
                        	request.setAttribute("Fail", "File is not PNG");
                        }
                    }
                }
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            request.setAttribute("Fail", "Please Select File!");
        }
        
        return "adminProductType";
    }
	
	
	
	@RequestMapping(value = "/appGroup")
	public String appGroup(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("actionPage", "intiGroup");
		List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
		request.setAttribute("groupProductList",groupProductList);
		
		return "mobileHeader";
	}
	
	@RequestMapping(value = "/appShop")
	public String appShop(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("actionPage", "intiShop");
		String groupProduct = (String) request.getParameter("groupProduct");
		ProductType productType = new ProductType();
		productType.setGroupProductId(groupProduct);
		List<ProductType> productTypeList = PlalaShopsService.getProductTypeByProductTypeObj(productType);
		request.setAttribute("productTypeList",productTypeList);
		
		return "mobileHeader";
	}
	
	
	@RequestMapping(value = "/gotoProductItem")
	public String gotoProductItem(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8");
		String productType = (String) request.getParameter("productType");
		Product product = new Product();
		product.setProductType(productType);
		List<Product> productList = PlalaShopsService.getProductsByProductsObj(product);
		request.setAttribute("actionPage", "intiItem");
		
		request.setAttribute("productList",productList);
		
		return "mobileHeader";
	}
	
	@RequestMapping(value = "/showDetailItem")
	public String showDetailItem(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8");
		String id = (String) request.getParameter("id");
		Product product = new Product();
		product.setProductId(Long.parseLong(id));
		product = PlalaShopsService.getProductsByProductsObj(product).get(0);
		List<ImgMapping> imgList = PlalaShopsService.getImgMapping(id);
		request.setAttribute("actionPage", "ShowDetail");
		request.setAttribute("imgList", imgList);
		request.setAttribute("product",product);
		return "mobileHeader";
	}
	
	
	@RequestMapping(value = "/adminGroupProduct")
	public String adminGroupProduct(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setAttribute(Utils.MENU_SELECT, "GroupProduct");
		return "adminGroupProduct";
	}
	
	
	@RequestMapping(value = "/getGroupProductList")
	public String getGroupProductList(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "GroupProduct");
			GroupProduct groupProduct = new GroupProduct();
			groupProduct.setGroupName(request.getParameter("groupProduct"));
			List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(groupProduct);
			request.setAttribute("groupProductList", groupProductList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminGroupProduct";
	}
	
	@RequestMapping(value = "/insertGroupProduct")
	public String insertGroupProduct(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "GroupProduct");
			String groupProductName = request.getParameter("groupProduct");
			if(groupProductName != null && groupProductName.length() > 0){
				GroupProduct groupProduct = new GroupProduct();
				groupProduct.setGroupName(groupProductName);
				PlalaShopsService.insertGroupProduct(groupProduct);
				getGroupProductList(request);
			}else{
				request.setAttribute("Fail", "Please input Group Product");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminGroupProduct";
	}
	
	@RequestMapping(value = "/uploadImageGrouproduct")
	public String uploadImageGrouproduct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		request.setAttribute(Utils.MENU_SELECT, "GroupProduct");
		request.setCharacterEncoding("UTF-8");
		String id = (String) request.getSession().getAttribute("id");
		GroupProduct groupProduct = new  GroupProduct();
		groupProduct.setGroupProductId(new Long(id));
		groupProduct = PlalaShopsService.getGroupProduct(groupProduct).get(0);
		request.setAttribute("groupProduct", groupProduct);
		request.setAttribute("action", "uploadItem");
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("Fail", "Please Select File!");
			return "adminGroupProduct";
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		String uploadPath = UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					if (!item.isFormField()) {
						String fileName = "IMG_"+(new Date().getTime())+(new File(item.getName()).getName());
						String extension = fileName.substring(fileName.lastIndexOf("."));
						if(".png".equalsIgnoreCase(extension)){
							String filePath = uploadPath + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile);
							groupProduct.setFileName(fileName);
							PlalaShopsService.updateGroupProduct(groupProduct);
							List<GroupProduct> groupProductList = new ArrayList<GroupProduct>();
							groupProductList.add(groupProduct);
							request.setAttribute("groupProductList", groupProductList);
							request.setAttribute("success", "Upload image success");
							request.setAttribute("action", "");
						}else{
							request.setAttribute("Fail", "File is not PNG");
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			request.setAttribute("Fail", "Please Select File!");
		}
		
		return "adminGroupProduct";
	}
	
	@RequestMapping(value = "/addImageGrouproduct")
	public String addImageGrouproduct(HttpServletRequest request) {
		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setAttribute(Utils.MENU_SELECT, "GroupProduct");
			String id = request.getParameter("id");
			GroupProduct groupProduct = new GroupProduct();
			groupProduct.setGroupProductId(Long.parseLong(id));
			groupProduct = PlalaShopsService.getGroupProduct(groupProduct).get(0);
			request.setAttribute("groupProduct", groupProduct);
			request.setAttribute("action", "uploadItem");
			request.getSession().setAttribute("id",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminGroupProduct"; 
	}
	
	
	@RequestMapping(value = "/deleteGroupProduct")
	public String deleteGroupProduct(HttpServletRequest request) {

		String sestionMsg = checkSession(request);
		if("error".equals(sestionMsg))return "adminLoginAgen";
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "GroupProduct");
			PlalaShopsService.deleteGroupProduct(request.getParameter("groupProduct"));
			List<GroupProduct> groupProductList = PlalaShopsService.getGroupProduct(new GroupProduct());
			request.setAttribute("groupProductList", groupProductList);
			request.setAttribute("success", "Delete \""+request.getParameter("groupProduct")+"\" success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminGroupProduct"; 
	}

	
	@RequestMapping(value = "/tagName") 
	 public String tagName(HttpServletRequest request) { 
	  try { 
	   request.setCharacterEncoding("UTF-8"); 
	  } catch (Exception e1) { 
	   e1.printStackTrace(); 
	  } 
	  return "tagName"; 
	 }
	
	
	@RequestMapping(value = "/CartDetail") 
	 public String CartDetail(HttpServletRequest request) { 
	  try { 
	   request.setCharacterEncoding("UTF-8"); 
	  } catch (Exception e1) { 
	   e1.printStackTrace(); 
	  } 
	  return "CartDetail"; 
	 }
	
	@RequestMapping(value = "/payableDelivery") 
	 public String payableDelivery(HttpServletRequest request) { 
	  try { 
	   request.setCharacterEncoding("UTF-8"); 
	  } catch (Exception e1) { 
	   e1.printStackTrace(); 
	  } 
	  return "payableDelivery"; 
	 }
	
	@RequestMapping(value = "/point")  
	  public String point(HttpServletRequest request) {  
	   try {  
	    request.setCharacterEncoding("UTF-8");  
	   } catch (Exception e1) {  
	    e1.printStackTrace();  
	   }  

	   return "point" ;  
	  }
	
}

