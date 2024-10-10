package com.xiaoweii.train.batch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.xiaoweii")//不能写的太通用, 比如只写了个"com",这样会把第三方的类也扫描到, 会有问题
@MapperScan("com.xiaoweii.train.*.mapper")//添加mapper扫描
public class BatchApplication {
    private static final Logger LOG = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        /*
        这里也可以打印欢迎语句,口号等, 或者项目常用的地址, 比如接口文档,项目文档, 数据库文档等
        项目的log
         */
        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"),env.getProperty("server.servlet.context-path"));
    }
}
