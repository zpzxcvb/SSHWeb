package com.zhangpan.controller.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysDictItem;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.sys.dict.item.SysDictItemService;
import com.zhangpan.service.sys.dict.type.SysDictTypeService;

@Controller
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController {
	
    @Autowired
    private SysDictTypeService dictTypeService;
    
    @Autowired
    private SysDictItemService dictItemService;
	
    @RequestMapping("/list")
    public String list(){
        return "/sys/dict/list";
    }
    
    @RequestMapping("/type/findList")
    @ResponseBody
    public Object findList(){
        List<Map<String, Object>> menus=dictTypeService.findDictTypes(paramMap);
        map.put("open", true);
        map.put("id", 0);
        map.put("name", "字典类型");
        menus.add(map);
        return menus;
    }
    
    @RequestMapping("/type/goAddDictType")
    public String goAddDictType(){
        return "/sys/dict/editDictType";
    }
    
    @RequestMapping("/type/saveDictType")
    @ResponseBody
    public Object saveDictType(SysDictType model){
        int count = dictTypeService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/type/deleteById")
    @ResponseBody
    public Object deleteById(Integer id){
        int count = dictTypeService.deleteById(id);
        return getResponseState(count);
    }
    
    @RequestMapping("/item/saveOrUpdate")
    public String saveOrUpdate(Integer id, Model model){
        String dictType = "";
        if(id != null) {
            SysDictItem item = dictItemService.findById(id);
            dictType = item.getDictType();
            model.addAttribute("item", item);
        }else {
            dictType = paramMap.get("dictType");
        }
        model.addAttribute("dictType", dictType);
        return "/sys/dict/editDictItem";
    }
    
    @RequestMapping("/item/save")
    @ResponseBody
    public Object save(SysDictItem model){
        int count = dictItemService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/item/update")
    @ResponseBody
    public Object update(SysDictItem model){
        int count = dictItemService.update(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/item/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = dictItemService.deleteByIds(ids);
        return getResponseState(count);
    }
    
    @RequestMapping("/item/pageList")
    @ResponseBody
    public Object pageList(){
        Page<Object> page = dictItemService.findPage(paramMap);
        return pageData(page);
    }
    
    @RequestMapping("/type/checkCode")
    @ResponseBody
    public Object checkTypeCode(){
        int count = dictTypeService.checkTypeCode(paramMap);
        return getResponseState(count);
    }
    
    @RequestMapping("/item/checkCode")
    @ResponseBody
    public Object checkItemCode(){
        int count = dictItemService.checkItemCode(paramMap);
        return getResponseState(count);
    }
}
