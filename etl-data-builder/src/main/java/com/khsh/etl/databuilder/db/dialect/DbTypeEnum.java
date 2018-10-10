package com.khsh.etl.databuilder.db.dialect;
/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbType
 * Author:   Ejet
 * CreateDate:     2018-08-23 13:58
 * Description: 数据库类型
 * History:
 * Version: 1.0
 */
public enum DbTypeEnum {

    //     // private static final String MSSQL_PART = "mssqlserver4";
    //     private static final String MSSQL_PART = "microsoft";
    //     private static final String SYBASE_SQLANY_PART = "sql anywhere";

    UNKNOWN("UNKNOWN", "UNKNOWN数据库"),
    SQLSERVER("sqlserver", "SqlServer数据库"),
    ORACLE("oracle", "oracle数据库"),
    MYSQL("mysql", "MySQL数据库"),
    POSTGRES("postgresql", "POSTGRES数据库"),
    HSQL("hsql", "HSQL数据库"),
    SYBASE_SQLANYWHERE("sql anywhere", "SYBASE_SQLANYWHERE数据库"),
    SQLITE("sqlite", "sqlite数据库"),
	H2("h2", "enum数据库");

    private String name;
    private String desc;

    private DbTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }


    public static DbTypeEnum getTypeName(String name) {
        if(name == null || "".equals(name.trim())){
            return null;
        }
        for (DbTypeEnum c : DbTypeEnum.values()) {
            if(c.getName().equals(name.trim().toLowerCase())){
                return c;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
