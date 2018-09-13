package com.zhangpan.test;

public class BigDigetTest {
	
	static int mutuAB[]=null;
	/**			1  2  3
	 * 			1  2  3
	 * 		    3   6  9
	 * 		2	4	6
	 * 	1	2	3
	 * 
	 * 	1	4	10	12	9  相加
	 * 	1	5	1	2	9  进位
	 * @param a
	 * @param b
	 * @return
	 */
	public static String mutuly(String a,String b){
		StringBuffer s=new StringBuffer();
		mutuAB=new int[a.length()+b.length()-1];
		for(int i=a.length()-1;i>=0;i--){
			int aa=a.charAt(i)-'0';
			for(int j=b.length()-1;j>=0;j--){
				int bb=b.charAt(j)-'0';
				mutuAB[i+j]+=aa*bb;
			}
		}
		bit(mutuAB);
		for(int i : mutuAB){
			s.append(i);
		}
		return s.toString();
	}
	
	public static void bit(int mutuAB[]){
		for(int i=mutuAB.length-1;i>0;i--){
			mutuAB[i-1]+=mutuAB[i]/10;
			mutuAB[i]=mutuAB[i]%10;
		}
	}
	
	/**
	 * 321
	 * 21
	 * 531
	 * @param a
	 * @param b
	 */
	public static String add(String a,String b){
		StringBuffer s = new StringBuffer();
		
		String s1=new StringBuffer(a).reverse().toString();
		String s2=new StringBuffer(b).reverse().toString();
		
		int al=a.length();
		int bl=b.length();
		mutuAB=new int[al+bl-1];
		
		String temp="";
		
		if(al<bl){
			temp=s1;
			s1=s2;
			s2=temp;
		}
		
		for(int i=0;i<=s1.length()-1;i++){
			int aa = s1.charAt(i)-'0';
			int bb = 0;
			if(i<=s2.length()-1){
				bb=s2.charAt(i)-'0';
			}
			mutuAB[i]=aa+bb;
		}
		
		for (int i = 0; i < mutuAB.length/2; i++) {
			int t = mutuAB[i];
			mutuAB[i]=mutuAB[mutuAB.length-1-i];
			mutuAB[mutuAB.length-1-i]=t;
		}
		
		bit(mutuAB);
		for(int i : mutuAB){
			s.append(i);
		}
		return s.toString();
	}
	public static void main(String[] args) {
//		bit(mutuAB);
//		int count=sum1(5);
		String count=mutuly("1234","123");
//		count = add("88","67");
		System.out.println(count);
		
	}

}
