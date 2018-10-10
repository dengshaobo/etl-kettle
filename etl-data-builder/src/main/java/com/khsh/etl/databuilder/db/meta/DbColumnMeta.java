package com.khsh.etl.databuilder.db.meta;

import com.khsh.etl.databuilder.db.dialect.DbColumnTypeEnum;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbColMeta
 * Author:   Ejet
 * CreateDate:     2018-08-23 15:44
 * Description: 表字段描述信息
 * History:
 * Version: 1.0
 */
public class DbColumnMeta {
    /**
     * 表字段名称
     */
    private String columnName;
    /**
     * 表字段类型
     */
    private DbColumnTypeEnum columnType;
    /**
     * 是否为空
     */
    private boolean isNull = true;
    /**
     * 表字段类型脚本
     */
    private String columnTypeShell;
    /**
     * 表字段长度
     */
    private Integer length = null;
    /**
     * 表字段小数点位数
     */
    private Integer dataScale;
    /**
     * 列顺序ID
     */
    private Integer colOrder;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DbColumnTypeEnum getColumnType() {
        return columnType;
    }

    public void setColumnType(DbColumnTypeEnum columnType) {
        this.columnType = columnType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

	public Integer getDataScale() {
		return dataScale;
	}

	public void setDataScale(Integer dataScale) {
		this.dataScale = dataScale;
	}

	public String getColumnTypeShell() {
		return columnTypeShell;
	}

	public void setColumnTypeShell(String columnTypeShell) {
		this.columnTypeShell = columnTypeShell;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

    public Integer getColOrder() {
        return colOrder;
    }

    public void setColOrder(Integer colOrder) {
        this.colOrder = colOrder;
    }
}
