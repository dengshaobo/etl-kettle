package com.khsh.etl.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseBuildModel;
@Mapper
public interface EtlDatabaseBuildDao { 


	public void insertSingle(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public int insertAutoKey(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public void update(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public void delete(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public abstract EtlDatabaseBuildModel  findByPK(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseBuildModel>  queryByCond(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseBuildModel>  queryByPage(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseBuildModel>  queryTree(EtlDatabaseBuildModel obj) throws CoBusinessException; 

	public abstract Integer  findMaxId(EtlDatabaseBuildModel obj) throws CoBusinessException; 


}