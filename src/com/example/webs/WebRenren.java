package com.example.webs;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;


public class WebRenren {
	
	private String account;
	private int type;
	public WebRenren(String input,int type){
		account=input;
		this.type=type;//用来记录账户类型：0.手机号；1.邮箱 ；2.无效的
	}
	
	/*
	 * 这是post，会返回一个数字 0.初始值  1.手机号已绑定  2.手机号错误  3.电子邮箱错误 4.邮箱已注册 5.手机/邮箱账号可使用
	 */
	public int Post() throws Exception{
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://reg.renren.com/AjaxRegisterAuth.do");

		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("authType", "email"));
		nvps.add(new BasicNameValuePair("value", account));
		nvps.add(new BasicNameValuePair("t", "1411730750927"));
		nvps.add(new BasicNameValuePair("rndval", "1411730750929"));
		
		
		if(type==0){
			
			nvps.add(new BasicNameValuePair("stage", "3"));
			
		}

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		
		int returnMsg = 0;//Ĭ��ֵ
		if (entity != null) {  
			
	        String content3 = EntityUtils.toString(entity);
	        
	        if(content3.length()<3){
	        	returnMsg=5;
	        }
	        else{
	        	 String checkString=content3.substring(0, 7);
		          
	        	if(checkString=="手机号已经绑定")
	        		returnMsg=1;
	        	else {
	        		if(checkString=="请输入正确的手")
	        		returnMsg=2;
	        		else{
	        			if(checkString=="无效的电子邮箱")
	        				returnMsg=3;
	        			else{
	        				if(checkString=="帐号已存在，请")
	        					returnMsg=4;
	        			}
	        		}
	        	}
	        }   
	    }
		Log.i("6","0666");
		return returnMsg;
	}
}
