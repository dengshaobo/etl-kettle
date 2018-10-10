package com.khsh.etl.databuilder.db.meta;

import com.khsh.etl.databuilder.db.dialect.DbTypeEnum;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DbDatabaseMeta
 * Author:   Ejet
 * CreateDate:     2018-08-24 10:16
 * Description: 库信息
 * History:
 * Version: 1.0
 */
public class DbDatabaseMeta {
    /**
     * 数据库类型
     */
    private DbTypeEnum dbtype;

    private String driverClass;

    private String url;

    private String username;

    private String password;

    private String version;
    /**
     * 类别
     */
    private String catalog;
    /**
     * 库、用户方案名称
     */
    private String schema;

    private DbDatabaseMeta() {

    }

    public DbDatabaseMeta(DbTypeEnum dbtype, String driverClass, String url, String username, String password,
                          String catalog, String schema) {
		this(dbtype, driverClass, url, username, password, catalog, schema, null);
	}

	public DbDatabaseMeta(DbTypeEnum dbtype, String driverClass, String url, String username, String password,
                          String catalog, String schema, String version) {
		super();
		this.dbtype = dbtype;
		this.driverClass = driverClass;
		this.url = url;
		this.username = username;
		this.password = password;
		this.catalog = catalog;
		this.schema = schema;
		this.version = version;
	}

	public DbTypeEnum getDbtype() {
		return dbtype;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getVersion() {
		return version;
	}

	public String getCatalog() {
		return catalog;
	}

	public String getSchema() {
		return schema;
	}


	
	

	
}
