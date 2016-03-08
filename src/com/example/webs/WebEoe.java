package com.example.webs;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.goodbyeworldx.ValidityCheck;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class WebEoe{
	HttpClient httpclient = null;
	String input=null;
	int returnMsg=0;
	String resultMsg=null;
public WebEoe(String input){
	httpclient = new DefaultHttpClient();
	this.input = input;
}
	
	public int Post(){
				Log.i("222222", "222222222222");
				String name = input;
				ValidityCheck vc = new ValidityCheck();
				HttpPost post = new HttpPost("http://www.eoeandroid.com/forum.php?mod=ajax&inajax=yes&infloat=register&handlekey=register&ajaxmenu=1&action=checkemail&email="+name);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("name",name));
				try {
					Log.i("7", "222222222222");
					post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
					HttpResponse respose = httpclient.execute(post);
					Log.i("77", "222222222222");
					resultMsg =EntityUtils.toString(respose.getEntity()).trim(); 
					
					System.out.println(vc.weatherFit(resultMsg)+"**************");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (vc.weatherFit(resultMsg)) {
					returnMsg=4;
				}else {
					returnMsg=5;
				}
				return returnMsg;
			}
}
