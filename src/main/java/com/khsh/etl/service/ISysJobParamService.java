package com.khsh.etl.service;

import java.util.List;

import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobParamModel;
public interface ISysJobParamService { 


	public void insertAutoKey(SysJobParamModel model) throws CoBusinessException; 

	public void update(SysJobParamModel model) throws CoBusinessException;

	public void delete(SysJobParamModel model) throws CoBusinessException; 

	public List<SysJobParamModel>  queryByCond(SysJobParamModel model) throws CoBusinessException;


}