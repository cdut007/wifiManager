package com.thirdpart.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.jameschen.comm.utils.Log;
import com.thirdpart.wifimanager.BuildConfig;


public class WebResponseContent {
	
	private String   status;
	private String      msg;
	private String     code;
	private String wifidata;
	
	private  String url;//for test
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getWifidata() {
		return wifidata;
	}
	public void setWifidata(String wifidata) {
		this.wifidata = wifidata;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public static WebResponseContent parseJson(String content) throws JSONException{
		JSONObject jsonObject = new JSONObject(content);
		WebResponseContent webResponseContent = new WebResponseContent();
		webResponseContent.status = jsonObject.optString("status", null);
		webResponseContent.code = jsonObject.optString("code", "1");
		webResponseContent.msg = jsonObject.optString("msg",null);
		webResponseContent.url = jsonObject.optString("url", null);
		return webResponseContent;
	}
	
}
