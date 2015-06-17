package com.thirdpart.tasktrackerpms.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jameschen.framework.base.BaseEditActivity;
import com.thirdpart.model.ConstValues.Item;
import com.thirdpart.model.TaskManager;
import com.thirdpart.model.WidgetItemInfo;
import com.thirdpart.tasktrackerpms.R;
import com.thirdpart.tasktrackerpms.ui.HomeFragment.TaskItem;

public class TaskDetailActivity extends BaseEditActivity {

	private Fragment mFragment;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

	}

	TaskItem taskItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		taskItem = (TaskItem) getIntent().getSerializableExtra(Item.TASK);
		setTitle("" + taskItem.name + "-"
				+ (TaskManager.getTaskType(taskItem.type)));
		initInfo();
	}

	//

	private void initInfo() {

		final List<WidgetItemInfo> itemInfos = new ArrayList<WidgetItemInfo>();
		// R.id. in array String
		itemInfos.add(new WidgetItemInfo("0", null, null, 0, false));

		createItemListToUI(itemInfos, R.id.edit_container,
				new CreateItemViewListener() {

					@Override
					public View oncreateItem(int index, View convertView,
							ViewGroup viewgroup) {
						// TODO Auto-generated method stub
						// if exsit just update , otherwise create it.

						final WidgetItemInfo widgetItemInfo = itemInfos
								.get(index);
						if (convertView == null) {
							// create
							LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

							convertView = inflater.inflate(
									R.layout.fragment_main, viewgroup, false);

						} else {

						}

						// bind tag
						convertView.setTag(widgetItemInfo);
						return convertView;
					}
				}, false);

		// make container

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		mFragment = fm.findFragmentByTag(TaskStatusFragment.class.getName());
		if (mFragment == null) {
			Bundle bundle = new Bundle();
			bundle.putBoolean("scan", true);
			bundle.putSerializable("task", taskItem);
			mFragment = TaskStatusFragment.instantiate(this,
					TaskStatusFragment.class.getName(), bundle);
			ft.add(R.id.fragment_content, mFragment,
					TaskStatusFragment.class.getName());
		}

		ft.commit();

	}

	@Override
	protected void initView() {
		setContentView(R.layout.list_edit_ui);// TODO Auto-generated method stub
		findViewById(R.id.commit_btn_layout).setVisibility(View.GONE);
		super.initView();

	}

}
