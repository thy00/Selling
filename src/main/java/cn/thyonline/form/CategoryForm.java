package cn.thyonline.form;

import lombok.Data;

/**
 * @Description:接收页面传递过来的类目信息
 * @Author: Created by thy
 * @Date: 2018/6/25 17:45
 */
@Data
public class CategoryForm {

    private Integer categoryId;//id
    private String categoryName;//名字
    private Integer categoryType;//编号
}
