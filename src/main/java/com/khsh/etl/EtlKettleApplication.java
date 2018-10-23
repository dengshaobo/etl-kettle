package com.khsh.etl;

import com.ejet.CommWebApplication;
import com.ejet.CommWebQuartzApplication;
import com.ejet.bss.userinfo.UserInfoApplication;
import com.ejet.comm.CommWebRedisApplication;
import com.ejet.context.CoApplicationContext;
import com.khsh.etl.comm.ApplicationCallbackImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication()
@EnableCaching
public class EtlKettleApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(EtlKettleApplication.class);


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EtlKettleApplication.class);
    }

    public static void main(String[] args) {

        //每一个模块，都可以有自己的启动回调实现接口，只需要将实现类添加进去即可
        ApplicationCallbackImpl callbackImpl = new ApplicationCallbackImpl();
        //设置启动回调接口
        CoApplicationContext.getInstance().addApplicationBootCallback(callbackImpl);


        //每个模块都可以有自己的拦截器，过滤器，只需要将相关接口加进去。
        List<Class> list  = new ArrayList<>();
        list.add(EtlKettleApplication.class);       //本项目
        list.add(CommWebApplication.class);         //基础项目
        list.add(CommWebQuartzApplication.class);   //调度项目
        list.add(CommWebRedisApplication.class);    //redis项目
        list.add(UserInfoApplication.class);    //userinfo项目

        SpringApplication.run(list.toArray(new Class[list.size()]), args);

        logger.info("======== start  ======");
        logger.trace("===========测试日志 trace =========");
        logger.debug("===========测试日志 debug =========");
        logger.info("===========测试日志 info =========");
        logger.warn("===========测试日志 warn =========");
        logger.error("===========测试日志 error =========");
    }



}
