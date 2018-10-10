package com.khsh.etl.service;

import java.util.List;

import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobLogModel;
public interface ISysJobLogService { 


	public void insertAutoKey(SysJobLogModel model) throws CoBusinessException; 

	public void update(SysJobLogModel model) throws CoBusinessException;

	public void delete(SysJobLogModel model) throws CoBusinessException; 

	public List<SysJobLogModel>  queryByCond(SysJobLogModel model) throws CoBusinessException;


}