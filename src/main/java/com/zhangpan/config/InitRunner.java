package com.zhangpan.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zhangpan.constant.SysConstant;
import com.zhangpan.model.SysDictItem;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.sys.dict.item.SysDictItemService;
import com.zhangpan.service.sys.dict.type.SysDictTypeService;

/**
 * @author zhangpan
 * @date 2018年11月19日
 */
@Component
@Order(1)
public class InitRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(InitRunner.class);
    
    @Autowired
    private SysDictTypeService dictTypeService;
    
    @Autowired
    private SysDictItemService dictItemService;

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>>>>服务启动执行，初始化字典表数据<<<<<<<<<<<<<");
        Map<String, List<SysDictItem>> sysDicts = new HashMap<>();
        List<SysDictType> types = dictTypeService.findList(null);
        
        Map<String, Object> itemParam = new HashMap<>();
        for(SysDictType type : types) {
            itemParam.put("dictType", type.getDictCode());
            List<SysDictItem> item = dictItemService.findList(itemParam);
            sysDicts.put(type.getDictCode(), item);
        }
        SysConstant.putSysDicts(sysDicts);
    }

}
