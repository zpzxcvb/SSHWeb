package com.zhangpan.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zhangpan.model.SysUser;

public class SortUtil {
	/**
	 * 对list进行排序
	 * @param <T>
	 * 
	 * @param sortList
	 *            需要排序的list
	 * @param param1
	 *            排序的参数名称
	 * @param orderType
	 *            排序类型：正序-asc；倒序-desc
	 */
	public static <T> void sort(List<T> list, String fieldName) {
		Collections.sort(list, new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				try {
					Field field1 = o1.getClass().getDeclaredField(fieldName);
					Field field2 = o2.getClass().getDeclaredField(fieldName);
					//设置private可读
					field1.setAccessible(true);
					field2.setAccessible(true);
					
					String value1 = field1.get(o1).toString();
					String value2 = field2.get(o2).toString();
					
					return value1.compareTo(value2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return 0;
			}
			
		});
	}
	
	public static void main(String[] args) {
		List<SysUser> list = new ArrayList<SysUser>();
		SysUser user=new SysUser();user.setUserName("a");
		user.setUserId(2);list.add(user);
		user=new SysUser();user.setUserName("c");
		user.setUserId(3);list.add(user);
		user=new SysUser();user.setUserName("b");
		user.setUserId(1);list.add(user);
		System.out.println(JSON.toJSONString(list));
		sort(list, "userName");
		System.out.println(JSON.toJSONString(list));
	}

}
