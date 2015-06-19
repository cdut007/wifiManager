package com.thirdpart.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jameschen.comm.utils.MyHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thirdpart.model.Config.ReqHttpMethodPath;
import com.thirdpart.model.entity.WifiData;
import com.thirdpart.wifimanager.ui.HomeFragment.TaskItem;

public class WiFiManagerAPI {

	private static WiFiManagerAPI managerAPI;

	private Context context;

	private LogInController mLogInController;

	public Map<String, String> getPublicParamRequstMap() {
		Map<String, String> headers = new HashMap<String, String>();

		return headers;
	}

	private JSONObject getCommonPageParams(boolean login, String pageSize, String pageNum) {
		// TODO Auto-generated method stub
		JSONObject requestParams = getPublicParams(login);
		try {
			requestParams.put("pagesize", pageSize);
			requestParams.put("pagenum", pageNum);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return requestParams;
	}
	
	private JSONObject getCommonPageParams(String pageSize, String pageNum) {
		// TODO Auto-generated method stub
		
		return getCommonPageParams(true, pageSize, pageNum);
	}

	private JSONObject getPublicParams(boolean loginId) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("appSessionKey", getAppKey());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}

	private JSONObject getPublicParams() {
		// TODO Auto-generated method stub

		return getPublicParams(true);
	}

	private WiFiManagerAPI(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public static WiFiManagerAPI getInstance(Context context) {
		// TODO Auto-generated method stub
		synchronized (WiFiManagerAPI.class) {
			if (managerAPI == null) {
				managerAPI = new WiFiManagerAPI(context);
			}
		}
		return managerAPI;
	}

	
	private String  getAppKey() {
		// TODO Auto-generated method stub
		return getLogInController().getInfo().getAppSessionKey();
	}
	
	private LogInController getLogInController() {
		// TODO Auto-generated method stub
    	  mLogInController = LogInController.getInstance(context);
      
      return mLogInController;
	}
	/**
	 * @param loginId
	 * @param password
	 * @param responseHandler
	 *            userInfo
	 */
	public void login(String phone, String password,
			AsyncHttpResponseHandler responseHandler) {
		JSONObject params = getPublicParams(false);
		try {
			params.put("phoneNumer", phone);
			params.put("password", password);
			//params.put("uuid", LogInController.getUUID(context));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_LOGIN_URL, params,
				responseHandler);

	}
	public void logout(String appSessionKey, 
			AsyncHttpResponseHandler responseHandler) {
		JSONObject params = getPublicParams(false);
		try {
			params.put("appSessionKey", appSessionKey);
			//params.put("uuid", LogInController.getUUID(context));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_LOGOUT_URL, params,
				responseHandler);

	}
	/**
	 *  {  “phoneNumber”:”123456”,

      “password”:”hashedvalue”,

       “otpNumber”:”1234”,

       “email”:”t@t.com”

  }

	 * @param loginId
	 * @param password
	 * @param responseHandler
	 */
	public void register(String phone, String password,String otp,String email,
			AsyncHttpResponseHandler responseHandler) {
		
		JSONObject params = getPublicParams(false);
		
		try {
			
			params.put("phoneNumber", phone);
			params.put("password", password);
			params.put("otpNumber", otp);
			params.put("email", email);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_LOGIN_URL, params,
				responseHandler);

	}
	

	Gson gson = new Gson();	
	/**
	 * 
	 *  appSessionKey”, “dadafdaffd”,

   “wifidata”, {

                     “longtitude”: “123.44”,

                     “latitude”: “234.55”

                      “ssid”: “publicwifi”,

                      “psk”: “password”,

                      “tag”: “kfc|home”,

                       “share”: “yes|no”

                   }

}

	 * @param wifidata
	 * @param responseHandler
	 */
	public void uploadWifiInfo(WifiData wifidata,
			AsyncHttpResponseHandler responseHandler) {
		
		JSONObject params = getPublicParams(false);
		
		try {
			params.put("wifidata", gson.toJson(wifidata));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_UPLOAD_WIFI_INFO_URL, params,
				responseHandler);

	}
	public void updateWifiInfo(WifiData wifidata,
			AsyncHttpResponseHandler responseHandler) {
		
		JSONObject params = getPublicParams(false);
		
		try {
			params.put("wifidata", gson.toJson(wifidata));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_UPDATE_WIFI_INFO_URL, params,
				responseHandler);

	}
	public void deleteWifiInfo(WifiData wifidata,
			AsyncHttpResponseHandler responseHandler) {
		
		JSONObject params = getPublicParams(false);
		
		try {
			params.put("wifidata", gson.toJson(wifidata));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_DELETE_WIFI_INFO_URL, params,
				responseHandler);

	}
	/**“wifidata” : “ {[

                     “longtitude”: “123.44”,

                     “latitude”: “234.55”

                      “ssid”: “publicwifi”,

                      “psk”: “password”,

                      “tag”: “kfc|home”,

                       “share”: “yes|no”

                      ],

                      [

                     “longtitude”: “123.44”,

                     “latitude”: “234.55”

                      “ssid”: “publicwifi”,

                      “psk”: “password”,

                      “tag”: “kfc|home”,

                       “share”: “yes|no”

                      

                   }

	 * @param responseHandler
	 */
	public void downloadWifiInfo(
			AsyncHttpResponseHandler responseHandler) {
		
		JSONObject params = getPublicParams(false);
	
		
		MyHttpClient.postJson(context,ReqHttpMethodPath.REQUST_DOWNLOAD_WIFI_INFO_URL, params,
				responseHandler);

	}
	
	
	public static String getdateTimeformat(long times) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(times);
		return sdf.format(date);
	}
	
	public static String getdateformat(long times) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(times);
		return sdf.format(date);
	}

	
	
	
}
