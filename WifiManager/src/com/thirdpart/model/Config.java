package com.thirdpart.model;

public class Config {

	
	public static final class  ReqHttpMethodPath{
		/** define the http server root url & port */
		public static final String HTTP_BASE_URL = "http://helloxlb.xicp.net:17905/easycms-website";

		/** define the login url 
		 *  
		 *  POST
		 * <br>param:Login_ID,Password
		 * */
		public static final String REQUST_LOGIN_URL = HTTP_BASE_URL+"/wifi/account/login/api/v1";
	
		/**  define the register url 
		 *  POST
		 
		 * */
		public static final String REQUST_REGISTER_URL = HTTP_BASE_URL+"/wifi/account/register/api/v1";
		
		
		public static final String REQUST_LOGOUT_URL = HTTP_BASE_URL+"/wifi/account/logout/api/v1";
		
		
		public static final String REQUST_UPLOAD_WIFI_INFO_URL = HTTP_BASE_URL+"/wifi/information/upload/api/v1";
		
		public static final String REQUST_UPDATE_WIFI_INFO_URL = HTTP_BASE_URL+"/wifi/information/update/api/v1";
		
		public static final String REQUST_DOWNLOAD_WIFI_INFO_URL = HTTP_BASE_URL+"/wifi/information/download/api/v1";
		
		public static final String REQUST_DELETE_WIFI_INFO_URL = HTTP_BASE_URL+"/wifi/information/delete/api/v1";
		
		
	}	

}
