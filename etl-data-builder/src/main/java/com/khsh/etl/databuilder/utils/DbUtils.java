package com.khsh.etl.databuilder.utils;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbUtils
 * Author:   Ejet
 * CreateDate:     2018-09-11 9:12
 * Description: 工具类
 * History:
 * Version: 1.0
 */
public class DbUtils {
    private static final String POSTGRES_PART = "postgresql";
    private static final String MYSQL_PART = "mysql";
    private static final String ORACLE_PART = "oracle";
    // private static final String MSSQL_PART = "mssqlserver4";
    private static final String MSSQL_PART = "microsoft";
    private static final String HSQL_PART = "hsql";
    private static final String H2_PART = "h2";
    private static final String SYBASE_SQLANY_PART = "sql anywhere";
    private static final String SQLITE_PART = "sqlite";

    /**
     * 根据数据库连接信息返回数据库类型
     * @param meta
     * @return
     */
    public static DbTypeEnum discoverSQLDialect(DatabaseMetaData meta) {
        DbTypeEnum dialectCode = DbTypeEnum.UNKNOWN;

        try {

            String dbName = meta.getDatabaseProductName().toLowerCase();

            if (dbName.indexOf(POSTGRES_PART) != -1) {
                return DbTypeEnum.POSTGRES;
            } else if (dbName.indexOf(MYSQL_PART) != -1) {
                return DbTypeEnum.MYSQL;
            } else if (dbName.indexOf(ORACLE_PART) != -1) {
                return DbTypeEnum.ORACLE;
            } else if (dbName.indexOf(MSSQL_PART) != -1) {
                return DbTypeEnum.SQLSERVER;
            } else if (dbName.indexOf(HSQL_PART) != -1) {
                return DbTypeEnum.HSQL;
            } else if (dbName.indexOf(H2_PART) != -1) {
                return DbTypeEnum.H2;
            } else if (dbName.indexOf(SYBASE_SQLANY_PART) != -1) {
                return DbTypeEnum.SYBASE_SQLANYWHERE;
            } else if (dbName.indexOf(SQLITE_PART) != -1) {
                return DbTypeEnum.SQLITE;
            } else {
                return DbTypeEnum.UNKNOWN;
            }
        } catch (SQLException sqle) {
            // we can't do much here
        }

        return dialectCode;
    }

    /**
     * 获取数据库连接类型
     * @return 数据库类型
     * @throws SQLException
     */
    public static DbTypeEnum formatDbTypeEnum(java.sql.Connection connection) throws SQLException {
        DbTypeEnum type = null;
        if(connection==null) {
            return type;
        }
        String name = connection.getMetaData().getDriverName().toUpperCase();
        if(name.contains(MYSQL_PART.toUpperCase())) {
            type = DbTypeEnum.MYSQL;
        }else if(name.contains(MSSQL_PART.toUpperCase())) {
            type = DbTypeEnum.SQLSERVER;
        }else if(name.contains(ORACLE_PART.toUpperCase())) {
            type = DbTypeEnum.ORACLE;
        }else if(name.contains(SYBASE_SQLANY_PART.toUpperCase())) {
            type = DbTypeEnum.SYBASE_SQLANYWHERE;
        }
        return type;
    }

    /**
     * 根据URL地址返回库名信息
     *
     * @param dbURL
     * @return
     */
    public static String getCatalogByURL(String dbURL) {
        String catalog = null;
        if(dbURL==null || "".equals(dbURL.trim())) {
            return catalog;
        }
        if(dbURL.toLowerCase().contains("jdbc:oracle")) {
            if(!dbURL.contains("@")) {
                return catalog;
            }
            catalog = dbURL.split("@")[1];
            int index = catalog.lastIndexOf("/");
            catalog = index==-1 ? catalog : catalog.substring(index+1, catalog.length());
            index = catalog.lastIndexOf(":");
            catalog = index==-1 ? catalog : catalog.substring(index+1, catalog.length());
            index = catalog.indexOf(".");
            catalog = index==-1 ? catalog : catalog.substring(0, index);
        }
        return catalog;
    }

    /**
     * 结果集转换json数组
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public JsonArray resultSetToJsonArray(ResultSet rs) throws SQLException {
        // json数组
        JsonArray array = new JsonArray();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JsonObject jsonObj = new JsonObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.addProperty(columnName, value);
            }
            array.add(jsonObj);
        }
        return array;
    }

    /**
     * 结果集转json字符串
     */
    public String resultSetToJsonString(ResultSet rs) throws SQLException {
        return resultSetToJsonArray(rs).toString();
    }


    /**
     * 结果集转对象数组
     */
    public <T> List<T> resultSetToListBean(ResultSet rs, final Class<T> clazz) throws SQLException {
        String jsonStr = resultSetToJsonArray(rs).toString();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, new TypeToken<List<T>>(){}.getType());
    }





}
