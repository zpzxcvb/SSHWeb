package com.zhangpan.util;

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
	
	public static void main(String[] args) {
		StringUtil s=new StringUtil();
		String str="0a12";
//		System.out.println(isEmpty(str));
		System.out.println(isNumber(str));
	}
}
