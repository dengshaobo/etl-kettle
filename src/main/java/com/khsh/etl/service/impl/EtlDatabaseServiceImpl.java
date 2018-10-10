package com.khsh.etl.service.impl;


import com.ejet.comm.utils.UuidUtils;
import com.ejet.comm.utils.time.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.ejet.comm.exception.ExceptionCode;
import com.ejet.comm.PageBean;
import com.github.pagehelper.PageHelper;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseModel;
import com.khsh.etl.mapper.EtlDatabaseDao;
import com.khsh.etl.service.IEtlDatabaseService;
@Service("etlDatabaseService")
public class EtlDatabaseServiceImpl implements IEtlDatabaseService { 


	private final Logger log = LoggerFactory.getLogger(EtlDatabaseServiceImpl.class);

	@Autowired
	private EtlDatabaseDao mDao;

	public void insertAutoKey(EtlDatabaseModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(EtlDatabaseModel model) throws CoBusinessException { 
 		if(model.getId()==null && model.getUuid()==null) {
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(EtlDatabaseModel model) throws CoBusinessException {
        if(model.getId()==null && model.getUuid()==null) {
            throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
        }
 		 mDao.delete(model);
 	}  

	public EtlDatabaseModel  findByPK(EtlDatabaseModel model) throws CoBusinessException { 
 		 return mDao.findByPK(model);
 	}  

	public List<EtlDatabaseModel>  queryByCond(EtlDatabaseModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<EtlDatabaseModel>  queryByPage(EtlDatabaseModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<EtlDatabaseModel> list = mDao.queryByPage(model);
		PageBean<EtlDatabaseModel> page = new PageBean<EtlDatabaseModel>(list);
  
		return page;
 	}  

	public int insertSingle(EtlDatabaseModel model) throws CoBusinessException { 
 		if(model.getUuid()==null) {
 		    model.setUuid(UuidUtils.get32UUID());
        }
        model.setModifyTime(TimeUtils.getCurrentFullTime());
 		int maxId = mDao.insertAutoKey(model);
 		return maxId;
 	}


}