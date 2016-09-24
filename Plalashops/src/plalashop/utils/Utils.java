package plalashop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	
	
	public static final String SESSION_USER_KEY = "userLogin";
	public static final String MENU = "menu";
	public static final String MENU_SELECT = "menuSelect";
	
	private static String dateReProductNo = "0000000";
	private static int runingProductNo = 0;
	
	
	
	
	
	
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
}
