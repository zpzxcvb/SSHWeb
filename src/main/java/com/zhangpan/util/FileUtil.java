package com.zhangpan.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件读写工具类
 * @author zhangpan
 * @date 2018年9月27日
 */
public class FileUtil {
    
    /**
     * 文件下载
     * @param response
     * @param filePath
     * @param filename
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static void downLoad(HttpServletResponse response, String filePath, String filename) throws UnsupportedEncodingException{
        File file = new File(filePath, filename);
        if(file.exists()){
        	response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf-8"));
            
            OutputStream os = null; //输出流
            BufferedInputStream bis = null;
            try {
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                
                byte[] buffer = new byte[1024];
                int size = 0;
                while((size = bis.read(buffer)) != -1){
                    os.write(buffer, 0, size);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(bis != null) {
                    	bis.close();
    				}
                    if(os != null) {
                    	os.close();
    				}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
	public static List<TreeNode> scanFiles(String filePath) {
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		List<TreeNode> fileTreeNodes=new ArrayList<TreeNode>();
		File[] files=null;
		File file = new File(filePath);
		if(file.exists()){
			files=file.listFiles();
			for(File f : files){
				if(f.getName().startsWith(".")){
					continue;
				}
				if(f.isDirectory()){
					if(f.getName().matches("bin|target")) {
						continue;
					}
					TreeNode node=buildTreeNode(f,true);//目录文件夹
					treeNodes.add(node);
				}else{
					TreeNode node=buildTreeNode(f,false);//非目录文件
					fileTreeNodes.add(node);
				}
			}
			treeNodes.addAll(fileTreeNodes);
		}else{
			System.out.println("不存在文件。");
		}
		return treeNodes;
	}
	
	private static TreeNode buildTreeNode(File file,boolean isParent){
		TreeNode treeNode=new TreeNode();
		treeNode.setName(file.getName());
		treeNode.setPath(file.getAbsolutePath());
		treeNode.setParent(isParent);
		treeNode.setId(file.getAbsolutePath());
		treeNode.setPid(file.getParent());
		return treeNode;
	}
	
	public static List<String> readFile(String filePath) throws Exception{
		List<String> list=new ArrayList<String>();
		FileInputStream in=new FileInputStream(filePath);
		BufferedReader br=new BufferedReader(new InputStreamReader(in,"UTF-8"));
		String str="";
		int rownum=0;
		while((str=br.readLine())!=null){
			rownum++;
			System.out.println(rownum+"---->"+str);
			str=str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			list.add(str);
		}
		br.close();
		return list;
	}
	
	/**
	 * 递归删除指定文件夹下的文件及文件夹
	 * @param path
	 */
	public static void deleteFile(String path) {
	    File file = new File(path);
	    if(file.exists()) {
	        File[] listFiles = file.listFiles();
	        for(File f : listFiles) {
	            if(f.isDirectory()) {
	                deleteFile(f.getAbsolutePath());
	            }
	            f.delete();
	        }
	        
	    }
	}
	
	public static void copyFile_NIO(String copyFile, String outFile) throws Exception {
		FileInputStream fin = new FileInputStream(copyFile);
		FileOutputStream fout = new FileOutputStream(outFile);
		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		while (true) {
			buffer.clear();
			int r = fcin.read(buffer);
			if (r == -1) {
				break;
			}
			buffer.flip();
			fcout.write(buffer);
		}
		
		fcin.close();
		fcout.close();
		fin.close();
		fout.close();
	}
	
	/**
	 * 字节流复制文件
	 * @param copyFile 复制文件路径
	 * @param outFile 生成文件路径
	 */
	public static void copyFile_IO(String copyFile, String outFile){
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			bin =new BufferedInputStream(new FileInputStream(copyFile));//默认8M的缓存
			bout = new BufferedOutputStream(new FileOutputStream(outFile));
			byte[] buffer = new byte[1024];
			int size = 0;
			//先从硬盘读出8M内容到缓存中。然后从缓存中读取1024字节写入文件，自然要比从硬盘中快得多。每8M与硬盘交互一次
	        while((size = bin.read(buffer)) != -1){
	        	bout.write(buffer, 0, size);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bin != null) {
					bin.close();
				}
				if(bout != null) {
					bout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
//	    deleteFile(filePath);
		/*String filePath=System.getProperty("user.dir");
		List<TreeNode> list=scanFiles(filePath);
		for(TreeNode node : list){
			System.out.println(JSON.toJSONString(node));
		}*/
//		List list=readFile(filePath);
//		System.out.println(list);
	    String copyFile="C:/zp/a.txt",
	    	outFile1="C:/zp/a1.txt",
	    	outFile2="C:/zp/a2.txt";
	    long start = System.currentTimeMillis();
	    copyFile_NIO(copyFile, outFile1);
	    long end1 = System.currentTimeMillis();
	    copyFile_IO(copyFile, outFile2);
	    long end2 = System.currentTimeMillis();
	    System.out.println("copyFile_IO copy lasts: " + (end1 - start));
		System.out.println("copyFile_IO_buffer copy lasts: " + (end2 - end1));
		
	}

}
