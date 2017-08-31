package com.zhangpan.ssh.test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test {
	
	public static void main(String[] args)  {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar calendar = Calendar.getInstance();
        
		long a=1499299200000l;
        calendar.setTimeInMillis(a);
        
        System.out.println(sdf.format(calendar.getTime()));
	}
	
	
	
}
