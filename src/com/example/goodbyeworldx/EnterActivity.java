package com.example.goodbyeworldx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ListViewSuppot.MyListActivity;
import com.example.webs.WebCSDN;
import com.example.webs.WebEoe;
import com.example.webs.WebRenren;

/*
 * 多线程的实现主要利用了实现Runnable接口的自定义类，然后在主线程中用Handler接受信息进行进一步处理
 * 线程一的arg1参数为判定该线程是否结束的参数，1为结束。线程二的为arg2。
 * 线程一二都会返回obj参数的数组，weatherAddCellsx是缓冲数组，用于暂时保存数据并最终保存到weatherAddCells中
 * 本程序采用一个Handler对应两个线程，可以用各自的what参数为区分保存数据，但是需要注意的是由于是两个线程，所以
 * 消息队列也有两个，故Handler会刷新两次，且第二次（由what判定）的刷新中，第一次的数据会回复初始值，此处想的
 * 办法是用if语句分次保存到全局变量中，再利用一个if判断是否最后一个线程结束，然后用intent传递数据到listActivity中
 *
 */
public class EnterActivity extends Activity {
	public EditText editText = null;
	//edittext的输入数据
	public String InMessage = null;
	//保存该用户是否有过注册，是1，否0
	public int[] weatherAddCells = new int[10];
	//缓冲数组
	public int[] weatherAddCellsx = new int[10];
	//0手机  1邮箱  2无效
	public int type = 2;
	//线程用Handler
	public Handler handler = null;
	//判定是否结束线程的参数
	public int weatherEnd1 = 0;
	public int weatherEnd2 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_activity);
		editText = (EditText) findViewById(R.id.editText);
	}

	public void post(View v) {
		InMessage = editText.getText().toString();
		//使用正则表达式检测输入
		ValidityCheck vc = new ValidityCheck();
		Log.i("0", "++++++++++++++++++");
		if (!(vc.isEmail(InMessage) || vc.isMobileNO(InMessage))) {
			Toast.makeText(EnterActivity.this, "请输入正确的邮箱或手机号码",
					Toast.LENGTH_LONG).show();
			type = 2;
		} else {
			if (vc.isEmail(InMessage)) {
				type = 1;
			}
			if (vc.isMobileNO(InMessage)) {
				type = 0;
			}
			
			//启用线程
			Thread1 thread1 = new Thread1();
			Thread t1 = new Thread(thread1);
			//设置优先级，默认为5，范围1-10。设置一个高优先级以确保线程二在一之后结束，方便判定所有线程是否结束 
//			t1.setPriority(6);
			t1.start();
			Thread2 thread2 = new Thread2();
			Thread t2 = new Thread(thread2);
			t2.start();

			//实现Handler
			handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					Log.i("1", "++++++++++++++++++");
					//使用what和if保存来自线程一的数据
					if (msg.what == 1) {
						Log.i("8", "888888888888888888");
						weatherEnd1 = msg.arg1;
						weatherAddCellsx = (int[]) msg.obj;
						weatherAddCells[0] = weatherAddCellsx[0];
						weatherAddCells[2]=weatherAddCellsx[1];
					}
					//使用what和if保存来自线程二的数据
					if (msg.what == 2) {
						Log.i("9", "9999999999999");
						weatherEnd2 = msg.arg2;
						weatherAddCellsx = (int[]) msg.obj;
						weatherAddCells[1] = weatherAddCellsx[0];
					}
					//线程二在线程一之后结束，故借其判断结束的参数使用if执行跳转与传递的操作
					if (weatherEnd1 == 1) {
						Log.i("2", "++++++++++++++++++");
						Intent i = new Intent(EnterActivity.this,
								MyListActivity.class);
						i.putExtra("weatherAddCells", weatherAddCells);
						startActivity(i);
					}
				};
			};
		}
	}

	public class Thread1 implements Runnable {

		public void run() {
			WebCSDN csdn = new WebCSDN(InMessage);
			WebEoe eoe = new WebEoe(InMessage);
			//此try为测试用
			try {
				Log.i("csdn",csdn.Post()+"");
				Log.i("eoe",eoe.Post()+"");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//设置message的参数
			try {
				Message msg = Message.obtain();
				//次数组大小应为此线程中测试的网站的数量，此处测试方便开2个单位，测试网站按照  约定  的顺序储存
				int addcell[] = new int[2];
				msg.what = 1;
				msg.arg1 = 1;
				msg.obj = addcell;
				//如果注册过csdn则更新响应的数组为1.此处默认数组第一位是csdn的参数
				if (csdn.Post() != 5) {
					addcell[0] = 1;
				} 
				if (eoe.Post()!=5) {
					addcell[1]=1;
				}
				//提交message，由上方的Handler处理
				handler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Thread2 implements Runnable {

		@Override
		public void run() {
			WebRenren renren = new WebRenren(InMessage, type);
			try {
				Log.i("RenRen",renren.Post()+"");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				Message msg = Message.obtain();
				int addcell[] = new int[2];
				msg.what = 2;
				msg.obj = addcell;
				msg.arg2 = 1;
				if (renren.Post() != 5) {
					addcell[0] = 1;
				}
				handler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
