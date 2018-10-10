package com.khsh.etl.databuilder.db.meta;

import com.ejet.comm.utils.collect.ListUtils;
import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据库辅助类
 *
 */
public class DbMetaHelper {
	
	private final static String MYSQL_DRIVER_NAME = "mysql";
    private final static String ORACLE_DRIVER_NAME = "oracle";
    private final static String SQLSERVER_DRIVER_NAME = "SQL SERVER";
    private final static String SYBASE_DRIVER_NAME = "sybase";

	
	/**
	 * 查询库中所有表(不包括视图)
	 */
	public static String getQueryTablesSQL(DbTypeEnum type, DbDatabaseMeta database, List<String> ignoreTables) {
		StringBuffer buffer = new StringBuffer();
		if(DbTypeEnum.SQLSERVER==type) {
			buffer.append("SELECT TABLE_CATALOG as tableCatalog, TABLE_SCHEMA as tableSchema, TABLE_NAME as tableName, TABLE_TYPE as tableType ")
			.append(" FROM INFORMATION_SCHEMA.TABLES WHERE 1=1 ");
			if(database.getSchema()!=null && !"".equals(database.getSchema().trim())) {
				buffer.append(" AND TABLE_TYPE='BASE TABLE' AND TABLE_SCHEMA='").append(database.getSchema()).append("'");
				//buffer.append(" AND TABLE_NAME like 'z%'");
				// buffer.append(" AND TABLE_NAME!='ZY_YB_JSB_DRSYB_FYMX'");
				// buffer.append(" AND TABLE_NAME!='ZySettlementDiscountsAssociated'");
				// buffer.append(" AND TABLE_NAME!='ZY_YB_JSB_DRGSYB_FYMX'");
			}
			if(ignoreTables!=null && ignoreTables.size()>0) {
			    buffer.append(" AND TABLE_NAME NOT IN(").append(ListUtils.joinSql(ignoreTables, ",")).append(")");
            }

		}else if(DbTypeEnum.ORACLE==type) {
			buffer.append("SELECT TABLESPACE_NAME AS tableCatalog, TABLESPACE_NAME AS tableSchema, TABLE_NAME AS tableName, 'BASE TABLE' AS tableType ")
			.append(" FROM user_tables WHERE 1=1 ");
			if(database.getSchema()!=null && !"".equals(database.getSchema().trim())) {
				buffer.append(" AND owner='").append(database.getSchema()).append("'");
			}
            if(ignoreTables!=null && ignoreTables.size()>0) {
                buffer.append(" AND TABLE_NAME NOT IN(").append(ListUtils.joinSql(ignoreTables, ",")).append(")");
            }
		}else if(DbTypeEnum.MYSQL==type) {
			buffer.append("SELECT TABLE_CATALOG AS tableCatalog, TABLE_SCHEMA AS tableSchema, TABLE_NAME AS tableName, TABLE_TYPE AS tableType ")
			.append(" FROM INFORMATION_SCHEMA.TABLES WHERE 1=1 ");
			if(database.getCatalog()!=null && !"".equals(database.getCatalog().trim())) {
				buffer.append(" AND TABLE_SCHEMA='").append(database.getCatalog()).append("'");
			}
            if(ignoreTables!=null && ignoreTables.size()>0) {
                buffer.append(" AND TABLE_NAME NOT IN(").append(ListUtils.joinSql(ignoreTables, ",")).append(")");
            }
		}
		//buffer.append(";");
		System.out.println("[查询表]" + buffer.toString());
		return buffer.toString();
	}
	
