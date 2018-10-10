package com.khsh.etl.databuilder.db;

import com.khsh.etl.databuilder.db.meta.DbDatabaseMeta;
import com.khsh.etl.databuilder.db.meta.DbTableMeta;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbContext
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:11
 * Description: 数据库、表、字段等信息
 * History:
 * Version: 1.0
 */
public class DbContext {

    private DbDatabaseMeta database;
    private List<DbTableMeta> tables;
    private Connection connection;

    /**
     * 新增表及字段信息，如果表名称为空，则字段针对所有表
     */
    private List<DbTableMeta> addtion;
    /**
     * 忽略同步表
     */
    private List<String> ignoreTables = new ArrayList<>();
    /**
     * 是否增加表前缀
     */
    private boolean hasPrefix = false;
    /**
     * 表字段前缀，如果没有设置，则为库名称
     */
    private String tablePrefix = null;

    public List<DbTableMeta> getAddtion() {
        return addtion;
    }

    public void setAddtion(List<DbTableMeta> addtion) {
        this.addtion = addtion;
    }

    public DbDatabaseMeta getDatabase() {
        return database;
    }
    public void setDatabase(DbDatabaseMeta database) {
        this.database = database;
    }
    public List<DbTableMeta> getTables() {
        return tables;
    }
    public void setTables(List<DbTableMeta> tables) {
        this.tables = tables;
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<String> getIgnoreTables() {
        return ignoreTables;
    }

    public void setIgnoreTables(List<String> ignoreTables) {
        this.ignoreTables = ignoreTables;
    }

    public void addIgnoreTable(String ignoreTableName) {
        ignoreTables.add(ignoreTableName);
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public boolean isHasPrefix() {
        return hasPrefix;
    }

    public void setHasPrefix(boolean hasPrefix) {
        this.hasPrefix = hasPrefix;
    }
}
