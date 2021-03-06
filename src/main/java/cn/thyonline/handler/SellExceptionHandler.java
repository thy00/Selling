package cn.thyonline.handler;

import cn.thyonline.VO.ResultVO;
import cn.thyonline.config.ProjectUrlConfig;
import cn.thyonline.exception.SellException;
import cn.thyonline.exception.SellerAuthorizeException;
import cn.thyonline.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: Created by thy
 * @Date: 2018/6/26 1:42
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig urlConfig;

    //拦截登录
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(urlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(urlConfig.getSellUrl())
                .concat("/sell/seller/login"));
    }

    //拦截异常
    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
