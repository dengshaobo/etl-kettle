package com.khsh.etl.databuilder;

import com.khsh.etl.databuilder.db.meta.DbColumnMeta;
import com.khsh.etl.databuilder.db.dialect.DbColumnTypeEnum;
import com.khsh.etl.databuilder.db.meta.DbTableMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: SynBaseTest
 * Author:   Ejet
 * CreateDate:     2018-08-28 14:58
 * Description: 基类，数据初始
 * History:
 * Version: 1.0
 */
public abstract class SynBaseTest {

    protected List<DbTableMeta> addtion = new ArrayList<>();

    protected List<String> ignoreTables = new ArrayList<>();

    public void init() {
        List<DbColumnMeta> columns = new ArrayList<>();
        DbColumnMeta col=new DbColumnMeta();
        col.setColumnName("KHSH_MODIFY_TIME");
        col.setColumnType(DbColumnTypeEnum.VARCHAR);
        col.setLength(30);
        col.setNull(true);
        columns.add(col); //增加一列

        //应用到所有表中
        DbTableMeta table = new DbTableMeta(null, columns);
        addtion.add(table);


        // ignoreTables.add("ZySettlementDiscountsAssociated"); //表名太长
        // ignoreTables.add("YG_BLOOD_CONTACT_OCCUPATIONAL_EXPOSURE"); //表名太长
        // ignoreTables.add("YG_BLOOD_CONTACT_CAREER_HISTORY"); //表名太长
        // ignoreTables.add("BL_PATIENT_RELATION_TEMP20140813"); //表名太长
        // ignoreTables.add("PUB_USERREPORT_RPTVIEWEVENT_CONTENTCELLDBLCLICK"); //表名太长
        // ignoreTables.add("DICT_DISTRIBUTION_SERIAL_NUMBER"); //表名太长
        // ignoreTables.add("YG_OPERATION_INFECTION_ICU_PART"); //表名太长
        // ignoreTables.add("YG_OPERATION_INFECTION_ICU_SAMPLE"); //表名太长
        //
        // ignoreTables.add("ZY_YB_JSB_DRGSYB_FYMX"); //表字段太长
        // ignoreTables.add("ZY_YB_JSB_DRSYB_FYMX");//表字段太长
        // ignoreTables.add("A_CW_TJ");//表字段太长

    }

}
