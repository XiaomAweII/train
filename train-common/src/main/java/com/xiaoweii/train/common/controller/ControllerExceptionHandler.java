package com.xiaoweii.train.common.controller;


import com.xiaoweii.train.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice//通过这个注解, 实现类似拦截器的效果
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)//对应所有的异常做处理
    @ResponseBody
    public CommonResp exceptionHandler(Exception e) throws Exception {
        CommonResp commonResp = new CommonResp();
        LOG.error("系统异常：", e);
        commonResp.setSuccess(false);
//        commonResp.setMessage("系统出现异常，请联系管理员");
        commonResp.setMessage(e.getMessage());//可以更清楚的看清楚返回值, 但是实际并不常用
        return commonResp;
    }

}
