package com.khsh.etl.kettle.kit;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: KettleFactory
 * Author:   Ejet
 * CreateDate:     2018-08-30 15:54
 * Description: kettle服务工厂，提供单实例进行调用
 * History:
 * Version: 1.0
 */
public class KettleFactory {
    private static final Logger logger = LoggerFactory.getLogger(KettleFactory.class);
    /**
     * kettle资源仓库
     */
    private static KettleDatabaseRepository repository = null;
    /**
     * kettle资源仓库map（多kettle数据源）
     */
    private static Map<String, KettleDatabaseRepository> repositoryMap = new ConcurrentHashMap<>();
    /**
     * kettle资源配置信息
     */
    private static KettleRepositoryConfig repositoryConfig = null;
    private static boolean hasInit = false;
    private KettleFactory() {}

    /**
     * 初始化环境变量
     */
    public static void initEnv() throws KettleException {
        //初始化
        if (!KettleEnvironment.isInitialized()) {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
        }
    }

    /**
     * 释放连接的kettle资源库
     */
    public static void destroyAll() {
        if(repository!=null){
            repository.disconnect();
            logger.info(repository.getName()+"释放资源库连接成功!");
        }
    }

    /**
     * 连接资源(可支持多库,目前只支持单库)
     */
    public synchronized static KettleDatabaseRepository connectRepository(KettleRepositoryConfig config) throws KettleException {
        initEnv();
        if(config==null) {
            throw new KettleException("kettle资源库配置信息为null???");
        }
        if(repository!=null && repository.isConnected()) {
            return repository;
        }
        repositoryConfig = config;
        //创建资源库对象，此时的对象还是一个空对象
        repository = new KettleDatabaseRepository();
        //创建资源库数据库对象，类似我们在spoon里面创建资源库
        DatabaseMeta dataMeta = new DatabaseMeta(config.getName(), config.getType(), config.getAccess(), config.getHost(), config.getDb(), config.getPort(),
                config.getUser(), config.getPassword());
        //资源库元对象,名称参数，id参数，描述等可以随便定义
        KettleDatabaseRepositoryMeta kettleDatabaseMeta =
                new KettleDatabaseRepositoryMeta(config.getId(), config.getName(), config.getDescription(), dataMeta);
        //给资源库赋值
        repository.init(kettleDatabaseMeta);
        //连接资源库
        repository.connect("admin","admin");
        hasInit = true;
        return repository;
    }

    /**
     * 获取资源库连接信息
     *
     * @return
     * @throws KettleException
     */
    public synchronized static KettleDatabaseRepository getRepository() throws KettleException {
        if(repositoryConfig==null) {
            throw new KettleException("kettle资源库配置信息未初始化???");
        }
        if(repository!=null && repository.isConnected()) {
            return repository;
        }
        return connectRepository(repositoryConfig);
    }


}
