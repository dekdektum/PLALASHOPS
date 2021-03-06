package plalashop.utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Encoder;

import java.util.LinkedHashMap;
import java.util.Map;
public class Utils {
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	
	
	public static final String SESSION_USER_KEY = "userLogin";
	public static final String MENU = "menu";
	public static final String MENU_SELECT = "menuSelect";
	
	private static String dateReProductNo = "0000000";
	private static int runingProductNo = 0;
	private static Map<String,String> memImg;
	
	public static <K, V> Map<K, V> createLRUMap(final int maxEntries) {
	    return new LinkedHashMap<K, V>(maxEntries*10/7, 0.7f, true) {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -3932497274912833803L;

			@Override
	        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
	            return size() > maxEntries;
	        }
	    };
	}
	
	
	
	public static String getMenuByRole(String role){
		StringBuilder str = new StringBuilder();

		str.append("<nav class=\"navbar navbar-default\">							 ");
		str.append("	<div class=\"container-fluid\">                              ");
		str.append("		<div class=\"navbar-header\">                            ");
		str.append("			<a class=\"navbar-brand\" href=\"#\">PLALA SHOP</a>  ");
		str.append("		</div>                                                   ");
		str.append("		<ul class=\"nav navbar-nav\">                            ");
		str.append("			<li class=\"active\"><a href=\"#\">Home</a></li>     ");
		if("superAdmin".equals(role)){
			str.append("			<li><a href=\"#\">Product Type</a></li>              ");
			str.append("			<li><a href=\"#\">Products</a></li>                  ");
		}
		str.append("			<li><a href=\"#\">Oeder Management</a></li>          ");
		str.append("		</ul>                                                    ");
		str.append("	</div>                                                       ");
		str.append("</nav>                                                           ");
		
		return str.toString();
	}
	
	
	
	
	public static String whereInjection(Object val,String key){
		String where = "";
		if(val != null && val.toString().length() > 0){
			where = "and "+key;
			if(val.toString().indexOf("*") >= 0 || val.toString().indexOf("%") >= 0){
				where+=" like"+"'"+val.toString().replaceAll("*", "%")+"'";
			}else{
				where+=" ="+"'"+val.toString()+"'";
			}
		}
		return where;
		
		
	}
	
	
	public static boolean isDouble(String value){
		try {
			Double.parseDouble(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	public static String genProductNo(){
		String dateNow = dateToString(new Date(), "yyMM");
		if(!dateReProductNo.equals(dateNow)){
			runingProductNo = 1;
			dateReProductNo = dateNow;
		}else{
			runingProductNo++;
		}
		return "P"+dateNow+genPattleNumber(runingProductNo+"");
	}
	
	public static String genPattleNumber(String val){
		for (int i = 4; i > 1; i--) {
			val = "0"+val;
		}
		return val;
	}
	
	public static String dateToString(Date date, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date );
	}
	
	public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
	
	
	public static byte[] scale(byte[] fileData, int width, int height) throws IOException {
    	ByteArrayInputStream in = new ByteArrayInputStream(fileData);
    	
    		BufferedImage img = null;
			try {
				img = ImageIO.read(in);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		if(height == 0) {
    			height = (width * img.getHeight())/ img.getWidth(); 
    		}
    		if(width == 0) {
    			width = (height * img.getWidth())/ img.getHeight();
    		}
    		Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    		BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

    		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    		
    		try {
				ImageIO.write(imageBuff, "png", buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				in.close();
			}

    	return buffer.toByteArray();
    }
	
	public static String convertImageToBase64(String imageUrlPath){	
		if(imageUrlPath!= null && imageUrlPath.length() > 0 && "null".equals(imageUrlPath.substring(imageUrlPath.length()-4))){
			return null;
		}
		if(memImg == null){
			memImg = createLRUMap(500);
		}
		String img = memImg.get(imageUrlPath);
		if(img != null){
			return img;
		}
		String encodedBytes = null;
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			java.io.File file = new java.io.File(imageUrlPath);
			double bytes = file.length();
			double kilobytes = bytes / 1024;

			byte[] filebyte = FileUtils.readFileToByteArray(file);
			if (kilobytes > 200.00) {
				byte[] fileImageConvertByte = scale(filebyte,200, 0);
				encodedBytes = encoder.encodeBuffer(fileImageConvertByte);
			} else {
				encodedBytes = encoder.encodeBuffer(filebyte);
			}
			encodedBytes = "data:image/jpeg;base64,"+encodedBytes;
			memImg.put(imageUrlPath, encodedBytes);
			return encodedBytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedBytes;
	}
}
