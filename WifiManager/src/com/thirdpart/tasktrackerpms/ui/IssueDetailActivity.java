package com.thirdpart.tasktrackerpms.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jameschen.framework.base.BaseDetailActivity;
import com.jameschen.framework.base.BaseEditActivity;
import com.jameschen.framework.base.CommonCallBack.OnRetryLisnter;
import com.thirdpart.model.ConstValues;
import com.thirdpart.model.ConstValues.Item;
import com.thirdpart.model.IssueManager;
import com.thirdpart.model.ManagerService;
import com.thirdpart.model.PMSManagerAPI;
import com.thirdpart.model.ManagerService.OnReqHttpCallbackListener;
import com.thirdpart.model.PlanManager;
import com.thirdpart.model.WidgetItemInfo;
import com.thirdpart.model.entity.IssueMenu;
import com.thirdpart.model.entity.IssueResult;
import com.thirdpart.model.entity.RollingPlan;
import com.thirdpart.tasktrackerpms.R;
import com.thirdpart.widget.DisplayItemView;
import com.thirdpart.widget.UserInputItemView;

public class IssueDetailActivity extends BaseDetailActivity {
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	//{"id":"20","worstepid":"121","stepno":null,"stepname":"坡口加工","describe":"111111111",
	//"questionname":"11","isOk":"0","level":"2","solvemethod":"","confirm":null,
	//"methodmanid":"0","solvedate":"","concerman":"91",
	//"currentsolver":"张旭","createdBy":"94","file":[]}
	
IssueManager sIssueManager;
 IssueResult issueResult;
 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		issueResult = (IssueResult) getIntent().getSerializableExtra(Item.ISSUE);	
      setTitle("问题详情");
      initInfo();
      sIssueManager = (IssueManager) IssueManager.getNewManagerService(this, IssueManager.class, this);
      bindViews();
      updateInfo();
      loadDetail();
 }
 
	UserInputItemView issueDescView,issueMethod;
	DisplayItemView issueStepTopic,solverMan,issueStatus;
	
	private void bindViews() {
	// TODO Auto-generated method stub
		issueDescView = (UserInputItemView) findViewById(R.id.issue_desc);
		issueMethod = (UserInputItemView) findViewById(R.id.issue_method);
		issueStepTopic = (DisplayItemView) findViewById(R.id.issue_topic);
		solverMan = (DisplayItemView) findViewById(R.id.issue_solver);
		issueStatus = (DisplayItemView) findViewById(R.id.issue_status);
		if (issueResult.getFile()!=null && issueResult.getFile().size()>0) {
			findViewById(R.id.issue_files).setVisibility(View.VISIBLE);	
			findViewById(R.id.issue_files).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ArrayList<String> mPhotos = new ArrayList<String>();
					mPhotos = PMSManagerAPI.getPhotosUrls(issueResult.getFile());
					Intent intent = new Intent(IssueDetailActivity.this,ImageDetailActivity.class);
					intent.putStringArrayListExtra("photos", mPhotos);
					startActivity(intent);
				}
			});
		}
	}

private void updateInfo() {
	// TODO Auto-generated method stub
	issueDescView.setContent(issueResult.getDescribe(), false);
	issueMethod.setContent(issueResult.getSolvemethod(), false);
	issueStepTopic.setContent(issueResult.getStepname());
	solverMan.setContent(issueResult.getCurrentsolver());
	issueStatus.setContent(IssueManager.getIssueStatus(issueResult.getIsOk()));

}

	private void loadDetail() {
		showLoadingView(true);
		sIssueManager.IssueDetail(issueResult.getId());
	
}
  @Override
public void failed(String name, int statusCode, Header[] headers,
		String response) {
	// TODO Auto-generated method stub
	super.failed(name, statusCode, headers, response);
	showLoadingView(false);
	/*showRetryView(new OnRetryLisnter() {
		
		@Override
		public void doRetry() {
			// TODO Auto-generated method stub
			
		}
	});*/
}
  
  @Override
public void succ(String name, int statusCode, Header[] headers,
		Object response) {
	// TODO Auto-generated method stub
	super.succ(name, statusCode, headers, response);
	setLoadSucc();
	 issueResult = (IssueResult) response;
	 updateInfo();
	
}


	private void initInfo() {

		final  List<WidgetItemInfo> itemInfos = new ArrayList<WidgetItemInfo>();
		 //R.id.  in array String
		 itemInfos.add(new WidgetItemInfo("0", null, null, 0, false));		
		
		  createItemListToUI(itemInfos, R.id.detail_container, new CreateItemViewListener() {

			@Override
			public View oncreateItem(int index, View convertView,
					ViewGroup viewgroup) {
				// TODO Auto-generated method stub
				//if exsit just update , otherwise create it.
				
				final WidgetItemInfo widgetItemInfo = itemInfos.get(index);
				if (convertView ==null) {
					//create
					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						
					convertView = inflater.inflate(R.layout.issue_detail_ui, viewgroup, false);	
					
					
				}else {
					
				}
				
				//bind tag
				convertView.setTag(widgetItemInfo);
				return convertView;
			}
		}, false);
		  
		  //make container
		  
		  
	}
	
	@Override
	protected void initView() {
		setContentView(R.layout.detail_ui);// TODO Auto-generated method stub
		super.initView();
		
	}
	


	

}
