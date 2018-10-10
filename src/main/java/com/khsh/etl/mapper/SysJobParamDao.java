package com.khsh.etl.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobParamModel;
@Mapper
public interface SysJobParamDao { 

	public void insertSingle(SysJobParamModel obj) throws CoBusinessException; 

	public int insertAutoKey(SysJobParamModel obj) throws CoBusinessException; 

	public void update(SysJobParamModel obj) throws CoBusinessException; 

	public void delete(SysJobParamModel obj) throws CoBusinessException; 

	public abstract SysJobParamModel  findByPK(SysJobParamModel obj) throws CoBusinessException; 

	public abstract List<SysJobParamModel>  queryByCond(SysJobParamModel obj) throws CoBusinessException; 

	public abstract List<SysJobParamModel>  queryByPage(SysJobParamModel obj) throws CoBusinessException; 

	public abstract List<SysJobParamModel>  queryTree(SysJobParamModel obj) throws CoBusinessException; 

	public abstract Integer  findMaxId(SysJobParamModel obj) throws CoBusinessException; 


}