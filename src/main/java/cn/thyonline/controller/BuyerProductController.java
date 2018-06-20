package cn.thyonline.controller;

import cn.thyonline.VO.ProductInfoVO;
import cn.thyonline.VO.ProductVO;
import cn.thyonline.VO.ResultVO;
import cn.thyonline.dataobject.ProductCategory;
import cn.thyonline.dataobject.ProductInfo;
import cn.thyonline.service.ProductCategoryService;
import cn.thyonline.service.ProductInfoService;
import cn.thyonline.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:卖家商品
 * @Author: Created by thy
 * @Date: 2018/6/20 13:23
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService infoService;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1、查询所有上架商品
        List<ProductInfo> infos = infoService.findUpAll();
        //2、查询类目
        List<Integer> categoryTypes = infos.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(categoryTypes);
        //3、数据拼装
        List<ProductVO> productVOS=new ArrayList<>();
        for (ProductCategory productCategory:categories){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> infoVOS=new ArrayList<>();
            for (ProductInfo info:infos){
                if (info.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO infoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(info,infoVO);
                    infoVOS.add(infoVO);//将商品信息装进集合
                }
            }
            productVO.setProductInfoVOS(infoVOS);
            productVOS.add(productVO);//将商品分类信息装进集合
        };
        return ResultVOUtil.success(productVOS);
    }

}