	/**
	 * 查询表结构
	 */
	public static String getQueryTableColumnsSQL(DbTypeEnum type, DbDatabaseMeta database, String tableName) {
		StringBuffer buffer = new StringBuffer();
		if(DbTypeEnum.SQLSERVER==type) {
			buffer.append("SELECT b.name AS dataType, a.name AS columnName, a.length AS length, a.xscale AS dataScale, CASE a.isnullable WHEN 0 THEN 'N' WHEN 1 THEN 'Y' END AS isnullable ")
			.append(" , a.colorder as colOrder ")
			.append(" FROM syscolumns a,systypes b WHERE a.xtype=b.xtype AND a.xusertype=b.xusertype ");
			if(tableName!=null && !"".equals(tableName.trim())) {
				buffer.append(" AND a.id=Object_Id('").append(tableName).append("')");
			}
		} else if(DbTypeEnum.ORACLE==type) {
			buffer.append("SELECT DATA_TYPE dataType, COLUMN_NAME AS columnName, DATA_LENGTH AS length, DATA_SCALE AS dataScale, NULLABLE AS isnullable ")
			.append(", COLUMN_ID as colOrder ")
			.append(" FROM user_tab_columns ");
			if(tableName!=null && !"".equals(tableName.trim())) {
				buffer.append("WHERE table_name='").append(tableName).append("'");
			}
		} else if(DbTypeEnum.MYSQL==type) {
			buffer.append("SELECT c.COLUMN_NAME columnName, c.DATA_TYPE AS dataType, CASE c.IS_NULLABLE WHEN 'yes' THEN 'Y' WHEN 'no' THEN 'N' END AS isnullable,  ")
			.append("CASE WHEN c.CHARACTER_MAXIMUM_LENGTH IS NOT NULL THEN c.CHARACTER_MAXIMUM_LENGTH WHEN c.NUMERIC_PRECISION IS NOT NULL THEN c.NUMERIC_PRECISION END AS length, ")
			.append("CASE WHEN c.NUMERIC_SCALE IS NOT NULL THEN c.NUMERIC_SCALE ELSE 0 END AS dataScale")
			.append(" FROM information_schema.columns c where 1=1 ");
			if(database.getCatalog()!=null && !"".equals(database.getCatalog())) {
				buffer.append(" AND TABLE_SCHEMA='").append(database.getCatalog()).append("'");
			}
			if(tableName!=null && !"".equals(tableName.trim())) {
				buffer.append(" AND table_name='").append(tableName).append("'");
			}
		}
		//buffer.append(";");
        System.out.println("[查询字段]" + buffer.toString());
		return buffer.toString();
	}

    /**
     * 查询表是否在
     */
    public static String getQueryTableExistSQL(DbTypeEnum type, DbDatabaseMeta database, String tableName) {
        StringBuffer buff = new StringBuffer();
        if(DbTypeEnum.ORACLE==type) {
            //查询
            buff.append("select count(*) from user_tables where Upper(table_name)=UPPER('").append(tableName.trim()).append("')");
        } else if(DbTypeEnum.MYSQL==type) {
            buff.append("select TABLE_NAME from INFORMATION_SCHEMA.TABLES where UPPER(TABLE_NAME)=UPPER('").append(tableName.trim()).append("')");
            if(database.getCatalog()!=null && !"".equals(database.getCatalog().trim())) {
                buff.append(" AND TABLE_SCHEMA='").append(database.getCatalog()).append("'");
            }
        } else if(DbTypeEnum.SQLSERVER==type) {
            buff.append("select TABLE_NAME from INFORMATION_SCHEMA.TABLES where UPPER(TABLE_NAME)=UPPER('").append(tableName.trim()).append("')");

        }
        return buff.toString();
    }


