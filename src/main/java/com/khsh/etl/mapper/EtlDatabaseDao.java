package com.khsh.etl.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseModel;
@Mapper
public interface EtlDatabaseDao { 


	public void insertSingle(EtlDatabaseModel obj) throws CoBusinessException; 

	public int insertAutoKey(EtlDatabaseModel obj) throws CoBusinessException; 

	public void update(EtlDatabaseModel obj) throws CoBusinessException; 

	public void delete(EtlDatabaseModel obj) throws CoBusinessException; 

	public abstract EtlDatabaseModel  findByPK(EtlDatabaseModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseModel>  queryByCond(EtlDatabaseModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseModel>  queryByPage(EtlDatabaseModel obj) throws CoBusinessException; 

	public abstract List<EtlDatabaseModel>  queryTree(EtlDatabaseModel obj) throws CoBusinessException; 

	public abstract Integer  findMaxId(EtlDatabaseModel obj) throws CoBusinessException; 


}