package com.khsh.etl.service.impl;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.ejet.comm.exception.ExceptionCode;
import com.ejet.comm.PageBean;
import com.github.pagehelper.PageHelper;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.EtlDatabaseBuildExtModel;
import com.khsh.etl.mapper.EtlDatabaseBuildExtDao;
import com.khsh.etl.service.IEtlDatabaseBuildExtService;
@Service("etlDatabaseBuildExtService")
public class EtlDatabaseBuildExtServiceImpl implements IEtlDatabaseBuildExtService { 


	private final Logger log = LoggerFactory.getLogger(EtlDatabaseBuildExtServiceImpl.class);

	@Autowired
	private EtlDatabaseBuildExtDao mDao;

	public void insertAutoKey(EtlDatabaseBuildExtModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(EtlDatabaseBuildExtModel model) throws CoBusinessException { 
 		if(model.getId()==null) { 
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(EtlDatabaseBuildExtModel model) throws CoBusinessException {
        if(model.getId()==null) {
            throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
        }
 		 mDao.delete(model);
 	}  

	public EtlDatabaseBuildExtModel  findByPK(EtlDatabaseBuildExtModel model) throws CoBusinessException { 
 		 return mDao.findByPK(model);
 	}  

	public List<EtlDatabaseBuildExtModel>  queryByCond(EtlDatabaseBuildExtModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<EtlDatabaseBuildExtModel>  queryByPage(EtlDatabaseBuildExtModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<EtlDatabaseBuildExtModel> list = mDao.queryByPage(model);
		PageBean<EtlDatabaseBuildExtModel> page = new PageBean<EtlDatabaseBuildExtModel>(list);
  
 		 return page;
 	}  

	public int insertSingle(EtlDatabaseBuildExtModel model) throws CoBusinessException { 
 		// 获取最大id。保证连续性
 		Integer maxId = mDao.findMaxId(null);
 		maxId = maxId==null? 1 : maxId+1;
 		model.setId(maxId);
 		mDao.insertSingle(model);
 		return maxId;
 	}

    /**
     * 批量插入
     *
     * @param list
     */
 	public void insertBatch(List<EtlDatabaseBuildExtModel> list) throws CoBusinessException {
        mDao.insertBatch(list);
    }

    /**
     * 通过uuid等信息删除
     *
     * @param model
     * @throws CoBusinessException
     */
    public void deleteByBuildUUid(EtlDatabaseBuildExtModel model) throws CoBusinessException {
        mDao.deleteByBuildUUid(model);
    }



}