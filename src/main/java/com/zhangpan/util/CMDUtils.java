package com.zhangpan.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpan
 * @date 2018年8月16日
 */
public class CMDUtils {
    
    public static void readCMD (List<String> cmd) {
        InputStream in = null;
        try {
            ProcessBuilder builder = new ProcessBuilder();
            Map<String, String> environment = builder.environment();
            System.out.println(environment);
            builder.command(cmd);
            Process process = builder.start();
            in = process.getInputStream();
            byte[] b = new byte[1024];  
            int count = 0;  
            while((count = in.read(b)) != -1){  
                System.out.println("命令输出内容为=====："+new String(b,0,count,"gbk"));  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static boolean pingAdress(String address) {
        boolean flag = false;
        try {
            InetAddress net = InetAddress.getByName(address);
            try {
                flag = net.isReachable(1000);
                if(flag) {
                    System.out.println(address);
                }else {
                    System.err.println(address);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        return flag;
    }
    
    /**
     * ping 本地网段可用ip
     */
    public static void pingLocalIP() {
        try {
            InetAddress local=InetAddress.getLocalHost();
            String net=local.getHostAddress().substring(0, local.getHostAddress().lastIndexOf(".")+1);
            for (int i = 0; i <= 255; i++) {
                String ip=net+i;
                pingAdress(ip);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
//        List<String> cmd = new ArrayList<String>();
//        cmd.add("echo");cmd.add("'aaa'");
//        readCMD(cmd);
        try {
            pingAdress("www.baidu.com");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
