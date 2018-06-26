package cn.thyonline.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description:卖家信息
 * @Author: Created by thy
 * @Date: 2018/6/25 18:42
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String id;
    private String username;
    private String password;
    private String openid;

}
