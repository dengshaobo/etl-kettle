package com.khsh.etl.databuilder;

import org.junit.Test;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: SynMysqlTest
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:35
 * Description: 测试同步
 * History:
 * Version: 1.0
 */
public class SynMysqlTest extends SynBaseTest {


    @Test
    public void Sqlserver2Mysql() {

        TablesBuilder syn = new TablesBuilder(DBConfigTest.sqlserver_185Test, DBConfigTest.mysql_127Test);
        try {
            init();
            syn.setAddition(addtion);
            syn.build(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void Oracle2Mysql() {
        TablesBuilder syn = new TablesBuilder(DBConfigTest.oracle_187_ShoppTest, DBConfigTest.mysql_127Test);
        try {
            init();
            syn.setAddition(addtion);
            syn.build(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void Mysql2Mysql() {

        TablesBuilder syn = new TablesBuilder(DBConfigTest.mysql_localhostKettleSrc, DBConfigTest.mysql_localhostKettleDest);
        try {
            init();
            syn.setAddition(addtion);
            syn.build(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
