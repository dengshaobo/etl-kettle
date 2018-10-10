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
import com.khsh.etl.model.SysJobLogModel;
import com.khsh.etl.mapper.SysJobLogDao;
import com.khsh.etl.service.ISysJobLogService;
@Service("sysJobLogService")
public class SysJobLogServiceImpl implements ISysJobLogService { 


	private final Logger log = LoggerFactory.getLogger(SysJobLogServiceImpl.class);

	@Autowired
	private SysJobLogDao mDao;

	public void insertAutoKey(SysJobLogModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(SysJobLogModel model) throws CoBusinessException { 
 		if(model.getId()==null) { 
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(SysJobLogModel model) throws CoBusinessException { 
 		 mDao.delete(model);
 	}  

	public SysJobLogModel  findByPK(SysJobLogModel model) throws CoBusinessException { 
 		 return mDao.findByPK(model);
 	}  

	public List<SysJobLogModel>  queryByCond(SysJobLogModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<SysJobLogModel>  queryByPage(SysJobLogModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<SysJobLogModel> list = mDao.queryByPage(model);
		PageBean<SysJobLogModel> page = new PageBean<SysJobLogModel>(list);
  
 		 return page;
 	}  

	public int insertSingle(SysJobLogModel model) throws CoBusinessException { 
 		// 获取最大id。保证连续性
 		Integer maxId = mDao.findMaxId(null);
 		maxId = maxId==null? 1 : maxId+1;
 		model.setId(maxId);
 		mDao.insertSingle(model);
 		return maxId;
 	}


}