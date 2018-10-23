package com.khsh.etl.vo;

import com.khsh.etl.model.EtlDatabaseBuildExtModel;
import com.khsh.etl.model.EtlDatabaseBuildModel;

import java.util.List;

public class EtlDatabaseBuildVO extends EtlDatabaseBuildModel {

    /**
     * 扩展的字段信息表
     */
    private List<EtlDatabaseBuildExtModel>  dbextlist;

    public List<EtlDatabaseBuildExtModel> getDbextlist() {
        return dbextlist;
    }

    public void setDbextlist(List<EtlDatabaseBuildExtModel> dbextlist) {
        this.dbextlist = dbextlist;
    }
}