package com.zhangpan.test;

public class QuitSortTest {
	
	public static int getMiddle(int a[],int low,int high){
		int middle=a[low];//[5, 2, 1, 7, 6, 9, 4, 3, 8]
		
		while(low<high){
			while(low < high && a[high]>=middle){
				high--;
			}
			a[low]=a[high];
			while(low < high && a[low]<=middle){
				low++;
			}
			a[high]=a[low];
		}
		a[low]=middle;
		return low;
	}
	
	public static void quictSorct(int a[],int low,int high){
		if (low < high) {
			int middle=getMiddle(a, low, high);
			quictSorct(a, low, middle-1);
			quictSorct(a, middle+1, high);
		}
	}
	
	public static void main(String[] args) {
		int a[]={2,3,5,1,0,4};
		quictSorct(a, 0, a.length-1);
		for(int i : a){
			System.out.print(i);
		}
	}
}
