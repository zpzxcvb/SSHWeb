package com.zhangpan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
     */
    public static void downLoad(HttpServletResponse response, String filePath, String filename){
        File file = new File(filePath, filename);
        if(file.exists()){
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            
            FileInputStream fis = null; //文件输入流
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                
                byte[] buffer = new byte[1024];
                int count = 0;
                while((count = fis.read(buffer)) != -1){
                    os.write(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    os.close();
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
	
	public static void main(String[] args) throws Exception {
	    String filePath="E:/test.csv";
//	    deleteFile(filePath);
		/*String filePath=System.getProperty("user.dir");
		List<TreeNode> list=scanFiles(filePath);
		for(TreeNode node : list){
			System.out.println(JSON.toJSONString(node));
		}*/
		List list=readFile(filePath);
		System.out.println(list);
	}

}
