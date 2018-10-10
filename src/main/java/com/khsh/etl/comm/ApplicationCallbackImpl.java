package com.khsh.etl.comm;

import com.ejet.comm.exception.CoBusinessException;
import com.ejet.comm.quartz.JobSchedulerManager;
import com.ejet.comm.utils.PropertyUtils;
import com.ejet.comm.utils.io.IOUtils;
import com.ejet.configurer.IApplicationBootCallback;
import com.ejet.context.CoApplicationContext;
import com.ejet.global.CoConstant;
import com.khsh.etl.kettle.kit.ConstantKettle;
import com.khsh.etl.kettle.kit.KettleFactory;
import com.khsh.etl.kettle.kit.KettleRepositoryConfig;
import com.khsh.etl.kettle.kit.KettleRepositoryLoader;
import com.khsh.etl.model.EtlKettleRepositoryModel;
import com.khsh.etl.model.SysJobScheduleModel;
import com.khsh.etl.service.impl.EtlKettleRepositoryServiceImpl;
import com.khsh.etl.service.impl.SysJobScheduleServiceImpl;
import org.pentaho.di.core.exception.KettleException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: ApplicationCallbackImpl
 * Author:   Ejet
 * CreateDate:     2018-09-10 10:28
 * Description: 应用启动后回调实现类
 * History:
 * Version: 1.0
 */
public class ApplicationCallbackImpl implements IApplicationBootCallback {

    private final Logger log = LoggerFactory.getLogger(ApplicationCallbackImpl.class);
    @Autowired
    EtlKettleRepositoryServiceImpl kettleRepositoryService;
    @Autowired
    SysJobScheduleServiceImpl jobScheduleService;

    @Override
    public void callApplicationReadyEvent() {
        log.info("======   ★启动应用完成callApplicationReadyEvent★   ======");
        try {
            if(jobScheduleService==null) {
                jobScheduleService = CoApplicationContext.getBean(SysJobScheduleServiceImpl.class);
            }
            if(kettleRepositoryService ==null) {
                kettleRepositoryService = CoApplicationContext.getBean(EtlKettleRepositoryServiceImpl.class);
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadKettleRepository();
                }
            }).start();

            //
            addQuartzListener();
            //
            startQuartzJobs();

        } catch (Exception e) {
            log.error("======   ★启动应用完成callApplicationReadyEvent★   ======", e);
        }
    }

    /**
     * 添加监听器， 调度器监听器和任务监听器
     */
    private void addQuartzListener() {
        //JobSchedulerManager.getInstance().setScheduler(schedulerFactoryBean.getScheduler());
        try {
            //设置监听器实现, 如果需要？？？
            JobSchedulerManager.getInstance().addSchedulerListener(new SchedulerListenerImpl());

        } catch (SchedulerException e) {
            log.error("初始化quartz错误", e);
        }

    }


    /**
     * 预加载加载作业资源
     */
    public void loadKettleRepository() {
        //初始化kettle服务
        InputStream inputStream = null;
        try {
            log.info("======   init kettle  ======");
            KettleFactory.initEnv();

            log.info("======   load kettle resource ======");
            ClassPathResource resource = new ClassPathResource("kettle_repository.properties");
            inputStream = resource.getInputStream();
            KettleRepositoryConfig config = (KettleRepositoryConfig) PropertyUtils.loadProperty(inputStream, KettleRepositoryConfig.class);
            KettleFactory.connectRepository(config);

            List<EtlKettleRepositoryModel> list = kettleRepositoryService.queryByCond(null);
            if(list!=null) {
                log.info("======   加载kettle资源   ======" + list.size());
                for (EtlKettleRepositoryModel item : list) {
                    try {
                        if(ConstantKettle.REP_TYPE_DB.equals(item.getRepType().toUpperCase())) { //文件方式不需要加载
                            KettleRepositoryLoader.loadRepository(item.getKtlJobType(), item.getRepPath());
                        }
                    } catch (KettleException e) {
                        log.error("加载kettle资源失败??? kettleType:{}, 路径:",  item.getKtlJobType(), item.getRepPath() , e);
                    }
                }
            }

        } catch (KettleException | CoBusinessException | InstantiationException | IllegalAccessException | IOException e) {
            log.error("加载kettle资源失败", e);
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 启动任务
     */
    public void startQuartzJobs() {
        try {
            log.info("======   加载任务   ======");
            //JobSchedulerManager.getInstance().setScheduler(schedulerFactoryBean.getScheduler());
            List<SysJobScheduleModel> list = jobScheduleService.queryByCond(null);
            if (list==null || list.size()==0) {
                return ;
            }
            log.info("加载定时任务信息, 任务总数：{}", list.size());
            for (SysJobScheduleModel model : list) {
                if (model.getStatus() != null && model.getStatus()== CoConstant.STATUS_NORMAL) { //1:正常运行
                    if (model.getIsTask() != null && model.getIsTask() == 0) { //0: 定时任务，1：普通任务执行一次'
                        log.info("加载定时任务：{}, 任务类:{}, 表达式:{}",  model.getJobName(), model.getJobClazz(), model.getJobCron());
                        JobSchedulerManager.getInstance().addScheduleJob(model);
                    } else { //当前类不作为任务运行，直接运行execute方法
                        log.info("加载直运任务：{}, 任务类:{}, 表达式:{}",  model.getJobName(), model.getJobClazz(), model.getJobCron());
                        JobSchedulerManager.getInstance().executeJob(model);
                    }
                }
            }

        } catch (CoBusinessException | SchedulerException e) {
            log.error("加载任务错误", e);
        }
    }


    @Override
    public void callContextStoppedEvent() {

    }

    @Override
    public void callContextClosedEvent() {

    }

    @Override
    public void callContextRefreshedEvent() {

    }

}
