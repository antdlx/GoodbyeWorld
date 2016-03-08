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

public class WebCSDN {

	private String email;
	private String account;
	int returnMsg;
	/**
	 * @param args
	 * @throws Exception
	 */
	public WebCSDN(String input) {
		email=input;
	}

	public int Post() throws Exception {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(
				"https://passport.csdn.net/account/register?action=validateEmail&email="
						+ email);


		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action", "validateEmail"));
		nvps.add(new BasicNameValuePair("email", email));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {

			String content = EntityUtils.toString(entity);

			if (content.equals("true"))
			returnMsg = 5;
			if (content.equals("false"))
			returnMsg = 4;
		}

		return returnMsg;
	}
}