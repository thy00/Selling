package cn.thyonline.controller;

import cn.thyonline.dataobject.ProductCategory;
import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.dto.OrderDTO;
import cn.thyonline.enums.ResultEnum;
import cn.thyonline.form.ProductForm;
import cn.thyonline.service.ProductCategoryService;
import cn.thyonline.service.ProductInfoService;
import cn.thyonline.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @Description:卖家
 * @Author: Created by thy
 * @Date: 2018/6/24 21:14
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 商品订单列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        Page<ProductInfo> productInfos = infoService.findAll(request);
        map.put("productInfos",productInfos);
        map.put("currentPage",page);
        return new ModelAndView("product/list",map);
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam String productId,Map<String,Object> map){
        try {
            ProductInfo productInfo = infoService.onSale(productId);
        } catch (Exception e) {
            log.error("【卖家端商品上架】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.PRODUCT_ONSALE_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam String productId,Map<String,Object> map) {
        try {
            ProductInfo productInfo = infoService.offSale(productId);
        } catch (Exception e) {
            log.error("【卖家端商品下架】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFFSALE_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 转跳到商品编辑/新增页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView list(@RequestParam(required = false) String productId,Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = infoService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> categories = categoryService.findAll();
        map.put("categories",categories);
        return new ModelAndView("product/index",map);
    }

    /**
     * 保存/更新
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult,Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //判断是否是新增商品，如果是则需要添加商品id
            if (!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = infoService.findOne(productForm.getProductId());//验证是否是已经存在的商品
            }else {
                productForm.setProductId(KeyUtil.genUnigueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            ProductInfo result = infoService.save(productInfo);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
