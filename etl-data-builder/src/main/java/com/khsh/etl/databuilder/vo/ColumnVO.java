package com.khsh.etl.databuilder.vo;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: ColumnVO
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:16
 * Description: 表字段信息
 * History:
 * Version: 1.0
 */
public class ColumnVO {
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列描述
     */
    private String columnComment;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 是否为空, Y: 是， N：否
     */
    private String isnullable;
    /**
     * 字段长度
     */
    private int length;
    /**
     * 字段小数点位数
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
    public String getColumnComment() {
        return columnComment;
    }
    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public Integer getDataScale() {
        return dataScale;
    }
    public void setDataScale(Integer dataScale) {
        this.dataScale = dataScale;
    }
    public String getIsnullable() {
        return isnullable;
    }
    public void setIsnullable(String isnullable) {
        this.isnullable = isnullable;
    }
    public int getLength() {
        return length;
    }
    public Integer getColOrder() {
        return colOrder;
    }
    public void setColOrder(Integer colOrder) {
        this.colOrder = colOrder;
    }
}
