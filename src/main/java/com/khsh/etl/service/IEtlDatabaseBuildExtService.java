package com.khsh.etl.service;

import java.util.List;

import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseBuildExtModel;
public interface IEtlDatabaseBuildExtService { 


	public void insertAutoKey(EtlDatabaseBuildExtModel model) throws CoBusinessException; 

	public void update(EtlDatabaseBuildExtModel model) throws CoBusinessException;

	public void delete(EtlDatabaseBuildExtModel model) throws CoBusinessException; 

	public List<EtlDatabaseBuildExtModel>  queryByCond(EtlDatabaseBuildExtModel model) throws CoBusinessException;


}