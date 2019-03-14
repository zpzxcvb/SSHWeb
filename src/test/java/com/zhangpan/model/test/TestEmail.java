package com.zhangpan.model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangpan.mail.EmailTool;
import com.zhangpan.model.SysRole;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.user.SysUserService;
import com.zhangpan.util.DateUtil;
import com.zhangpan.util.mail.MailSenderInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEmail {
    @Autowired
    private EmailTool email;

    @Test
    public void test() throws Exception {
        final String username="zpzxcvb@126.com";
        final String password="527517062";
        String tos="527517062@qq.com";
        String ccAddress="17691125766@163.com";
        String bccAddress="3412003909@qq.com";
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setUserName(username);
        mailInfo.setPassword(password);
        mailInfo.setFromAddress(username);
        mailInfo.setToAddress(tos);//收件人
        mailInfo.setCcAddress(ccAddress);//抄送
        mailInfo.setBccAddress(bccAddress);//密送
        mailInfo.setSubject("测试");
        mailInfo.setContent("<h1>hello world！</h1>");
        List<DataSource> attachments = new ArrayList<>();
        DataSource dataSource1 = new FileDataSource("H:/photo/me.jpg");
        attachments.add(dataSource1);
        DataSource dataSource2 = new FileDataSource("H:/photo/me1.jpg");
        attachments.add(dataSource2);
        mailInfo.setAttachments(attachments);//附件
        boolean bool = email.sendSimpleMail(mailInfo);
        System.out.println(bool);
    }
}
