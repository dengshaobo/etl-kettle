package com.khsh.etl.databuilder.db;

import com.khsh.etl.databuilder.db.dialect.DbColumnTypeEnum;
import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;
import com.khsh.etl.databuilder.db.meta.*;
import com.khsh.etl.databuilder.utils.DbUtils;
import com.khsh.etl.databuilder.vo.ColumnVO;
import com.khsh.etl.databuilder.vo.TableVO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbHelper
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:17
 * Description: 数据库帮助类
 * History:
 * Version: 1.0
 */
public class DbHelper {
    final static String TYPES_TABLE = "TABLE";
    /**
     * 查询所有表
     * @throws Exception
     */
    public static List<TableVO> queryTables(java.sql.Connection connection, DbDatabaseMeta database, List<String> ignoreTables) throws Exception {
        DbTypeEnum type = DbUtils.formatDbTypeEnum(connection);
        if(type==null) {
            throw new RuntimeException("数据库类型不可识别!");
        }
        QueryRunner qr = new QueryRunner();
        //查询
        List<TableVO> tables = qr.query(connection, DbMetaHelper.getQueryTablesSQL(type, database, ignoreTables),
                new BeanListHandler<TableVO>(TableVO.class));
        return tables;
    }

    /**
     * 查询表中所有列
     */
    public static List<ColumnVO> queryColumns(java.sql.Connection connection, DbDatabaseMeta database, String tableName) throws Exception {
        DbTypeEnum type = DbUtils.formatDbTypeEnum(connection);
        List<ColumnVO> columns = new ArrayList<>();
        QueryRunner qr = new QueryRunner();
        //查询
        columns = qr.query(connection, DbMetaHelper.getQueryTableColumnsSQL(type, database, tableName), new BeanListHandler<ColumnVO>(ColumnVO.class));
        return columns;
    }

    /**
     * 判断表是否存在
     * @return
     * @throws SQLException
     */
    public static boolean existTable(java.sql.Connection connection, DbDatabaseMeta database, String tableName) throws SQLException {
        boolean isExist = false;
        DbTypeEnum type = DbUtils.formatDbTypeEnum(connection);
        if(DbTypeEnum.ORACLE==type) {
            //查询
            QueryRunner qr = new QueryRunner();
            BigDecimal count = qr.query(connection, DbMetaHelper.getQueryTableExistSQL(type, database, tableName), new ScalarHandler<BigDecimal>());
            return count.longValue()>0;
        } else if(DbTypeEnum.MYSQL==type) {
            QueryRunner qr = new QueryRunner();
            String count = qr.query(connection, DbMetaHelper.getQueryTableExistSQL(type, database, tableName), new ScalarHandler<String>());
            if(count!=null) {
                return true;
            }
            return false;
        }
        return isExist;
    }

    /**
     * 删除表
     * @throws SQLException
     */
    public static boolean dropTable(java.sql.Connection connection, DbDatabaseMeta database, String tableName) throws SQLException {
        //查询
        if(!existTable(connection, database, tableName) ) { //不存在,直接返回
            return true;
        }
        DbTypeEnum type = DbUtils.formatDbTypeEnum(connection);
        StringBuffer buff = new StringBuffer();
        if(DbTypeEnum.ORACLE==type) {
            buff.append("DROP TABLE ").append(tableName).append("");
        } else if(DbTypeEnum.MYSQL==type) {
            buff.append("DROP TABLE IF EXISTS `").append(tableName).append("`;");
        }
        java.sql.Statement statement = connection.createStatement();
        boolean rs = statement.execute(buff.toString());
        statement.close();
        return rs;
    }


    /**
     * 建表
     */
    public static boolean createTable(java.sql.Connection connection, DbDatabaseMeta database, boolean isDrop, String tableName, String createSql) throws SQLException {
        if(!isDrop && existTable(connection, database, tableName)) { //不需要删除，并且已经存在
            return true;
        }
        if(isDrop) {
            boolean r = dropTable(connection, database, tableName);
            System.out.println(tableName + "删除执行结果:" + r );
        }
        java.sql.Statement statement = connection.createStatement();
        boolean rs = statement.execute(createSql);
        statement.close();
        System.out.println(tableName + "建表执行结果:" + rs );
        return rs;
    }

    /**
     * 将数据库查询字段进行转换
     */
    public static DbColumnMeta convertColumnVO(ColumnVO columnVO) {
        DbColumnMeta meta = new DbColumnMeta();
        meta.setColumnName(columnVO.getColumnName());
        meta.setColumnType(DbColumnTypeEnum.getColTypeByname(columnVO.getDataType())); //获取字典类型
        meta.setNull((columnVO.getIsnullable()!=null && columnVO.getIsnullable().equalsIgnoreCase("N")) ? false : true);
        meta.setLength(columnVO.getLength());
        meta.setDataScale(columnVO.getDataScale());
        meta.setColOrder(columnVO.getColOrder());
        return meta;
    }

    /**
     * 获取表所有字段信息
     * @return
     */
    public static List<DbColumnMeta> getColumnMeta(DbContext ctx, String tableName) throws Exception {
        //获取表字段
        List<ColumnVO> columns = queryColumns(ctx.getConnection(), ctx.getDatabase(), tableName);
        List<DbColumnMeta> columnList = new ArrayList<>();
        if(columns!=null && columns.size()>0) {
            for(ColumnVO item : columns) {
                DbColumnMeta col = convertColumnVO(item);
                if(col.getColumnType()==null) {
                    throw new RuntimeException("[表]" + tableName + ", 字段: " + item.getColumnName() + ", " + item.getDataType() + ", 格式不可识别!");
                }
                columnList.add(col);
            }
        }
        return columnList;
    }

    public static final boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    // GENERAL_PUNCTUATION 判断中文的“号
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


}
