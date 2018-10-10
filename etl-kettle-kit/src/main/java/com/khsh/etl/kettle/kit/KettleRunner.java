package com.khsh.etl.kettle.kit;

import org.pentaho.di.base.AbstractMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobExecutionConfiguration;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransExecutionConfiguration;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.cluster.TransSplitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: KettleRunner
 * Author:   Ejet
 * CreateDate:     2018-08-30 13:59
 * Description: kettle运行工具类
 * History:
 * Version: 1.0
 */
public class KettleRunner {

    private static Logger log = LoggerFactory.getLogger("KettleRunner");

    /**
     *  执行job
     */
    public static boolean run(Map<String,String> initKettleParam, String ktlJobType, String repType, String repPath) throws KettleException {
        String uuid = UUID.randomUUID().toString();
        AbstractMeta meta = KettleRepositoryLoader.load(ktlJobType, repType, repPath);
        if (meta == null) {
            throw new KettleException("查找资源失败!!!");
        }
        if (meta instanceof JobMeta) {
            runJob(initKettleParam, (JobMeta)meta);
        } else if (meta instanceof TransMeta) {
            runTrans(initKettleParam, (TransMeta)meta);
        }
        return true;
    }

    /**
     *  执行job
     * @return
     */
    public static boolean runJob(Map<String,String> initKettleParam, JobMeta jobMeta) {
        String uuid = UUID.randomUUID().toString();
        StringBuffer msg = new StringBuffer();
        msg.append("====== runJob =======").append(uuid);
        log.info(msg.toString());
        Job job = new Job(null, jobMeta);
        //初始化job参数，脚本中获取参数值：${variableName}
        if(initKettleParam!=null) {
            for (String variableName : initKettleParam.keySet()) {
                job.setVariable(variableName, initKettleParam.get(variableName));
            }
        }
        job.start();
        job.waitUntilFinished();
        if (job.getErrors() > 0) {
            msg.append(" failed!!!!");
        }else{
            msg.append(" success!!!!");
        }
        log.info(msg.toString());
        return true;
    }


    /**
     * 执行转换
     *
     * @return
     */
    public static boolean runTrans(Map<String,String> initKettleParam, TransMeta transMeta) throws KettleException {
        String uuid = UUID.randomUUID().toString();
        StringBuffer msg = new StringBuffer();
        msg.append("====== runTrans =======").append(uuid);
        log.info(msg.toString());
        Trans trans = new Trans(transMeta);
        //初始化trans参数，脚本中获取参数值：${variableName}
        if(initKettleParam!=null) {
            for (String variableName : initKettleParam.keySet()) {
                trans.setVariable(variableName, initKettleParam.get(variableName));
            }
        }
        //执行转换
        trans.execute(null);
        //等待转换执行结束
        trans.waitUntilFinished();
        if (trans.getErrors() > 0) {
            msg.append(" failed!!!!");
        }else{
            msg.append(" success!!!!");
        }
        log.info(msg.toString());
        return true;
    }


    /**
     * 集群运行任务
     * @return
     * @throws KettleException
     */
    public static TransSplitter runTransCluster(Map<String,String> initKettleParam, TransMeta transMeta) throws KettleException {
        Trans trans = null;
        log.info(" ====== 集群runTransCluster =======");
        //转换
        trans = new Trans(transMeta);
        for (String variableName : initKettleParam.keySet()) {
            trans.setVariable(variableName, initKettleParam.get(variableName));
        }
        // 设置执行模式
        TransExecutionConfiguration config = new TransExecutionConfiguration();
        ////设置集群为true
        config.setExecutingClustered(true);
        //设置local为false
        config.setExecutingLocally(false);
        config.setExecutingRemotely(false);
        config.setClusterPosting(true);
        //设置准备执行为true
        config.setClusterPreparing(true);
        //设置开始执行为true，否则需要到carte的监控页面上点击开始执行
        config.setClusterStarting(true);
        TransSplitter transSplitter = Trans.executeClustered(transMeta, config);
        log.info(" ====== 集群runTransCluster =======" + transSplitter.getCarteObjectMap());
        return transSplitter;
    }

    /**
     * 集群运行任务
     * @return
     * @throws KettleException
     */
    public static TransSplitter runJobCluster(Map<String,String> initKettleParam, JobMeta jobMeta) throws KettleException {
        Trans trans = null;
        log.info(" ====== 集群runJobCluster =======");
        //转换
        Job job = new Job(null, jobMeta);
        //初始化job参数，脚本中获取参数值：${variableName}
        if(initKettleParam!=null) {
            for (String variableName : initKettleParam.keySet()) {
                job.setVariable(variableName, initKettleParam.get(variableName));
            }
        }
        JobExecutionConfiguration config  = new JobExecutionConfiguration();
        ////设置集群为true
        // config.setExecutingClustered(true);
        // //设置local为false
        // config.setExecutingLocally(false);
        // config.setExecutingRemotely(false);
        // config.setClusterPosting(true);
        // //设置准备执行为true
        // config.setClusterPreparing(true);
        // //设置开始执行为true，否则需要到carte的监控页面上点击开始执行
        // config.setClusterStarting(true);
        // job.setExecutingServer();
        // TransSplitter transSplitter = Trans.executeClustered(transMeta, config);
        // System.out.println(transSplitter.getCarteObjectMap());
        return null;
    }



}
