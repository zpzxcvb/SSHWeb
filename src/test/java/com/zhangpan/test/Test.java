package com.zhangpan.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.annotations.JsonAdapter;
import com.zhangpan.controller.BaseController;
import com.zhangpan.util.ImageUtil;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoadLibs;

public class Test {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public static void main(String[] args) throws Exception {
        log.info("1-----3");
        //二值化
//        BufferedImage BinaryImage = ImageHelper.convertImageToBinary(ImageIO.read(new File("H:/photo/idCard.jpg")));
//        ImageIO.write(BinaryImage, "jpg", new File("H:/photo/", "test2.jpg"));
        //灰度化
//        BufferedImage grayImage = ImageHelper.convertImageToGrayscale(ImageIO.read(new File("H:/photo/idCard.jpg")));
//        ImageIO.write(grayImage, "jpg", new File("H:/photo/", "test2.jpg"));
        String[] s= {""};
        String s1=String.join(",", s);
        System.out.println(s1);
    }

}
