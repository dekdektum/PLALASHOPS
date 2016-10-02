package plalashop.controller;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		try {
			request.setCharacterEncoding("UTF-8");
			
			String productId = request.getParameter("productId");
			String productType = request.getParameter("producrType");
			String productName = request.getParameter("productName");
			String price = request.getParameter("price");
			String salePrice = request.getParameter("salePrice");
			String description = request.getParameter("description");
			String sex = request.getParameter("sex");
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
			List<ImgMapping> imageList = PlalaShopsService.getImgMapping(request.getParameter("productId"));
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
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String advertiseOwner = request.getParameter("advertiseOwner");
			String linkAdvertise = request.getParameter("linkAdvertise");
			String queue = request.getParameter("queue");
			Long queueLong = queue != null && queue.length() > 0 ? Long.parseLong(queue) : null;
			PlalaShopsService.insertAdvertise(new Advertise(advertiseOwner, linkAdvertise,queueLong));
			List<Advertise> advertiseList = PlalaShopsService.getAdvertise(new Advertise());
			request.setAttribute("advertiseList", advertiseList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "adminAdvertise";
	}
	@RequestMapping(value = "/deleteAdvertise")
	public String deleteAdvertise(HttpServletRequest request) {
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
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(Utils.MENU_SELECT, "Advertise");
			String id = request.getParameter("id");
			String advertiseOwner = request.getParameter("advertiseOwner");
			String linkAdvertise = request.getParameter("linkAdvertise");
			String queue = request.getParameter("queue");
			Advertise advertise = new Advertise();
			advertise.setAdvertiseId(Long.parseLong(id));
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
}

