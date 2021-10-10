package com.yh.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.lxw.framework.domain.cms") //扫描实体类
@ComponentScan(basePackages = {"com.lxw.api.cms","com.lxw.api.config"}) //1.扫描接口2.因为没有扫描swagger配置类，所以swagger页面进不来，加上即可
@ComponentScan(basePackages = {"com.yh.cms"})  //这里在上面配置了扫描后可能自己包以及子包可能扫描不到（默认扫描不生效）,再配置一下扫描本类
@ComponentScan(basePackages = {"com.lxw.framework.exception"}) //配置了全局异常扫描类，在这里要扫描到异常的包
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }
}
