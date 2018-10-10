package com.khsh.etl.kettle.kit;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: KettleRepositoryConfig
 * Author:   Ejet
 * CreateDate:     2018-09-07 16:52
 * Description: 连接kettle资源库配置信息, 配置信息, 具体可查看kettle根目录/.kettle/repositories.xml
 * History:
 * Version: 1.0
 */
public class KettleRepositoryConfig {

    String name;
    String type;
    String access;
    String host;
    String db;
    String port;
    String user;
    String password;
    String id;
    String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
