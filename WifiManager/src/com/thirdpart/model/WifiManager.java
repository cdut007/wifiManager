package com.thirdpart.model;


import java.io.File;
import java.util.List;

import android.graphics.Color;


public class WifiManager extends ManagerService {
	
	public WifiManager(){
		super();
	}
	private WifiManager(OnUploadReqHttpCallbackListener reqHttpCallbackListener) {
		super(reqHttpCallbackListener);
		// TODO Auto-generated constructor stub
	}



	public static String ACTION_WIFI_UPDATE = "com.jameschen.wifi.UPDATE";
	public static String ACTION_WIFI_DELETE = "com.jameschen.wifi.DELETE";
	public static String ACTION_WIFI_UPLOAD = "com.jameschen.wifi.UPLOAD";
	public static String ACTION_WIFI_DOWNLOAD = "com.jameschen.wifi.DOWNLOAD";
	
 

	
	
	/**
	 * @param userId
	 * @param qustionId
	 * @param iswork
	 */
	public void confirmIssue(String qustionId, String iswork) {
		WiFiManagerAPI.getInstance(context).confirmIssue(qustionId,
				iswork, new ManagerNetworkHandler<IssueResult>(context,ACTION_ISSUE_CONFIRM){});

	}

	/**
	 * @param issueResult
	 * @param mFiles 
	 * @param iswork
	 */
	public void createIssue(IssueResult issueResult, List<File> mFiles ) {
		WiFiManagerAPI.getInstance(context).createIssue(issueResult, mFiles,new ManagerNetworkHandler<IssueResult>(context,ACTION_ISSUE_CREATE){});

	}
	
	

	
	/**
	 * @param userId
	 */
	public void IssueDetail(String issueId) {
		WiFiManagerAPI.getInstance(context).IssueDetail(issueId,  new ManagerNetworkHandler<IssueResult>(context,ACTION_ISSUE_DETAIL){});
	}
	

	
	/**
	 * @param userid
	 * @param problemid
	 * @param solvemethod 
	 * @param autoid
	 * @param solvedman
	 * @param isSolve
	 * @param solverid
	 */
	public void handleIssue(String problemid, String solvemethod, String autoid, String solvedman, String isSolve, String solverid) {
		WiFiManagerAPI.getInstance(context).handleIssue(problemid,solvemethod, autoid, solvedman, isSolve, solverid,new ManagerNetworkHandler<IssueResult>(context,ACTION_ISSUE_HANDLE){});
	}
	
	public static int getIssueStatusColor(String isOk) {
		return "0".equals(isOk)?Color.RED:0xFF00a529;
	}
	public static String getIssueStatus(String isOk) {
		return "0".equals(isOk)?"未解决":"解决";
	}
}
