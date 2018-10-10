package com.khsh.etl.service;

import java.util.List;

import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobScheduleModel;
public interface ISysJobScheduleService { 


	public void insertAutoKey(SysJobScheduleModel model) throws CoBusinessException; 

	public void update(SysJobScheduleModel model) throws CoBusinessException;

	public void delete(SysJobScheduleModel model) throws CoBusinessException; 

	public List<SysJobScheduleModel>  queryByCond(SysJobScheduleModel model) throws CoBusinessException;


}