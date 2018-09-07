package com.zhangpan.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * @author zhangpan
 * @date 2018年8月15日
 */
public class ImageUtil {
    
    /**
     * 图片转base64码
     * @param imgPath
     * @return
     */
    public static String imgToCode(String imgPath) {
        String code = "";
        try {
            FileInputStream in = new FileInputStream(imgPath);
            try {
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                in.close();
                code = Base64.getEncoder().encodeToString(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
    
    /**
     * base64码转图片
     * @param base64Code
     */
    public static void codeToImg(String imgPath, String base64Code) {
        
        try {
            byte[] bytes = Base64.getDecoder().decode(base64Code);
            FileOutputStream out = new FileOutputStream(imgPath);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 识别图片上的数字
     * @param imgUrl
     * @return
     */
    public static String getImgContent(String imgPath) {
        String content = "";
        File imageFile = new File(imgPath);
        // 读取图片数字
        ITesseract instance = new Tesseract();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setLanguage("eng");// 英文库识别数字比较准确
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        
        try {
            content = instance.doOCR(imageFile).replace("\n", "");
            System.out.println(content);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return content;
    }
    
    /**
     * 拷贝图片
     * @param sourceImg 原图路径
     * @param targetImg 目标路径
     */
    public void copyImage(String sourceImg, String targetImg) {
        BufferedImage buffer = null;
        try {
            buffer = ImageIO.read(new File(sourceImg));
            ImageIO.write(buffer, "jpg", new File(targetImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String imgPath = "E:/temp/c.jpg";
        String imgPath2 = "E:/temp/ttt.jpg";
        String imgToCode = imgToCode(imgPath);
        System.out.println(imgToCode);
        codeToImg(imgPath2, imgToCode);
    }
}
