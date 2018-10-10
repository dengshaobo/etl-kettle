package com.khsh.etl.databuilder.db;

import com.khsh.etl.databuilder.db.meta.DbColumnMeta;
import com.khsh.etl.databuilder.db.dialect.DbColumnTypeEnum;
import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbColumnConverter
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:22
 * Description: 字段类型转换器
 * History:
 * Version: 1.0
 */
public class DbColumnConverter {
    /**
     * sqlserver转为mysql字段
     */
    public static Map<String, String> sqlServer2Mysql = new HashMap<>();
    /**
     * sqlserver转为oracle字段
     */
    public static Map<String, String> sqlServer2Oracle = new HashMap<>();
    /**
     * oracle转为mysql字段
     */
    public static Map<String, String> Oracle2Mysql = new HashMap<>();

    static {
        sqlServer2Mysql.put("bigint", "bigint");
        sqlServer2Mysql.put("binary", "varbinary"); //mysql类型中binary最大长度为255所以变更
        sqlServer2Mysql.put("bit", "tinyint");
        sqlServer2Mysql.put("tinyint", "tinyint");
        sqlServer2Mysql.put("char", "char");
        sqlServer2Mysql.put("nchar", "char");
        sqlServer2Mysql.put("varchar", "varchar");
        sqlServer2Mysql.put("nvarchar", "varchar");
        sqlServer2Mysql.put("sysname", "varchar");

        sqlServer2Mysql.put("date", "date");
        sqlServer2Mysql.put("datetime", "datetime");
        sqlServer2Mysql.put("datetime2", "datetime");
        sqlServer2Mysql.put("datetimeoffset", "datetime");
        sqlServer2Mysql.put("smalldatetime", "datetime");
        sqlServer2Mysql.put("time", "time");
        sqlServer2Mysql.put("timestamp", "TinyBlob"); //由于sqlserver存储的为二进制8位时间戳，所以需要转为二进制，否则存储失败

        sqlServer2Mysql.put("decimal", "decimal");
        sqlServer2Mysql.put("float", "float");
        sqlServer2Mysql.put("int", "int");
        sqlServer2Mysql.put("money", "decimal");
        sqlServer2Mysql.put("numeric", "decimal");
        sqlServer2Mysql.put("real", "float");
        sqlServer2Mysql.put("smallint", "smallint");
        sqlServer2Mysql.put("smallmoney", "decimal");

        sqlServer2Mysql.put("uniqueidentifier", "varchar");

        sqlServer2Mysql.put("varbinary", "varbinary");//图像二进制

        sqlServer2Mysql.put("ntext", "mediumtext");
        sqlServer2Mysql.put("text", "text");
        sqlServer2Mysql.put("xml", "mediumtext");
        //sqlServer2Mysql.put("sql_variant", "text"); kettle数据同步不支持，所以去掉

        sqlServer2Mysql.put("geography", "LONGBLOB");
        sqlServer2Mysql.put("geometry", "LONGBLOB");
        sqlServer2Mysql.put("hierarchyid", "LONGBLOB");
        sqlServer2Mysql.put("image", "BLOB");


        // ===============================  sqlserver转oracle  ===================================================
        sqlServer2Oracle.put("bigint", "NUMBER");
        sqlServer2Oracle.put("binary", "BLOB"); //图片
        sqlServer2Oracle.put("bit", "NUMBER");
        sqlServer2Oracle.put("tinyint", "NUMBER");
        sqlServer2Oracle.put("char", "CHAR");
        sqlServer2Oracle.put("nchar", "NCHAR");
        sqlServer2Oracle.put("varchar", "NVARCHAR2");
        sqlServer2Oracle.put("nvarchar", "NVARCHAR2");
        sqlServer2Oracle.put("sysname", "NVARCHAR2");

        sqlServer2Oracle.put("date", "DATE");
        sqlServer2Oracle.put("datetime", "TIMESTAMP");
        sqlServer2Oracle.put("datetime2", "TIMESTAMP");
        sqlServer2Oracle.put("datetimeoffset", "TIMESTAMP (9) WITH TIME ZONE"); //保留9位最大值
        sqlServer2Oracle.put("smalldatetime", "DATE");
        sqlServer2Oracle.put("time", "VARCHAR2");
        sqlServer2Oracle.put("timestamp", "RAW");

        sqlServer2Oracle.put("decimal", "NUMBER");
        sqlServer2Oracle.put("float", "FLOAT");
        sqlServer2Oracle.put("int", "NUMBER");
        sqlServer2Oracle.put("money", "NUMBER");
        sqlServer2Oracle.put("numeric", "NUMBER");
        sqlServer2Oracle.put("real", "REAL");
        sqlServer2Oracle.put("smallint", "NUMBER");
        sqlServer2Oracle.put("smallmoney", "NUMBER");

        sqlServer2Oracle.put("uniqueidentifier", "VARCHAR");

        sqlServer2Oracle.put("varbinary", "BLOB");//图像二进制

        sqlServer2Oracle.put("ntext", "NCLOB");
        sqlServer2Oracle.put("text", "CLOB");
        sqlServer2Oracle.put("xml", "CLOB");

        sqlServer2Oracle.put("geography", "BLOB");
        sqlServer2Oracle.put("geometry", "BLOB");
        sqlServer2Oracle.put("hierarchyid", "BLOB");
        sqlServer2Oracle.put("image", "BLOB");


        // ===============================  oracle转mysql  ===================================================
        Oracle2Mysql.put("char", "BLOB");
        Oracle2Mysql.put("nchar", "BLOB");
        Oracle2Mysql.put("varchar2", "BLOB");
        Oracle2Mysql.put("varchar", "BLOB");
        Oracle2Mysql.put("nvarchar2", "BLOB");
        Oracle2Mysql.put("clob", "BLOB");
        Oracle2Mysql.put("nclob", "BLOB");
        Oracle2Mysql.put("long", "BLOB");
        Oracle2Mysql.put("number", "BLOB");
        Oracle2Mysql.put("binary_float", "BLOB");
        Oracle2Mysql.put("binary_double", "BLOB");
        Oracle2Mysql.put("TIMESTAMP", "BLOB");
        Oracle2Mysql.put("BLOB", "BLOB");
        Oracle2Mysql.put("BFILE", "BLOB");
        Oracle2Mysql.put("RAW", "BLOB");
        Oracle2Mysql.put("LONG RAW", "BLOB");
        Oracle2Mysql.put("ROWID", "BLOB");
        Oracle2Mysql.put("CHARACTER", "BLOB");
        Oracle2Mysql.put("CHARACTER VARYING", "BLOB");
        Oracle2Mysql.put("CHAR VARYING", "BLOB");
        Oracle2Mysql.put("NATIONAL CHARACTER", "BLOB");
        Oracle2Mysql.put("NATIONAL CHAR", "BLOB");
        Oracle2Mysql.put("NATIONAL CHARACTER VARYING", "BLOB");
        Oracle2Mysql.put("NATIONAL CHAR VARYING", "BLOB");
        Oracle2Mysql.put("NCHAR VARYING", "BLOB");
        Oracle2Mysql.put("NUMERIC", "BLOB");
        Oracle2Mysql.put("DECIMAL", "BLOB");
        Oracle2Mysql.put("INTEGER", "BLOB");
        Oracle2Mysql.put("INT", "BLOB");
        Oracle2Mysql.put("SMALLINT", "BLOB");
        Oracle2Mysql.put("FLOAT", "BLOB");
        Oracle2Mysql.put("DOUBLE PRECISION", "BLOB");
        Oracle2Mysql.put("REAL", "BLOB");
        Oracle2Mysql.put("COMPLEX", "BLOB");
        //Oracle2Mysql.put("INTERVAL DAY TO SECOND", "BLOB");

    }



