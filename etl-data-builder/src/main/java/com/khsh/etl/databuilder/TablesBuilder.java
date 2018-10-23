package com.khsh.etl.databuilder;

import com.ejet.comm.db.CoDbConfig;
import com.ejet.comm.db.CoDbFactory;
import com.ejet.comm.utils.io.IOUtils;
import com.khsh.etl.databuilder.db.DbContext;
import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;
import com.khsh.etl.databuilder.db.meta.DbDatabaseMeta;
import com.khsh.etl.databuilder.db.meta.DbTableMeta;
import com.khsh.etl.databuilder.utils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: TablesBuilder
 * Author:   Ejet
 * CreateDate:     2018-09-06 16:05
 * Description: 数据表构建类
 * History:
 * Version: 1.0
 */
public class TablesBuilder {

    private CoDbConfig srcDB = null;
    private CoDbConfig destDB = null;
    private DbContext src = new DbContext();
    private DbContext dest = new DbContext();

    public TablesBuilder(CoDbConfig src, CoDbConfig dest) {
        this.srcDB = src;
        this.destDB = dest;
    }

    /**
     * 初始并校验库连接
     */
    private void initConnection(CoDbConfig config, DbContext ctx) throws SQLException {
        Connection connection = CoDbFactory.getConnection(config.getDriverClassName(), config.getUrl(), config.getUsername(), config.getPassword());
        if(connection==null) {
            throw new RuntimeException("数据库连接失败!" + config.getUrl());
        }
        String catalog = null;
        String schema = null;
        DbTypeEnum dbType = DbUtils.formatDbTypeEnum(connection);
        if(dbType== DbTypeEnum.ORACLE) {
            catalog = DbUtils.getCatalogByURL(config.getUrl());
        } else {
            catalog = connection.getCatalog();
            schema = connection.getSchema();
        }
        DbDatabaseMeta databaseSrc = new DbDatabaseMeta(dbType, config.getDriverClassName(),
                config.getUrl(), config.getUsername(), config.getPassword(), catalog, schema);
        ctx.setDatabase(databaseSrc);
        ctx.setConnection(connection);
    }


    /**
     * 设置新增表及字段信息，如果表名称为空，则字段针对所有表
     * @param addtion
     */
    public void setAddition(List<DbTableMeta> addtion) {
        dest.setAddtion(addtion);
    }

    /**
     * 设置忽略同步表名字
     * @param ignoreTables
     */
    public void setIgnoreTables(List<String> ignoreTables) {
        src.setIgnoreTables(ignoreTables);
    }

    /**
     * 是否开启表前缀，如果开启就增加，默认为false
     */
    public void setHasPrefix(boolean hasPrefix) {
        src.setHasPrefix(hasPrefix);
    }

    /**
     * 构建表
     * @param isDrop
     * @throws Exception
     */
    public void build(boolean isDrop) throws Exception {
        try {
            initConnection(srcDB, src);
            initConnection(destDB, dest);

            TablesTrans trans = new TablesTrans(src, dest);
            trans.execute(isDrop);

        } finally {

            IOUtils.closeQuietly(src.getConnection());
            IOUtils.closeQuietly(dest.getConnection());
        }
    }


}
