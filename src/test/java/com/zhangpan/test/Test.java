package com.zhangpan.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.annotations.JsonAdapter;
import com.zhangpan.util.ImageUtil;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoadLibs;

public class Test {
    

    public static void main(String[] args) throws Exception {
        //二值化
//        BufferedImage BinaryImage = ImageHelper.convertImageToBinary(ImageIO.read(new File("H:/photo/idCard.jpg")));
//        ImageIO.write(BinaryImage, "jpg", new File("H:/photo/", "test2.jpg"));
        //灰度化
//        BufferedImage grayImage = ImageHelper.convertImageToGrayscale(ImageIO.read(new File("H:/photo/idCard.jpg")));
//        ImageIO.write(grayImage, "jpg", new File("H:/photo/", "test2.jpg"));
        Map map=new HashMap();
        map.put("name", "admin");
        map.put("age", "18");
        System.out.println(JSON.toJSONString(map, true));;
    }

}
