package com.khsh.etl.databuilder;

import org.junit.Test;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: SynOracleTest
 * Author:   Ejet
 * CreateDate:     2018-08-28 11:30
 * Description: 测试同步
 * History:
 * Version: 1.0
 */
public class SynOracleTest extends  SynBaseTest{


    @Test
    public void Sqlserver2Oracle() {

        TablesBuilder syn = new TablesBuilder(DBConfigTest.sqlserver_185_qyy, DBConfigTest.oracle_187Test);
        try {

            init();
            syn.setAddition(addtion);
            syn.setIgnoreTables(ignoreTables);
            syn.build(true);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Test
    public void Oracle2Oracle() {

        TablesBuilder syn = new TablesBuilder(DBConfigTest.oracle_187_ShoppTest, DBConfigTest.oracle_187Test);
        try {
            init();
            syn.setAddition(addtion);
            syn.build(false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
