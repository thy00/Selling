package cn.thyonline.service.impl;

import cn.thyonline.config.WechatAccountConfig;
import cn.thyonline.dto.OrderDTO;
import cn.thyonline.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/26 12:49
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Resource(name = "wxMpService")
    private WxMpService mpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage var1=new WxMpTemplateMessage();
        var1.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        var1.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data=Arrays.asList(
                new WxMpTemplateData("first","亲记得收货"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("remark","欢迎下次光临")
        );
        var1.setData(data);
        try {
            mpService.getTemplateMsgService().sendTemplateMsg(var1);
        } catch (WxErrorException e) {
            log.error("【微信消息模板】消息发送失败，{}",e);
        }
    }
}
