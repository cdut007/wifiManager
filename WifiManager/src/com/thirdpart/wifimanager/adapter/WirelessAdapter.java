package com.thirdpart.wifimanager.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jameschen.framework.base.BaseCheckItemAdapter;
import com.jameschen.framework.base.MyBaseAdapter;
import com.thirdpart.model.entity.WifiData;
import com.thirdpart.wifimanager.R;

public class WirelessAdapter extends BaseCheckItemAdapter<WifiData> {
	private Context context;
	
	
	public WirelessAdapter(Context context) {
		super(context,R.layout.delivery_plan_item);
		this.context = context;
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = super.getView(position, convertView, parent);
		
		return convertView;

	}

	@Override
	public void recycle() {
	
		super.recycle();

	}

	@Override
	protected HoldView<WifiData> createHoldView() {
		// TODO Auto-generated method stub
		return new ItemHoldView();
	}

	private final static class ItemHoldView extends CheckItemHoldView<WifiData> {
		
		
		TextView noTextView,drawNoTextView;
		CheckBox isChecked;
		BaseCheckItemAdapter<WifiData> mCheckItemAdapter;
		@Override
		protected void initChildView(View convertView,
				MyBaseAdapter<WifiData> myBaseAdapter) {
			// TODO Auto-generated method stub
			noTextView = (TextView) convertView.findViewById(R.id.task_no);
			drawNoTextView = (TextView) convertView.findViewById(R.id.task_draw_no);
			isChecked = (CheckBox) convertView.findViewById(R.id.task_delivery_check);
			mCheckItemAdapter = (BaseCheckItemAdapter<WifiData>) myBaseAdapter;
		View checkContainer = (View)isChecked.getParent();
		checkContainer.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mCheckItemAdapter.setItemChecked((String)isChecked.getTag(), isChecked);
				}
			});
			if (((WirelessAdapter)myBaseAdapter).scanMode) {
				checkContainer.setVisibility(View.GONE);
			}
			
		}
		
		
		@Override
		protected void setInfo(WifiData plan) {
			// TODO Auto-generated method stub
			noTextView.setText(plan.getWeldno());
			drawNoTextView.setText(plan.getDrawno());
		
		}
		@Override
		public void setCheckedInfo(BaseCheckItemAdapter<WifiData> adapter,int position,WifiData item){
			mCheckItemAdapter.markItemCheckStatus(position, isChecked);
			isChecked.setTag(position+"");
		 }
	}

	boolean scanMode;
	public void setScanMode(boolean scanMode) {
		// TODO Auto-generated method stub
		this.scanMode = scanMode;
	}

	
	

}
