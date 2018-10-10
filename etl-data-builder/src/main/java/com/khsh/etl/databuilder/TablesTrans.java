package com.khsh.etl.databuilder;

import com.ejet.comm.utils.StringUtils;
import com.khsh.etl.databuilder.db.DbColumnConverter;
import com.khsh.etl.databuilder.db.DbContext;
import com.khsh.etl.databuilder.db.DbHelper;
import com.khsh.etl.databuilder.db.meta.DbColumnMeta;
import com.khsh.etl.databuilder.db.meta.DbMetaHelper;
import com.khsh.etl.databuilder.db.meta.DbTableMeta;
import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;
import com.khsh.etl.databuilder.vo.TableVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: TablesTrans
 * Author:   Ejet
 * CreateDate:     2018-09-11 9:21
 * Description: 同步表信息
 * History:
 * Version: 1.0
 */
public class TablesTrans {

    private DbContext srcCtx = new DbContext();
    private DbContext destCtx = new DbContext();
    private StringBuffer msg = new StringBuffer();

    public TablesTrans(DbContext src, DbContext dest) {
        this.srcCtx = src;
        this.destCtx = dest;
    }
    /**
     * 获取源库表
     */
    private void getSrcTables() throws Exception {
        //获取所有库表
        if(srcCtx.getTables()==null) {
            List<DbTableMeta> tableMeta = new ArrayList<>();
            srcCtx.setTables(tableMeta);
        }
        srcCtx.getTables().clear();
        List<TableVO> srcTables = DbHelper.queryTables(srcCtx.getConnection(), srcCtx.getDatabase(), srcCtx.getIgnoreTables());
        if(srcTables!=null) {
            msg.append("源库表个数:").append(srcTables.size()).append("\r\n");
            System.out.println("[库表个数]" + srcTables.size() );
            for(TableVO item : srcTables) {
                String tableName = item.getTableName();
                System.out.println("[" + item.getTableType() + "]"
                        + "[catalog.schema.表名]" + item.getTableCatalog() + "." + item.getTableSchema() + "." + tableName  );
                List<DbColumnMeta> columnMeta = DbHelper.getColumnMeta(srcCtx, tableName);

                if(checkTables(tableName, columnMeta)) {
                    DbTableMeta meta = new DbTableMeta(tableName, columnMeta);
                    srcCtx.getTables().add(meta);
                }
            }
        }
    }


    /**
     * 校验表名及字段的长度
     */
    private boolean checkTables(String tableName, List<DbColumnMeta> columnMeta) {
        String destTableName = destCtx.getTablePrefix() + tableName;
        if(destCtx.getDatabase().getDbtype()== DbTypeEnum.ORACLE && destTableName.length()>30) {
            msg.append("自动忽略表,表名太长:").append(tableName).append("\r\n");
            destCtx.addIgnoreTable(tableName);
            return false;
        }
        if(columnMeta==null)
            return false;
        for (DbColumnMeta item : columnMeta) {
            if(item.getColumnName()==null ||
                    (destCtx.getDatabase().getDbtype()== DbTypeEnum.ORACLE && item.getColumnName().length()>30 ) ||
                    (destCtx.getDatabase().getDbtype()== DbTypeEnum.ORACLE && (DbHelper.isChinese(item.getColumnName()) && item.getColumnName().length()>10) )//一个中文汉字占用几个字节，要根据Oracle中字符集编码决定,select lengthb('你') from dual;查询，此处简单处理,当作3个字节
            ) {
                msg.append("自动忽略表,字段太长:").append(tableName).append("表字段:").append(item.getColumnName()).append("\r\n");
                destCtx.addIgnoreTable(tableName);
                return false;
            }
        }
        return true;
    }

    /**
     * 处理附加新增字段信息
     */
    private void buildAddtion(DbTableMeta tableMeta) {
        if(destCtx.getAddtion()!=null && destCtx.getAddtion().size()>0) {
            for(DbTableMeta item : destCtx.getAddtion()) {
                if(item==null) {
                    continue;
                }
                if(item.getTableName()==null || item.getTableName().trim()=="" || item.getTableName().equals(tableMeta.getTableName())) {
                    for(DbColumnMeta col: item.getColumns()) {
                        col.setColumnTypeShell(DbColumnConverter.getColumnTypeShell(col));
                        tableMeta.getColumns().add(col);
                    }
                }
            }
        }
    }

    /**
     * 构建目标表，前缀
     */
    private void buildDestTablenamePrefix() {
        if(!srcCtx.isHasPrefix()) {
           destCtx.setTablePrefix(""); //不需要设置前缀，则置空
           return;
        }
        DbTypeEnum dbType = srcCtx.getDatabase().getDbtype();
        String prefix = srcCtx.getTablePrefix();
        if(dbType== DbTypeEnum.ORACLE) {
            prefix = (StringUtils.isBlank(prefix) ? srcCtx.getDatabase().getCatalog() : prefix);
        }if(dbType== DbTypeEnum.MYSQL) {
            prefix = (StringUtils.isBlank(prefix) ? srcCtx.getDatabase().getCatalog() : prefix);
        } if(dbType== DbTypeEnum.SQLSERVER) {
            prefix = (StringUtils.isBlank(prefix) ? srcCtx.getDatabase().getSchema() : prefix);
        }  else {
            prefix = (StringUtils.isBlank(prefix) ? srcCtx.getDatabase().getCatalog() : prefix);
        }
        destCtx.setTablePrefix(prefix+"_");
    }

    /**
     * 构建目标表
     * @throws Exception
     */
    private void buildDestTables(boolean isDrop) throws Exception {
        if(destCtx.getTables()==null) {
            List<DbTableMeta> tableMeta = new ArrayList<>();
            destCtx.setTables(tableMeta);
        }
        destCtx.getTables().clear();
        int count = 0;
        for(DbTableMeta item : srcCtx.getTables()) {
            System.out.println(++count + "====[源库.表名]" + srcCtx.getDatabase().getSchema() + "." + item.getTableName()  );
            DbTableMeta tableMeta = buildDestColumns(item);
            System.out.println(count + "====[目标库.表名]"+ tableMeta.getTableName() + ", 建表脚本:" + tableMeta.getTableCreateShell());
            DbHelper.createTable(destCtx.getConnection(), destCtx.getDatabase(), isDrop, tableMeta.getTableName(), tableMeta.getTableCreateShell());
            destCtx.getTables().add(tableMeta);
        }
    }

    /**
     * 构建表字段
     *
     * @return
     * @throws Exception
     */
    private DbTableMeta buildDestColumns(DbTableMeta item) throws Exception {
        //获取表字段
        List<DbColumnMeta> columnList = new ArrayList<>();
        for(DbColumnMeta col : item.getColumns()) {
            DbColumnMeta dest = DbColumnConverter.convertColumn(col, srcCtx.getDatabase().getDbtype(), destCtx.getDatabase().getDbtype());
            columnList.add(dest);
        }
        //加上前缀
        DbTableMeta tableMeta = new DbTableMeta(destCtx.getTablePrefix() + item.getTableName(), columnList);
        //处理附加信息
        buildAddtion(tableMeta);
        tableMeta.setTableCreateShell(DbMetaHelper.getCreateSQL(destCtx.getDatabase().getDbtype(), tableMeta));
        return tableMeta;
    }


    /**
     * 开始构建传输
     */
    public void execute(boolean isDrop) throws Exception {
        try {
            buildDestTablenamePrefix();
            getSrcTables();
            buildDestTables(isDrop);
        }finally {
            System.out.println(msg.toString());
        }
    }


}
