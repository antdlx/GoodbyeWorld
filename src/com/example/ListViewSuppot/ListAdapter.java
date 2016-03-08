package com.example.ListViewSuppot;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.goodbyeworldx.R;

public class ListAdapter extends BaseAdapter {

//注意，在动态更新的要求下不再适合使用数组储存对象
//	public ListData [] data = new ListData[]{
//			new ListData("CSDN", "this is csdn", R.drawable.logo_csdn, null),
//			new ListData("Renren", "this is renrne", R.drawable.logo_renren, null)
//	};
	
	//设计上使用动态数组储存数据
	public ArrayList<ListData> data = new ArrayList<ListData>();
	
	private Context context=null;
	//面向对象的优化
	public ListAdapter(Context context){
		this.context = context;
	}
	//面向对象的优化，注意：构造方法不能有return，故自定义此方法
	public Context getContext(){
		return context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public ListData getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	//自定义方法，用于动态设置数据使用
	public void setData(String title,String description,int iconId,Class classes){
		ListData cell = new ListData(title, description, iconId, classes);
		data.add(cell);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//声明要返回的view
		LinearLayout ll=null;
		
		//优化的回收机构
		if (convertView!=null) {
			ll=(LinearLayout) convertView;
		}else{
			ll=(LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.cell,null);
		}
		
		//声明listView中的组件并更新其内容
		TextView title = (TextView) ll.findViewById(R.id.tv_title);
		TextView des = (TextView) ll.findViewById(R.id.tv_description);
		ImageView icon = (ImageView) ll.findViewById(R.id.iv_icon);
		//方便更新用，获得当前数据对象
		ListData currentData = getItem(position);
		
		title.setText(currentData.title);
		des.setText(currentData.description);
		icon.setImageResource(currentData.iconId);
		
		return ll;
	}

}
