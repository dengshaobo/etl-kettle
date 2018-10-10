package com.khsh.etl.databuilder.vo;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: TableVO
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:14
 * Description: 表信息
 * History:
 * Version: 1.0
 */
public class TableVO {
    String tableCatalog;
    String tableSchema;
    String tableName;
    String tableType;
    public String getTableCatalog() {
        return tableCatalog;
    }
    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }
    public String getTableSchema() {
        return tableSchema;
    }
    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableType() {
        return tableType;
    }
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
