package com.thirdpart.widget;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.jameschen.comm.utils.UtilsUI;
import com.thirdpart.model.WidgetItemInfo;
import com.thirdpart.wifimanager.R;

public class ChooseItemView extends FrameLayout {
	public ChooseItemView(Context context){
		this(context ,null);
	}
	public ChooseItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 View view = LayoutInflater.from(context).inflate(
				R.layout.common_choose_item, this, true);
		InitView(view, attrs);
	}
	
	
	public static interface onDismissListener<T> {
		void onDismiss(T item);
	}
	
	public <T> void showMenuItem(final List<T> items,final List<String> titles, final onDismissListener<T> onDismissListener) {
		// TODO Auto-generated method stub
		final PopupWindowUtil mPopupWindow = new PopupWindowUtil();
		mPopupWindow.showActionWindow(contentView, getContext(), titles);
		mPopupWindow.setItemOnClickListener(new PopupWindowUtil.OnItemClickListener() {

			@Override
			public void onItemClick(int index) {
					T item = items.get(index);
					contentView.setText(titles.get(index));
					onDismissListener.onDismiss(item);
			}
		});
	}
	
	@Override
	public void setBackgroundResource(int resid) {
		// TODO Auto-generated method stub
		super.setBackgroundResource(resid);
		getChildAt(0).setBackgroundResource(resid);
	
	}
	@Override
	public void setOnClickListener(OnClickListener l) {
		// TODO Auto-generated method stub
		setChooseItemClickListener(l);
	}
	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
	}
	
	private TextView nameView;
	private Button contentView;

	private void InitView(View view, AttributeSet attrs) {
		// TODO Auto-generated method stub
		String name = null;
		String content = null;
		boolean tinySepc = false,onlyBtn = false;
		if (attrs!=null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.DisplayViewStyle);
			 name = a.getString(R.styleable.DisplayViewStyle_customName);
			 content = a
						.getString(R.styleable.DisplayViewStyle_customContent);
			 tinySepc = a
						.getBoolean(R.styleable.DisplayViewStyle_tinySpec,false);
			 onlyBtn = a
						.getBoolean(R.styleable.DisplayViewStyle_onlybtn,false);

			a.recycle();
		}
		
		nameView = (TextView) view.findViewById(R.id.common_choose_item_title);
		contentView = (Button) view
				.findViewById(R.id.common_choose_item_content);
		if (name != null) {
			nameView.setText(name);
		}
		if (content != null) {
			contentView.setText(content);
		}
		
		if (tinySepc) {
			LinearLayout.LayoutParams param = (android.widget.LinearLayout.LayoutParams) contentView.getLayoutParams();
			param.rightMargin = UtilsUI.getPixByDPI(getContext(), 40);
			contentView.setLayoutParams(param);
		}
		
		if (onlyBtn) {
			LinearLayout.LayoutParams param = (android.widget.LinearLayout.LayoutParams) contentView.getLayoutParams();
			param.width = LayoutParams.MATCH_PARENT;
			contentView.setLayoutParams(param);
			nameView.setVisibility(View.GONE);
		}

	}

	public void setContent(String content) {
		// TODO Auto-generated metho
			contentView.setText(content);
	}
	
	
	public String getContent() {
		// TODO Auto-generated method stub
		if (contentView.getText() == null) {
			return null;
		}
		return contentView.getText().toString();
	}
	
	public void setName(String name) {
		// TODO Auto-generated metho
		nameView.setText(name);
	}
	public void setNameAndContent(String name,String content) {
		// TODO Auto-generated method stub
		setName(name);
		setContent(content);
	}
	
	public void setChooseItemClickListener(OnClickListener onClickListener) {
		// TODO Auto-generated method stub
		contentView.setOnClickListener(onClickListener);
	}
	
}
