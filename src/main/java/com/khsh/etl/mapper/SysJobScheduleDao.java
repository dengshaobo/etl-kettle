package com.khsh.etl.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobScheduleModel;
@Mapper
public interface SysJobScheduleDao { 


	public void insertSingle(SysJobScheduleModel obj) throws CoBusinessException; 

	public int insertAutoKey(SysJobScheduleModel obj) throws CoBusinessException; 

	public void update(SysJobScheduleModel obj) throws CoBusinessException; 

	public void delete(SysJobScheduleModel obj) throws CoBusinessException; 

	public abstract SysJobScheduleModel  findByPK(SysJobScheduleModel obj) throws CoBusinessException; 

	public abstract List<SysJobScheduleModel>  queryByCond(SysJobScheduleModel obj) throws CoBusinessException; 

	public abstract List<SysJobScheduleModel>  queryByPage(SysJobScheduleModel obj) throws CoBusinessException; 

	public abstract List<SysJobScheduleModel>  queryTree(SysJobScheduleModel obj) throws CoBusinessException; 

	public abstract Integer  findMaxId(SysJobScheduleModel obj) throws CoBusinessException; 


}