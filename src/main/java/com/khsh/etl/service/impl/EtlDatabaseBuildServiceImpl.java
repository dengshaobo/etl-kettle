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
import com.khsh.etl.model.EtlDatabaseBuildModel;
import com.khsh.etl.mapper.EtlDatabaseBuildDao;
import com.khsh.etl.service.IEtlDatabaseBuildService;
@Service("etlDatabaseBuildService")
public class EtlDatabaseBuildServiceImpl implements IEtlDatabaseBuildService { 


	private final Logger log = LoggerFactory.getLogger(EtlDatabaseBuildServiceImpl.class);

	@Autowired
	private EtlDatabaseBuildDao mDao;

	public void insertAutoKey(EtlDatabaseBuildModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(EtlDatabaseBuildModel model) throws CoBusinessException { 
 		if(model.getId()==null) { 
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(EtlDatabaseBuildModel model) throws CoBusinessException { 
 		 mDao.delete(model);
 	}  

	public EtlDatabaseBuildModel  findByPK(EtlDatabaseBuildModel model) throws CoBusinessException { 
 		 return mDao.findByPK(model);
 	}  

	public List<EtlDatabaseBuildModel>  queryByCond(EtlDatabaseBuildModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<EtlDatabaseBuildModel>  queryByPage(EtlDatabaseBuildModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<EtlDatabaseBuildModel> list = mDao.queryByPage(model);
		PageBean<EtlDatabaseBuildModel> page = new PageBean<EtlDatabaseBuildModel>(list);
  
 		 return page;
 	}  

	public int insertSingle(EtlDatabaseBuildModel model) throws CoBusinessException {
        if(model.getUuid()==null) {
            model.setUuid(UuidUtils.get32UUID());
        }
        model.setModifyTime(TimeUtils.getCurrentFullTime());
        int maxId = mDao.insertAutoKey(model);
        return maxId;
 	}


}