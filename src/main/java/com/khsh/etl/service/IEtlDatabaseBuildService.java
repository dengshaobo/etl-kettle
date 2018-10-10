package com.khsh.etl.service;

import java.util.List;

import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseBuildModel;
public interface IEtlDatabaseBuildService { 


	public void insertAutoKey(EtlDatabaseBuildModel model) throws CoBusinessException; 

	public void update(EtlDatabaseBuildModel model) throws CoBusinessException;

	public void delete(EtlDatabaseBuildModel model) throws CoBusinessException; 

	public List<EtlDatabaseBuildModel>  queryByCond(EtlDatabaseBuildModel model) throws CoBusinessException;


}