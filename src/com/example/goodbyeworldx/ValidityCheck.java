package com.example.goodbyeworldx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidityCheck { 
	
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
		}
	
	public boolean isEmail(String email) {
		String str = "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,4}\\.[a-zA-Z0-9]{1,3}$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
		}
	public boolean weatherFit(String ss){
		System.out.println(ss);
		//([\\s\\S]*)   用于匹配任意字符
		Pattern pattern = Pattern.compile("([\\s\\S]*)地址已被注册([\\s\\S]*)");
		Matcher m = pattern.matcher(ss);
		return m.matches();
	}
}
	
