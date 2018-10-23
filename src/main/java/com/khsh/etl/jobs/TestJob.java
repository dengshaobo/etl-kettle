package com.khsh.etl.jobs;

import com.alibaba.fastjson.JSONObject;
import com.ejet.comm.exception.CoBusinessException;
import com.ejet.comm.quartz.CoQuartzJob;
import com.ejet.comm.utils.StringUtils;
import com.ejet.context.CoApplicationContext;
import com.khsh.etl.kettle.kit.ConstantKettle;
import com.khsh.etl.kettle.kit.KettleRunner;
import com.khsh.etl.service.impl.EtlKettleRepositoryServiceImpl;
import com.khsh.etl.vo.bus.KettleJobParamVO;
import org.pentaho.di.core.exception.KettleException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: KettleJob
 * Author:   Ejet
 * CreateDate:     2018/9/30 15:27
 * Description: 基于kettle抽取数据任务
 * History:
 * Version: 1.0
 */
public class TestJob extends CoQuartzJob {
    private final Logger log = LoggerFactory.getLogger(TestJob.class);


    @Override
    public void run(JobExecutionContext context) {

        try {
            log.info("★★★★★ 测试任务:{}, {}" , jobModel.getJobName(), jobModel.getParamValue());

            Thread.sleep(1000*50);

        } catch (Exception e) {
            log.error("Kjb任务异常", e);
            JobExecutionException je = new JobExecutionException(e);
            // 不再执行，所有该job的调度全部停止
            je.setUnscheduleAllTriggers(true);
            //再次尝试执行
            //je.refireImmediately();
        }
    }

    @Override
    public void stop() {

    }
}
