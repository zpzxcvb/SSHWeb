package com.zhangpan.util;

public class SystemContext {
	private static ThreadLocal<Integer> pageNum = new ThreadLocal<Integer>();    // 保存第几页
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();    // 保存每页记录条数
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