    /**
     * 创建mysql语句
     * @return
     */
	private static String createSQL_Mysql(DbTableMeta tableMeta) {
        if(tableMeta.getColumns()==null || tableMeta.getColumns().size()==0) {
            return null;
        }
        StringBuffer buff = new StringBuffer();
            buff.append("CREATE TABLE `").append(tableMeta.getTableName()).append("` (");
        int size = tableMeta.getColumns().size();
        for(int i=0; i<size; i++) {
            DbColumnMeta item = tableMeta.getColumns().get(i);
            buff.append("`").append(item.getColumnName().trim()).append("`").append(" ")
                    .append(item.getColumnType().getName()).append(" ");
            if(item.getColumnTypeShell()!=null && !"".equals(item.getColumnTypeShell().trim())) {
                buff.append(item.getColumnTypeShell());
            }
            buff.append(item.isNull()? " NULL " : " NOT NULL ").append(" ");
            if(i<size-1) {
                buff.append(",  ");
            }
        }
        buff.append(")");
        if(tableMeta.getTableDesc()!=null && !"".equals(tableMeta.getTableDesc().trim())) {
            buff.append("comment='").append(tableMeta.getTableDesc()).append("'").append("\r\n");
        }
        buff.append("ENGINE=InnoDB").append("\r\n");
        buff.append("DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci");
        //buff.append(";");
        return buff.toString();
    }

    /**
     * 创建oracle语句
     * @return
     */
    private static String createSQL_Oracle(DbTableMeta tableMeta) {
        if(tableMeta.getColumns()==null || tableMeta.getColumns().size()==0) {
            return null;
        }
        StringBuffer buff = new StringBuffer();
        buff.append("CREATE TABLE \"").append(tableMeta.getTableName()).append("\" (");
        int size = tableMeta.getColumns().size();
        for(int i=0; i<size; i++) {
            DbColumnMeta item = tableMeta.getColumns().get(i);
            buff.append("\"").append(item.getColumnName().trim()).append("\"").append(" ")
                    .append(item.getColumnType().getName()).append(" ");
            if(item.getColumnTypeShell()!=null && !"".equals(item.getColumnTypeShell().trim())) {
                buff.append(item.getColumnTypeShell());
            }
            buff.append(item.isNull()? " NULL " : " NOT NULL ").append(" ");
            if(i<size-1) {
                buff.append(",  ");
            }
        }
        buff.append(")");
        //buff.append(";");
        return buff.toString().toUpperCase();
    }

	/**
	 * 获取创建表sql
	 */
	public static String getCreateSQL(DbTypeEnum type, DbTableMeta tableMeta) throws Exception {
        if(tableMeta.getColumns()==null || tableMeta.getColumns().size()==0) {
        	return null;
        }
        StringBuffer buff = new StringBuffer();
        if(DbTypeEnum.MYSQL==type) {
            return createSQL_Mysql(tableMeta);
        } else if(DbTypeEnum.ORACLE==type) {
            return createSQL_Oracle(tableMeta);
        } else if(DbTypeEnum.SQLSERVER==type) {
        	buff.append("CREATE TABLE ").append(tableMeta.getTableName()).append(" (");
        }
        int size = tableMeta.getColumns().size();
        for(int i=0; i<size; i++) {
        	DbColumnMeta item = tableMeta.getColumns().get(i);
        	buff.append(item.getColumnName()).append(" ")
        	.append(item.getColumnType().getName()).append(" ");
        	if(item.getColumnTypeShell()!=null && !"".equals(item.getColumnTypeShell().trim())) {
        		buff.append(item.getColumnTypeShell());
        	}
        	buff.append(item.isNull()? " NULL " : " NOT NULL ").append(" ");
        	if(i<size-1) {
        		buff.append(",  ");
        	}
        }
        buff.append(")");
        return buff.toString();
    }
	
	/**
	 * 获取删除表sql
	 */
	public static String getDropSQL(DbTypeEnum type, DbTableMeta tableMeta) throws Exception {
        StringBuffer buff = new StringBuffer();
        if(DbTypeEnum.ORACLE==type) {
        	buff.append("DROP TABLE ").append(tableMeta.getTableName()).append("");
        } else if(DbTypeEnum.MYSQL==type) {
        	buff.append("DROP TABLE IF EXISTS '").append(tableMeta.getTableName()).append("';");
        }
        return buff.toString();
    }
	




	
	
}
