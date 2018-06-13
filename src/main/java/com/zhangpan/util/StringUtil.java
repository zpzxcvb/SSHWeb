package com.zhangpan.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张攀
 * @ClassName : StringUtil
 * @ModifiedBy : 张攀
 * @date : 2017-1-18 上午10:56:10
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空白,包括null和""，"  "
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null || "".equals(str.trim());
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
	
	/**
	 * 大整数字串相乘
	 * @param a
	 * @param b
	 * @return a*b 
	 */
	public static String bigNumberMultiply(String a, String b){
		//计算最终长度
		int maxLength=a.length()+b.length();
		int[] multi=new int[maxLength];
		
		char[] c1=a.toCharArray();
		char[] c2=b.toCharArray();
		
		for (int i = c1.length-1; i>=0 ; i--) {
			int vi=c1[i]-'0';
			for (int j = c2.length-1; j>=0 ; j--) {
				int vj=c2[j]-'0';
				multi[i+j+1]+=(vi*vj);
			}
		}
		String s=is10add1(multi);
		return s;
	}
	
	/**
	 * 大整数字串相加
	 * @param a
	 * @param b
	 * @return a+b
	 */
	public static String bigNumberPlus(String a, String b){
		//计算最终长度
		int maxLength=a.length()>b.length()?a.length():b.length();
		int[] sum=new int[maxLength+1];
		
		char[] c1=new StringBuffer(a).reverse().toString().toCharArray();
		char[] c2=new StringBuffer(b).reverse().toString().toCharArray();
		for (int i = 0; i < maxLength; i++) {
			int i1=i<a.length()?(c1[i]-'0'):0;
			int i2=i<b.length()?(c2[i]-'0'):0;
			sum[maxLength-i]=i1+i2;
		}
		String s=is10add1(sum);
		return s;
	}
	
	/**
	 * 大整数字串相减
	 * @param a
	 * @param b
	 * @return a-b  123-24=99
	 * 321
		42
		3 -2 -1
		2 7 9
		
		123
		 24
		1 0 -1
		0 9 9
	 */
	public static String bigNumberSub(String a, String b){
		//计算最终长度
		int maxLength=a.length()>b.length()?a.length():b.length();
		int[] sub=new int[maxLength];
		
		char[] c1=new StringBuffer(a).reverse().toString().toCharArray();
		char[] c2=new StringBuffer(b).reverse().toString().toCharArray();
		for (int i = 0; i < maxLength; i++) {
			int i1=i<a.length()?(c1[i]-'0'):0;
			int i2=i<b.length()?(c2[i]-'0'):0;
			sub[maxLength-i]=i1-i2;
		}
		
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	/**
	 * 逢十进一
	 * @param int a[]
	 * @return
	 */
	private static String is10add1(int[] a){
		StringBuffer sb = new StringBuffer();
		for (int i = a.length-1; i >0 ; i--) {
			if(a[i]>=10){
				a[i-1]+=a[i]/10;
				a[i]=a[i]%10;
			}
		}
		// 该字段用于标识是否有前置0，如果是0就不需要打印或者存储下来
		boolean flag=true;
		for(int i : a){
			if(i==0&&flag){
				continue;
			}else{
				flag=false;
			}
			sb.append(i);
		}
		return sb.toString();//.replaceAll("^0+", "")替换前置0
	}
	
	/**
     * 将array转换成逗号分隔的String
     * @param int a[]
     * @return
     */
	public static String arrayToString(Object o) {
	    String str="";
	    boolean flag = false;
		if(o instanceof List) {
			System.out.println("list");
		}else if(o instanceof Map) {
			System.out.println("map");
		}else if(o instanceof Object[]) {
			System.out.println("[]");
			Object[] objs = (Object[]) o;
			for(Object obj : objs) {
			    if(flag) {
			        str += ",";
			    }
			    str += obj;
			    flag = true;
			}
		}
		return str;
	}
	
	public static void main(String[] args) {
		String a="123";
		String b="24";
		int[] i=new int[]{1,2,3,4,5};
//		System.out.println(bigNumberPlus(a, b));
//		System.out.println(bigNumberSub(a, b));
//		System.out.println(bigNumberMultiply(a, b));
		System.out.println(arrayToString(i));
	}
}
