package com.khsh.etl.service;

import java.util.List;

import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseModel;
public interface IEtlDatabaseService { 


	public void insertAutoKey(EtlDatabaseModel model) throws CoBusinessException; 

	public void update(EtlDatabaseModel model) throws CoBusinessException;

	public void delete(EtlDatabaseModel model) throws CoBusinessException; 

	public List<EtlDatabaseModel>  queryByCond(EtlDatabaseModel model) throws CoBusinessException;


}