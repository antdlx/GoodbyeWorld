package com.example.ListViewSuppot;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.goodbyeworldx.R;


public class MyListActivity extends ListActivity {

public ListAdapter adapter=null;
public int [] indexOfAddCells = new int [10];
//监听是否注册过的参数，activity每次载入会更新响应数据，不必担心返回后index！=0
public int index=0;

/*
 * 根据EnterActivity传入的数据动态创建listview。
 * 在listView的设计中使用arrayList（动态数组）
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//获得是否注册的数据数组   1注册  0没有注册
		indexOfAddCells = getIntent().getIntArrayExtra("weatherAddCells");
		
		//测试用
		Log.i("weatherAddcells", indexOfAddCells[0]+"");
		Log.i("weatherAddcells", indexOfAddCells[1]+"");
		Log.i("weatherAddcells", indexOfAddCells[2]+"");
		
		adapter = new ListAdapter(this);
		if (indexOfAddCells[0]==1) {
			adapter.setData("CSDN", "this is csdn", R.drawable.logo_csdn, null);
			index++;
		}
		if (indexOfAddCells[1]==1) {
			adapter.setData("Renren", "this is renrne", R.drawable.logo_renren, null);
			index++;
		}
		if (indexOfAddCells[2]==1) {
			adapter.setData("EOE", "this is eoe", R.drawable.eoe, null);
			index++;
		}
		//若什么都没注册，为了防止listView的构造出错，添加一个null提示什么都没有注册过
		if (index==0) {
			adapter.setData("NULL", "null", R.drawable.ic_launcher, null);
		}
		setListAdapter(adapter);
	}
	
}
