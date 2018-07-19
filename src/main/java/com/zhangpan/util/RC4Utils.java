package com.zhangpan.util;

import java.io.UnsupportedEncodingException;

public class RC4Utils {

	/*
	 * 解密
	 */
    public static String deCode(String key, String value) {
    	if ("".equals(value)||null==value) {
			return "";
		}
        return RC4(key, new String(hexStringToBytes(value)));
    }

    /*
     * 加密
     */
    public static String enCode(String key, String value) {
    	if ("".equals(value)||null==value) {
			return "";
		}
        return bytesToHexString(RC4(key, value).getBytes());
    }

    private static String RC4(String key, String value) {
        int[] iS = new int[256];
        byte[] iK = new byte[256];
        for (int i = 0; i < 256; i++)
            iS[i] = i;
        for (short i = 0; i < 256; i++) {
            iK[i] = (byte) key.charAt((i % key.length()));
        }
        int j = 0;
        for (int i = 0; i < 255; i++) {
            j = (j + iS[i] + iK[i]) % 256;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
        }
        int i = 0;
        j = 0;
        char[] iInputChar = value.toCharArray();
        char[] iOutputChar = new char[iInputChar.length];
        for (short x = 0; x < iInputChar.length; x++) {
            i = (i + 1) % 256;
            j = (j + iS[i]) % 256;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
            int t = (iS[i] + (iS[j] % 256)) % 256;
            int iY = iS[t];
            char iCY = (char) iY;
            iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
        }
        return new String(iOutputChar);
    }

    /**
    * @Title: bytesToHexString 
    * @Description: byte[]转 十六进制
    * @param @param srcs
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    private static String bytesToHexString(byte[] srcs) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (srcs == null || srcs.length <= 0) {
            return null;
        }
        for (byte src : srcs) {
            int v = src & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
    * @Title: hexStringToBytes 
    * @Description: 十六进制转byte[] 
    * @param @param hexString
    * @param @return    设定文件 
    * @return byte[]    返回类型 
    * @throws
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
    	String key ="zpzxcvb";
		String value ="name=admin&password=123&type=是&id=3";
		value = "sysid=ZXGS&sysname=报账平台&syspwd=password&documentnum=103017032810404578032&dtype=业务招待申请单&userid=30036&username=zhaoshen@cmos.cmcc&ouname=OU_中移在线服务本部&ouid=0810&linkname=起草&token=1490670770051";
		System.out.println(value);
    	
    	System.err.println(RC4Utils.enCode(key, value));
    	
    	System.out.println(RC4Utils.deCode(key, RC4Utils.enCode(key, value)));
    	
    	System.out.println(key.charAt(0%key.length()));
    }
}