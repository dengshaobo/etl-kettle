package com.khsh.etl.kettle;

import com.khsh.etl.model.EtlDatabaseBuildModel;
import com.khsh.etl.model.EtlDatabaseModel;

import java.lang.reflect.InvocationTargetException;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: Mock_etl_database
 * Author:   Ejet
 * CreateDate:     2018/10/8 14:37
 * Description:
 * History:
 * Version: 1.0
 */
public class Mock_etl_database {



    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String result = MockUtils.getMockJson(EtlDatabaseModel.class);
        result = MockUtils.getMockJson(EtlDatabaseBuildModel.class);

        System.out.println("==== model ====" + result);
    }



}
