package com.khsh.etl.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseBuildExtModel;
@Mapper
public interface EtlDatabaseBuildExtDao { 


	public void insertSingle(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public int insertAutoKey(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public void update(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public void delete(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public abstract EtlDatabaseBuildExtModel  findByPK(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseBuildExtModel>  queryByCond(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseBuildExtModel>  queryByPage(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseBuildExtModel>  queryTree(EtlDatabaseBuildExtModel obj) throws CoBusinessException; 

	public abstract Integer  findMaxId(EtlDatabaseBuildExtModel obj) throws CoBusinessException;


    public abstract void deleteByBuildUUid(EtlDatabaseBuildExtModel obj) throws CoBusinessException;

    public abstract void insertBatch(List<EtlDatabaseBuildExtModel> list) throws CoBusinessException;
}