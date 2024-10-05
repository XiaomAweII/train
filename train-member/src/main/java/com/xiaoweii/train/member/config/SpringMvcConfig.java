package com.xiaoweii.train.member.config;

import com.xiaoweii.train.common.interceptor.LogInterceptor;
import com.xiaoweii.train.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 开启拦截器
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    MemberInterceptor memberInterceptor;

    @Resource
    LogInterceptor logInterceptor;

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);//启用拦截器

        // 路径不要包含context-path
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**") //正则表达式, 针对所有请求接口生效
                .excludePathPatterns( //排除
                        "/member/hello",
                        "/member/member/send-code",
                        "/member/member/login"
                );
    }
}
