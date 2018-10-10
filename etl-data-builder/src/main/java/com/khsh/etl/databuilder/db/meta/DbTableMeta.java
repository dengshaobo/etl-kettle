package com.khsh.etl.databuilder.db.meta;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbTableMeta
 * Author:   Ejet
 * CreateDate:     2018-08-24 10:42
 * Description: 数据库表同步信息
 * History:
 * Version: 1.0
 */
public class DbTableMeta {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableDesc;
    /**
     * 表字段信息
     */
    private List<DbColumnMeta> columns = new ArrayList<>();
    /**
     * 创建表脚本
     */
    private String tableCreateShell;

    public DbTableMeta(String tableName, List<DbColumnMeta> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DbColumnMeta> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumnMeta> columns) {
        this.columns = columns;
    }

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getTableCreateShell() {
		return tableCreateShell;
	}

	public void setTableCreateShell(String tableCreateShell) {
		this.tableCreateShell = tableCreateShell;
	}
    
	
    
}
