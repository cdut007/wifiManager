package com.thirdpart.model;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.Header;

import android.app.Fragment.InstantiationException;
import android.content.Context;
import android.util.Log;

import com.jameschen.framework.base.UINetworkHandler;

public abstract class ManagerService {



public static interface OnUploadReqHttpCallbackListener extends OnReqHttpCallbackListener{

	public void onProgress(String name,int statusCode,
			int totalSize, String response);
}

public static interface OnReqHttpCallbackListener{
	
	public void start(String name);
	
	public void failed(String name,int statusCode,
			Header[] headers, String response);
	
	public void finish(String name);
	
	
	public void succ(String name,int statusCode,
			Header[] headers, Object response);
}


private static ConcurrentHashMap<String, Class<?>> managerServiceMap = new ConcurrentHashMap<String, Class<?>>();	

public static final String CODE = "code",REASON="reanson",SUCC="succ",DATA="data";

protected Context context;
protected  OnReqHttpCallbackListener reqHttpCallbackListener;
ManagerService(OnReqHttpCallbackListener reqHttpCallbackListener){
	this.reqHttpCallbackListener = reqHttpCallbackListener;
}

public ManagerService(){
}

public static  ManagerService getNewManagerService(Context context,Class<?> mClass,OnReqHttpCallbackListener reqHttpCallbackListener){
    String serviceName = mClass.getName();
	try {
        Class<?> clazz = managerServiceMap.get(serviceName);
        if (clazz == null) {
            // Class not found in the cache, see if it's real, and try to add it
            clazz = context.getClassLoader().loadClass(serviceName);
            if (!ManagerService.class.isAssignableFrom(clazz)) {
                throw new InstantiationException("Trying to instantiate a class " + serviceName
                        + " that is not a ManagerService", new ClassCastException());
            }
            managerServiceMap.put(serviceName, clazz);
        }
        ManagerService managerService = (ManagerService)clazz.newInstance();
        managerService.context = context;
        managerService.reqHttpCallbackListener = reqHttpCallbackListener;
        return managerService;
    } catch (ClassNotFoundException e) {
        throw new InstantiationException("Unable to instantiate managerService " + serviceName
                + ": make sure class name exists, is public, and has an"
                + " empty constructor that is public", e);
    } catch (java.lang.InstantiationException e) {
        throw new InstantiationException("Unable to instantiate managerService " + serviceName
                + ": make sure class name exists, is public, and has an"
                + " empty constructor that is public", e);
    } catch (IllegalAccessException e) {
        throw new InstantiationException("Unable to instantiate managerService " + serviceName
                + ": make sure class name exists, is public, and has an"
                + " empty constructor that is public", e);
    }
}



protected   ManagerNetworkHandler getManagerNetWorkHandler(String action){
	return null;
};

 protected  class ManagerNetworkHandler<T> extends UINetworkHandler<T>{
	 private String action;
	public ManagerNetworkHandler(Context context,String action) {
		super(context);
		// TODO Auto-generated constructor stub
		this.action = action;
	}

	
	@Override
	public void onProgress(int bytesWritten, int totalSize) {
		// TODO Auto-generated method stub
		super.onProgress(bytesWritten, totalSize);
		//send
		if (reqHttpCallbackListener instanceof OnUploadReqHttpCallbackListener) {

			int percent = bytesWritten*100/totalSize;
			if (percent>=100) {//ok
				Log.i("upload", "count==="+percent);
				percent=99;
			}
			String content = "已上传"+percent+"%";
			((OnUploadReqHttpCallbackListener)reqHttpCallbackListener).onProgress(action,bytesWritten, totalSize,content);
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		notifyStart(action);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		notifyFinish(action);
	}

	@Override
	public void callbackFailure(int statusCode,
			Header[] headers, String response) {
		// TODO Auto-generated method stub
		notifyFailedResult(action, statusCode,headers,
				response);
	}

	@Override
	public void callbackSuccess(int statusCode,
			Header[] headers, T response) {
		
		notifySuccResult(action, statusCode,headers,response);
	}


	

}


 private void  notifyStart(String action){
	 reqHttpCallbackListener.start(action);

}

 private void  notifyFinish(String action){
	  
	 reqHttpCallbackListener.finish(action);
}

 private void  notifyFailedResult(String action,int statusCode,Header[] headers, String response){
	 reqHttpCallbackListener.failed(action, statusCode, headers, response);

}

 private void  notifySuccResult(String action,int statusCode, Header[] headers, Object response){
	 reqHttpCallbackListener.succ(action, statusCode, headers, response);
}

}
