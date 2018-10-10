package com.khsh.etl.comm;

import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: SchedulerListenerImpl
 * Author:   Ejet
 * CreateDate:     2018-09-12 17:00
 * Description: 任务调度监听器
 * History:
 * Version: 1.0
 */
public class SchedulerListenerImpl implements SchedulerListener {

    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("====== 1、jobScheduled()  ======");
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("====== 2、jobUnscheduled()  ======");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("====== 3、triggerFinalized()  ======");
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("====== 4、triggerPaused()  ======");
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        System.out.println("====== 5、triggersPaused()  ======");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println("====== 6、triggerResumed()  ======");
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        System.out.println("====== 7、triggersResumed()  ======");
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("====== 8、jobAdded()  ======");
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        System.out.println("====== 9、jobDeleted()  ======");
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        System.out.println("====== 10、jobPaused()  ======");
    }

    @Override
    public void jobsPaused(String jobGroup) {
        System.out.println("====== 11、jobsPaused()  ======");
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        System.out.println("====== 12、jobResumed()  ======");
    }

    @Override
    public void jobsResumed(String jobGroup) {
        System.out.println("====== 13、jobsResumed()  ======");
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        System.out.println("====== 14、schedulerError()  ======");
    }

    @Override
    public void schedulerInStandbyMode() {
        System.out.println("====== 15、schedulerInStandbyMode()  ======");
    }

    @Override
    public void schedulerStarted() {
        System.out.println("====== 16、schedulerStarted()  ======");
    }

    @Override
    public void schedulerStarting() {
        System.out.println("====== 17、schedulerStarting()  ======");
    }

    @Override
    public void schedulerShutdown() {
        System.out.println("====== 18、schedulerShutdown()  ======");
    }

    @Override
    public void schedulerShuttingdown() {
        System.out.println("====== 19、schedulerShuttingdown()  ======");
    }

    @Override
    public void schedulingDataCleared() {
        System.out.println("====== 20、schedulingDataCleared()  ======");
    }
}
