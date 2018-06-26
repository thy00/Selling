package cn.thyonline.controller;

import cn.thyonline.dataobject.ProductCategory;
import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.form.CategoryForm;
import cn.thyonline.form.ProductForm;
import cn.thyonline.service.ProductCategoryService;
import cn.thyonline.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Description:卖家商品类目
 * @Author: Created by thy
 * @Date: 2018/6/25 17:08
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;
    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categories = categoryService.findAll();
        map.put("categories",categories);
        return new ModelAndView("category/list",map);
    }

    /**
     * 转跳到类目编辑/新增页面
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView list(@RequestParam(required = false) Integer categoryId, Map<String,Object> map){
        if (categoryId !=null ){
            ProductCategory category = categoryService.findOneByCategoryId(categoryId);
            map.put("category",category);
        }
        return new ModelAndView("category/index",map);
    }

    /**
     * 保存/更新
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory category = new ProductCategory();
        try {
            //判断是否是新增商品，如果是则需要添加商品id
            if (categoryForm.getCategoryId()!=null){
                category = categoryService.findOneByCategoryId(categoryForm.getCategoryId());//验证是否是已经存在的类目
            }
            BeanUtils.copyProperties(categoryForm,category);
            ProductCategory result = categoryService.save(category);
        } catch (Exception e) {
            log.error("【类目更新/保存】发生错误，e={}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
