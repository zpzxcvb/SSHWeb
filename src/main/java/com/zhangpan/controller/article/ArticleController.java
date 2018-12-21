package com.zhangpan.controller.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zhangpan.constant.DictTypeEnum;
import com.zhangpan.constant.SysConstant;
import com.zhangpan.controller.BaseController;
import com.zhangpan.model.SysDictItem;
import com.zhangpan.model.SysInterface;
import com.zhangpan.model.article.Article;
import com.zhangpan.service.article.ArticleService;
import com.zhangpan.util.DateUtil;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    
    @Autowired
    private ArticleService articleService;
    
    @RequestMapping("/articleTypes")
    @ResponseBody
    public Object articleTypes(){
        List<Map<String, Object>> types = SysConstant.getSysDictValue(DictTypeEnum.ARTICLETYPE.getValue());
        return types;
    }
	
	@RequestMapping("/list")
    public String chartlist() {
        return "article/list";
    }
	
	@RequestMapping("/pageList")
    @ResponseBody
    public Object pageList(){
	    paramMap.put(DictTypeEnum.ARTICLETYPE.getValue(), DictTypeEnum.ARTICLETYPE.getValue());
        Page<Object> page = articleService.findPage(paramMap);
        return pageData(page);
    }
	
	@RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Integer id, Model model){
        if(id != null) {
            Article obj = articleService.findById(id);
            model.addAttribute("obj", obj);
        }
        return "/article/edit";
    }
	
	@RequestMapping("/save")
    @ResponseBody
    public Object save(Article model){
	    Integer userId = (Integer) session.getAttribute("userId");
        model.setCreateTime(new Date());
        model.setUserId(userId);
        int count = articleService.save(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public Object update(Article model){
        Integer userId = (Integer) session.getAttribute("userId");
        model.setUserId(userId);
        int count = articleService.update(model);
        return getResponseState(count);
    }
    
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]")Integer[] ids){
        int count = articleService.deleteByIds(ids);
        return getResponseState(count);
    }
    
    /**
     * 预览文章
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/preview/{id}")
    public String preview(@PathVariable("id")Integer id, Model model){
        Object obj = articleService.preview(id);
        model.addAttribute("obj", obj);
        return "/article/preview";
    }
}
