package com.khsh.etl.jobs;

import com.alibaba.fastjson.JSONObject;
import com.ejet.comm.exception.CoBusinessException;
import com.ejet.comm.quartz.CoQuartzJob;
import com.ejet.comm.utils.StringUtils;
import com.ejet.context.CoApplicationContext;
import com.khsh.etl.kettle.kit.ConstantKettle;
import com.khsh.etl.kettle.kit.KettleRunner;
import com.khsh.etl.service.impl.EtlKettleRepositoryServiceImpl;
import com.khsh.etl.service.impl.SysJobParamServiceImpl;
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
public class KettleJob extends CoQuartzJob {
    private final Logger log = LoggerFactory.getLogger(KettleJob.class);
    @Autowired
    EtlKettleRepositoryServiceImpl  kettleRepositoryService;

    /**
     * @param param
     * @return
     * @throws KettleException
     */
    private boolean isKettleJob(KettleJobParamVO param) throws KettleException {
        boolean ret = false;
        if(param==null) {
            throw new KettleException("");
        }
        if(param!=null && param.getParamType()!=null && ConstantKettle.KETTLE.equals(param.getParamType().toUpperCase())) {
            return true;
        }
        return ret;
    }

    @Override
    public void run(JobExecutionContext context) {
        try {
            log.info("★★★★★Kjb作业任务:{}, {}" , jobModel.getJobName(), jobModel.getParamValue());
            if(kettleRepositoryService==null) {
                kettleRepositoryService = CoApplicationContext.getBean(EtlKettleRepositoryServiceImpl.class);
            }
            //查询任务对应参数
            KettleJobParamVO query = new KettleJobParamVO();
            query.setJobId(jobModel.getJobId());

            List<KettleJobParamVO> list = kettleRepositoryService.queryKettleJobParam(query);
            if (list==null)     return;

            for(KettleJobParamVO param: list) {
                log.info("任务：{} （{}）， Kettle参数：{}", param.getJobName(), param.getJobId(), param.getKtlParamValue() );
                if(!isKettleJob(param)) {
                    continue;
                }
                Map<String, String> jobParams = new HashMap<String, String>();
                if(! StringUtils.isBlank(param.getKtlParamValue())) {
                    jobParams = JSONObject.parseObject(param.getKtlParamValue(), Map.class);
                }
                KettleRunner.run(jobParams, param.getKtlJobType(), param.getRepType(), param.getRepPath());
            }
        } catch (KettleException | CoBusinessException e) {
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
