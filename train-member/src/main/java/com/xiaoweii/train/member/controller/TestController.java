package com.xiaoweii.train.member.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    //增加日志的第一种方法,在return之前增加一个返回日志,简单粗暴,具有侵入性,需要改每一个接口的代码
    //第二种方法,增添AOP和拦截器,没有侵入性;AOP更加强大, 可以拦截controller,也可以拦截service等其他类
    //增加的几个类固定相对
    public String hello(){
        return "Hello World!!!Member";
    }
}
