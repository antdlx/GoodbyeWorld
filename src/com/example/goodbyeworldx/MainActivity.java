package com.example.goodbyeworldx;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author antdlx
 * @version version_x   
 *					x:初步搭建一个架构、listView使用了更加具有灵活性的baseAdapter、修正了线程使用的相关bug、一定程度上使用了分包与面向对象的思想
 * @data 2014/10/3-4
 *
 */
/*
 * 主体思路：由一个引导activity进入到enterActivity，这个activity是第一个正式的界面，供用户输入邮箱或手机，然后调用正则表达式进行
 * 判断，判断后调用线程*2利用HTTPclient进行验证，随后返回结果。返回后的结果被抽象化为数组的数据传输给MyListActivity，然后在这个
 * activity中设置相关的listView数据。
 * 重难点：正则表达式、HTTPclient的简单使用、多线程
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask()
		{
			@Override
			public void run(){
				Intent intent =new Intent(MainActivity.this,EnterActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		};
		System.out.println("*************************");
		timer.schedule(task,1000*3);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
