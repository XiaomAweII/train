package com.xiaoweii.train.aspect;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;


//这个类是切面类,以后用到直接使用即可
@Aspect
@Component
public class LogAspect {
    public LogAspect() {
        System.out.println("Common LogAspect");
    }

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义一个切点
     */
    //基本上固定, * 表示所有的返回值; com.xiaoweii.. 包下的子层级 ; *Controller 所有带Controller后缀的; .*任何方法; (..) 任意参数
    @Pointcut("execution(public * com.xiaoweii..*Controller.*(..))")
    public void controllerPointcut() {
    }


    @Before("controllerPointcut()")//在进入切点之前
    public void doBefore(JoinPoint joinPoint) {

        //增加日志流水号
        //值为系统参数+随机三位字符串,得到的值放到LOG_ID当中
        //MDC日志框架自带的增加自定义参数功能
        MDC.put("LOG_ID",System.currentTimeMillis()+ RandomUtil.randomString(3));

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();//这里的name就是方法名

        // 打印请求信息
        LOG.info("------------- 开始 -------------");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        // LOG.info("请求参数: {}", JSONObject.toJSONString(args));

        // 排除特殊类型的参数，如文件类型(文件可能很大,无意义,所以忽略不打印), 其余类型也可以增添
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示：身份证、手机号、邮箱、密码等
        //p.s.不管你做的是什么项目, 养成习惯, 敏感字段不要打印在日志里, 且数据库应该加密保存, 保障客户信息安全
        //p.s.自己开发的系统自己也破解不了, 保障客户信息安全
        String[] excludeProperties = {};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludefilter));
    }


    //环绕通知, 打印一组请求结果
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示：身份证、手机号、邮箱、密码等
        String[] excludeProperties = {};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}
