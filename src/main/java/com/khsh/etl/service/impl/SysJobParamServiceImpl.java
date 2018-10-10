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
import com.khsh.etl.model.SysJobParamModel;
import com.khsh.etl.mapper.SysJobParamDao;
import com.khsh.etl.service.ISysJobParamService;
@Service("sysJobParamService")
public class SysJobParamServiceImpl implements ISysJobParamService { 


	private final Logger log = LoggerFactory.getLogger(SysJobParamServiceImpl.class);

	@Autowired
	private SysJobParamDao mDao;

	public void insertAutoKey(SysJobParamModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(SysJobParamModel model) throws CoBusinessException { 
 		if(model.getId()==null) { 
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(SysJobParamModel model) throws CoBusinessException { 
 		 mDao.delete(model);
 	}  

	public SysJobParamModel  findByPK(SysJobParamModel model) throws CoBusinessException { 
 		 return mDao.findByPK(model);
 	}  

	public List<SysJobParamModel>  queryByCond(SysJobParamModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<SysJobParamModel>  queryByPage(SysJobParamModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<SysJobParamModel> list = mDao.queryByPage(model);
		PageBean<SysJobParamModel> page = new PageBean<SysJobParamModel>(list);
  
 		 return page;
 	}  

	public int insertSingle(SysJobParamModel model) throws CoBusinessException { 
 		// 获取最大id。保证连续性
 		Integer maxId = mDao.findMaxId(null);
 		maxId = maxId==null? 1 : maxId+1;
 		model.setId(maxId);
 		mDao.insertSingle(model);
 		return maxId;
 	}


}