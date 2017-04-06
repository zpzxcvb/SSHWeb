package com.zhangpan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张攀
 * @ClassName : StringUtil
 * @ModifiedBy : 张攀
 * @date : 2017-1-18 上午10:56:10
 */
public class StringUtil {
	private static StringUtil instance=new StringUtil();
	private StringUtil(){}
	/**
	 * 判断字符串是否为空白,包括null和""，"  "
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null||"".equals(str.trim());
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		String regex="^-?[0-9]+$";
		return str.matches(regex);
	}
	
	/**
	 * 返回目标字串中匹配的第一组子串
	 * @param regex 正则表达式
	 * @param targetStr 目标字串
	 * @return
	 */
	public static List<String> regexStr(String regex, String targetStr){
		List<String> list=new ArrayList<String>();
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(targetStr);
		while(m.find()){
			String str=m.group();
			list.add(str);
		}
		return list;
	}
	
	public static void main(String[] args) {
		StringUtil s=new StringUtil();
		String str="0a12";
//		System.out.println(isEmpty(str));
		System.out.println(isNumber(str));
	}
}
