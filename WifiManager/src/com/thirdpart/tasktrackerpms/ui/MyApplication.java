package com.thirdpart.tasktrackerpms.ui;


import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.apache.log4j.Level;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

import com.jameschen.comm.utils.Log;
import com.jameschen.comm.utils.OttoBusHelper;
import com.jameschen.comm.utils.StorageUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.otto.Bus;
import com.thirdpart.model.TestReq;
import com.thirdpart.tasktrackerpms.R;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by yongjun on 05/16/2015.
 */
@ReportsCrashes(mailTo = "316458704@qq.com", customReportContent = {
		ReportField.APP_VERSION_NAME, ReportField.APP_VERSION_CODE,
		ReportField.APPLICATION_LOG, ReportField.ANDROID_VERSION,
		ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA,
		ReportField.STACK_TRACE, ReportField.LOGCAT }, mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text,formKey = "", logcatArguments = {
		"-t", "3000", "-v", "long", "ActivityManager:I", "MyApp:D", "*:S" })
public class MyApplication extends Application {

	private static final String TAG = "Mypplication";
	private Bus mBus = OttoBusHelper.getBus(this);

	@Override
	public void onCreate() {
		super.onCreate();
		ACRA.init(this);
		initializeImageLoader(this);
		initLogConfig();
		mBus.register(this); // listen for "global" events
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        TestReq.init(this);
	}

	
	private void initializeImageLoader(MyApplication myApplication) {
		// TODO Auto-generated method stub
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(myApplication).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
																					// for
																					// release
																					// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}


	public void fulshLog() {
		// TODO Auto-generated method stub
		if (logConfigurator!=null) {
			logConfigurator.setImmediateFlush(true);
			logConfigurator.setImmediateFlush(false);
			logConfigurator.configure();
		}
	}
	
	LogConfigurator logConfigurator ;
	private void initLogConfig() {
		// TODO Auto-generated method stub
		// Create log Configurator
		 logConfigurator = new LogConfigurator();
		// Setting append log coudn't cover by a new log.
		logConfigurator.setUseFileAppender(true);
		// Define a file path for output log.
		String filename = StorageUtils.getLogFile();
		Log.i("info", filename);
		// Setting log output
		logConfigurator.setFileName(filename);
		// Setting log's level
		logConfigurator.setRootLevel(Level.DEBUG);
		logConfigurator.setLevel("org.apache", Level.ERROR);
		logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
		logConfigurator.setMaxFileSize(1024 * 1024 * 5);
		// Set up to use the cache first and then output to a file for a period
		// of time
		logConfigurator.setImmediateFlush(false);
		logConfigurator.setUseLogCatAppender(true);
		// logConfigurator.setResetConfiguration(true);
		logConfigurator.configure();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		mBus.unregister(this);
	}

}