package com.khsh.etl.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobLogModel;
@Mapper
public interface SysJobLogDao { 


	public void insertSingle(SysJobLogModel obj) throws CoBusinessException; 

	public int insertAutoKey(SysJobLogModel obj) throws CoBusinessException; 

	public void update(SysJobLogModel obj) throws CoBusinessException; 

	public void delete(SysJobLogModel obj) throws CoBusinessException; 

	public abstract SysJobLogModel  findByPK(SysJobLogModel obj) throws CoBusinessException; 

	public abstract List<SysJobLogModel>  queryByCond(SysJobLogModel obj) throws CoBusinessException; 

	public abstract List<SysJobLogModel>  queryByPage(SysJobLogModel obj) throws CoBusinessException; 

	public abstract List<SysJobLogModel>  queryTree(SysJobLogModel obj) throws CoBusinessException; 

	public abstract Integer  findMaxId(SysJobLogModel obj) throws CoBusinessException; 


}