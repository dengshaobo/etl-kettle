package com.khsh.etl.databuilder;

import com.ejet.comm.db.CoDbConfig;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: DBConfigTest
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:37
 * Description: 数据库配置信息
 * History:
 * Version: 1.0
 */
public class DBConfigTest {

    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";


    public static CoDbConfig oracle_187Test = new CoDbConfig();
    public static CoDbConfig oracle_187_ShoppTest = new CoDbConfig();

    public static CoDbConfig mysql_127Test = new CoDbConfig();

    public static CoDbConfig mysql_localhostKettleDest = new CoDbConfig();
    public static CoDbConfig mysql_localhostKettleSrc = new CoDbConfig();

    public static CoDbConfig sqlserver_185Test = new CoDbConfig();
    public static CoDbConfig sqlserver_185_qyy = new CoDbConfig();

    static  {
        oracle_187Test.setDriverClassName(ORACLE_DRIVER);
        oracle_187Test.setUrl("jdbc:oracle:thin:@192.168.0.187:1521:test");
        oracle_187Test.setUsername("test");
        oracle_187Test.setPassword("test");

        oracle_187_ShoppTest.setDriverClassName(ORACLE_DRIVER);
        oracle_187_ShoppTest.setUrl("jdbc:oracle:thin:@192.168.0.187:1521:orcl");
        oracle_187_ShoppTest.setUsername("shopping");
        oracle_187_ShoppTest.setPassword("shopping");


        mysql_127Test.setDriverClassName(MYSQL_DRIVER);
        mysql_127Test.setUrl("jdbc:mysql://192.168.0.127:3306/qyy_syn?characterEncoding=utf8");
        mysql_127Test.setUsername("root");
        mysql_127Test.setPassword("123456");


        mysql_localhostKettleSrc.setDriverClassName(MYSQL_DRIVER);
        mysql_localhostKettleSrc.setUrl("jdbc:mysql://127.0.0.1:3306/kettle_src?characterEncoding=utf8");
        mysql_localhostKettleSrc.setUsername("root");
        mysql_localhostKettleSrc.setPassword("weidong@2013");


        mysql_localhostKettleDest.setDriverClassName(MYSQL_DRIVER);
        mysql_localhostKettleDest.setUrl("jdbc:mysql://127.0.0.1:3306/kettle_dest?characterEncoding=utf8");
        mysql_localhostKettleDest.setUsername("root");
        mysql_localhostKettleDest.setPassword("weidong@2013");


        sqlserver_185Test.setDriverClassName(SQLSERVER_DRIVER);
        sqlserver_185Test.setUrl("jdbc:sqlserver://192.168.0.185:1433;databaseName=test;user=sa;password=Khsh185");
        sqlserver_185Test.setUsername("sa");
        sqlserver_185Test.setPassword("Khsh185");

        sqlserver_185_qyy.setDriverClassName(SQLSERVER_DRIVER);
        sqlserver_185_qyy.setUrl("jdbc:sqlserver://192.168.0.185:1433;databaseName=qyy;user=sa;password=Khsh185");
        sqlserver_185_qyy.setUsername("sa");
        sqlserver_185_qyy.setPassword("Khsh185");

    }



}
