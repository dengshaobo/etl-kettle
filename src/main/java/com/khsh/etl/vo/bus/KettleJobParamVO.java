package com.khsh.etl.vo.bus;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: KettleJobParamVO
 * Author:   Ejet
 * CreateDate:     2018-09-13 9:33
 * Description: kettle
 * History:
 * Version: 1.0
 */
public class KettleJobParamVO {

    /**  任务ID  */
    private String jobId;
    /**  任务名称  */
    private String jobName;
    /**  参数名称  */
    private String paramName;
    /**  参数类型  */
    private String paramType;

    /**  kette作业id  */
    private String ktlJobUuid;
    /**  kette作业类型: KJBKTR  */
    private String ktlJobType;
    /**  作业名称  */
    private String ktlJobName;
    /**  资源类型: DBFILE  */
    private String repType;
    /**  资源路径  */
    private String repPath;
    /**  kettle参数值，采用json，key：value键值对  */
    private String ktlParamValue;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getKtlJobUuid() {
        return ktlJobUuid;
    }

    public void setKtlJobUuid(String ktlJobUuid) {
        this.ktlJobUuid = ktlJobUuid;
    }

    public String getKtlJobType() {
        return ktlJobType;
    }

    public void setKtlJobType(String ktlJobType) {
        this.ktlJobType = ktlJobType;
    }

    public String getKtlJobName() {
        return ktlJobName;
    }

    public void setKtlJobName(String ktlJobName) {
        this.ktlJobName = ktlJobName;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getRepPath() {
        return repPath;
    }

    public void setRepPath(String repPath) {
        this.repPath = repPath;
    }
    public void setKtlParamValue(String ktlParamValue) {
        this.ktlParamValue=ktlParamValue;
    }

    public String getKtlParamValue(){
        return ktlParamValue;
    }
}