    /**
     * 表字段及类型转换
     */
    public static DbColumnMeta convertColumn(DbColumnMeta srcMeta, DbTypeEnum srcType, DbTypeEnum destType) throws Exception {

        DbColumnMeta destMeta = new DbColumnMeta();
        destMeta.setColumnName(srcMeta.getColumnName());
        destMeta.setNull(srcMeta.isNull());

        convertColumnType(srcMeta, srcType, destType, destMeta);

        destMeta.setLength(destMeta.getLength()==null ? srcMeta.getLength() : destMeta.getLength());
        destMeta.setDataScale(srcMeta.getDataScale());
        destMeta.setColumnTypeShell(getColumnTypeShell(destMeta));
        return destMeta;
    }

    /**
     * 长度设置字段
     */
    public static String getColumnTypeShell(DbColumnMeta item) {
        StringBuilder sb = new StringBuilder();
        if(isScale(item)) {
            sb.append("(").append(item.getLength()).append(", ").append(item.getDataScale()).append(")");
            return sb.toString();
        } else if(isVarchar(item)) {
            if(item.getLength()!=null) { //不为空才设置长度
                sb.append("(").append(item.getLength()).append(")");
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 判断是否需要设置精度
     *
     * @param item
     * @return
     */
    public static boolean isScale(DbColumnMeta item) {
        //oracle中无长度，代表主键，不需要设置长度
        if(item.getColumnType()== DbColumnTypeEnum.NUMBER && item.getDataScale()==null) {
            return false;
        }

        if(item.getColumnType()== DbColumnTypeEnum.DECIMAL || item.getColumnType()== DbColumnTypeEnum.NUMBER) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为需要设置长度类型
     *
     * @param item
     * @return
     */
    public static boolean isVarchar(DbColumnMeta item) {
        if(item.getColumnType()== DbColumnTypeEnum.VARCHAR || item.getColumnType()== DbColumnTypeEnum.NCHAR ||
                item.getColumnType()== DbColumnTypeEnum.CHAR || item.getColumnType()== DbColumnTypeEnum.RAW ||
                //oracle
                item.getColumnType()== DbColumnTypeEnum.NVARCHAR2 || item.getColumnType()== DbColumnTypeEnum.VARCHAR2 ||
                //mysql中DATETIME只能保存6为
                item.getColumnType()== DbColumnTypeEnum.DATETIME || item.getColumnType()== DbColumnTypeEnum.TIMESTAMP ||
                item.getColumnType()== DbColumnTypeEnum.SMALLINT || item.getColumnType()== DbColumnTypeEnum.BIGINT ||
                item.getColumnType()== DbColumnTypeEnum.TINYINT ||
                item.getColumnType()== DbColumnTypeEnum.VARBINARY ) {
            return true;
        }
        return false;
    }




    /**
     * 字段类型转换
     */
    public static void convertColumnType(DbColumnMeta srcMeta, DbTypeEnum srcType, DbTypeEnum destType, DbColumnMeta destMeta) {
        if(srcType==destType) {
            destMeta.setColumnType(srcMeta.getColumnType());
            return;
        }
        DbColumnTypeEnum dest = null;
        if(srcType== DbTypeEnum.SQLSERVER && destType== DbTypeEnum.MYSQL) {
            dest = DbColumnTypeEnum.getColTypeByname(sqlServer2Mysql.get(srcMeta.getColumnType().getName().toLowerCase()));
            destMeta.setColumnType(dest);
        }
        if(srcType== DbTypeEnum.SQLSERVER && destType== DbTypeEnum.ORACLE) {
            dest = DbColumnTypeEnum.getColTypeByname(sqlServer2Oracle.get(srcMeta.getColumnType().getName().toLowerCase()));
            destMeta.setColumnType(dest);
        }
        if(dest==null) {
            throw new RuntimeException("字段类型转换异常, 源库：" + srcType.getDesc() +
                    ", 字段类型:" + srcMeta.getColumnType().getName() + "--->目标库类型未找到：" + destType.getDesc());
        }
        convertSpecail(srcMeta, srcType, destType, destMeta);
    }

    /**
     * 特殊字段转换设置长度
     *
     * @param src
     */
    public static void convertSpecailSqlserver2MySql(DbColumnMeta src, DbColumnMeta destMeta) {
        //mysql datetime2  DATETIMEOFFSET 长度为7时，需要转为char类型
        switch (src.getColumnType()) {
            case DATETIME2:
                if(src.getLength()>=7) {
                    destMeta.setColumnType(DbColumnTypeEnum.VARCHAR);
                    destMeta.setLength(64);
                }
                break;
            case DATETIMEOFFSET:
                if(src.getLength()>=7) {
                    destMeta.setColumnType(DbColumnTypeEnum.VARCHAR);
                    destMeta.setLength(64);
                }
                break;
            case SMALLMONEY:
                src.setLength(10);
                src.setDataScale(4);
                break;
            case UNIQUEIDENTIFIER:
                src.setLength(64);
                destMeta.setLength(64);
                break;
            case CHAR: //SQLSERVER CHAR长度可为1024
                if(src.getLength()==-1 || src.getLength()>=255) {
                    destMeta.setColumnType(DbColumnTypeEnum.TEXT);
                }
                break;
            case VARCHAR:
                if(src.getLength()==-1 || src.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.TEXT);
                }
                break;
            case NVARCHAR:
                if(src.getLength()==-1 || src.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.TEXT);
                }
                break;
            // case TIMESTAMP: //由于sqlserver存储的为二进制8位时间戳，所以需要转为二进制，否则存储失败
            //     if(src.getLength()>=6) {
            //         destMeta.setColumnType(DbColumnTypeEnum.TINYBLOB);
            //         // destMeta.setLength(64);
            //     }
            //     break;
            case DATETIME: //精度损失，有问题，或者直接用varchar
                if(src.getLength()>6) {
                    destMeta.setColumnType(DbColumnTypeEnum.VARCHAR);
                    destMeta.setLength(64);
                }
                break;
            case VARBINARY: //如果VARBINARY类型
                if(src.getLength()==-1) {
                    destMeta.setColumnType(DbColumnTypeEnum.LONGBLOB);
                }
                break;
            case SYSNAME: //
                destMeta.setLength(128);
                break;
        }
    }

    /**
     * 特殊字段转换设置长度
     *
     * @param srcMeta
     */
    public static void convertSpecailSqlserver2Oracle(DbColumnMeta srcMeta, DbColumnMeta destMeta) {

        switch (srcMeta.getColumnType()) {
            case SMALLINT:
                destMeta.setLength(5);
                destMeta.setDataScale(4);
                break;
            case INT:
                destMeta.setLength(10);
                destMeta.setDataScale(4);
                break;
            case BIGINT:
                destMeta.setLength(19);
                destMeta.setDataScale(4);
                break;
            case DECIMAL:
                destMeta.setLength(srcMeta.getLength()*2>18 ? 19 : srcMeta.getLength()*2); //此处获取的值长度与实际长度相差2
                destMeta.setDataScale(srcMeta.getDataScale());
                break;
            case MONEY:
                destMeta.setLength(19);
                destMeta.setDataScale(4);
                break;
            case SMALLMONEY:
                destMeta.setLength(10);
                destMeta.setDataScale(4);
                break;
            case UNIQUEIDENTIFIER:
                destMeta.setLength(64);
                destMeta.setLength(64);
                break;
            case VARCHAR:
                if(srcMeta.getLength()==-1 || srcMeta.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.CLOB);
                } else if(srcMeta.getLength()>=2000) {
                    destMeta.setLength(srcMeta.getLength()/2);
                }
                break;
            case NVARCHAR:
                if(srcMeta.getLength()==-1 || srcMeta.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.CLOB);
                }else if(srcMeta.getLength()>=2000) {
                    destMeta.setLength(srcMeta.getLength()/2);
                }
                break;
            case NCHAR:
                if(srcMeta.getLength()==-1 || srcMeta.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.CLOB);
                }else if(srcMeta.getLength()>=2000) {
                    destMeta.setColumnType(DbColumnTypeEnum.NVARCHAR2);
                    destMeta.setLength(srcMeta.getLength()/2);
                }else if(srcMeta.getLength()>0) {
                    destMeta.setLength(srcMeta.getLength()/2);
                }
                break;
            case NVARCHAR2:
                if(srcMeta.getLength()==-1 || srcMeta.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.CLOB);
                }else if(srcMeta.getLength()>=2000) {
                    destMeta.setLength(srcMeta.getLength()/2);
                }
                break;
            case TIMESTAMP: //sqlserver为8位，所以需要转换
                if(srcMeta.getLength()==null || srcMeta.getLength()<=8) {
                    destMeta.setLength(8);
                }
                break;
            case VARBINARY: //如果VARBINARY类型
                if(srcMeta.getLength()==-1) {
                    destMeta.setColumnType(DbColumnTypeEnum.BLOB);
                }
                break;
            case CHAR: //sqlserver转oracle，如果通过kettle转换，char字符类型，默认oracle建表需要多2个字符，否则会提示长度不够。oracle长度为2000，大于2000需要转化
                if(srcMeta.getLength()==-1 || srcMeta.getLength()>=4000) {
                    destMeta.setColumnType(DbColumnTypeEnum.CLOB);
                }else if(srcMeta.getLength()>=2000-2) {
                    destMeta.setColumnType(DbColumnTypeEnum.VARCHAR2);
                }
                destMeta.setLength(srcMeta.getLength()+2);
                break;
            case TIME: //
                destMeta.setLength(128);
                break;
            case SYSNAME: //
                destMeta.setLength(128);
                break;
        }
    }

    /**
     * 特殊字段类型转换
     */
    public static void convertSpecail(DbColumnMeta srcMeta, DbTypeEnum srcType, DbTypeEnum destType, DbColumnMeta destMeta) {
        if(srcType== DbTypeEnum.SQLSERVER && destType== DbTypeEnum.MYSQL) {
            convertSpecailSqlserver2MySql(srcMeta, destMeta);
        }
        if(srcType== DbTypeEnum.SQLSERVER && destType== DbTypeEnum.ORACLE) {
            convertSpecailSqlserver2Oracle(srcMeta, destMeta);
        }
    }

}
