package com.khsh.etl.service.impl;

import java.sql.SQLException;

import com.ejet.comm.utils.StringUtils;
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
import com.khsh.etl.model.EtlKettleRepositoryModel;
import com.khsh.etl.mapper.EtlKettleRepositoryDao;
import com.khsh.etl.service.IEtlKettleRepositoryService;
@Service("etlKettleRepositoryService")
public class EtlKettleRepositoryServiceImpl implements IEtlKettleRepositoryService { 


	private final Logger log = LoggerFactory.getLogger(EtlKettleRepositoryServiceImpl.class);

	@Autowired
	private EtlKettleRepositoryDao mDao;

	public void insertAutoKey(EtlKettleRepositoryModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(EtlKettleRepositoryModel model) throws CoBusinessException { 
 		if(model.getId()==null) { 
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(EtlKettleRepositoryModel model) throws CoBusinessException { 
 		 mDao.delete(model);
 	}  

	public EtlKettleRepositoryModel  findByPK(EtlKettleRepositoryModel model) throws CoBusinessException {
        if(model.getId()==null && model.getUuid()==null) {
            throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
        }
 		 return mDao.findByPK(model);
 	}  

	public List<EtlKettleRepositoryModel>  queryByCond(EtlKettleRepositoryModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<EtlKettleRepositoryModel>  queryByPage(EtlKettleRepositoryModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<EtlKettleRepositoryModel> list = mDao.queryByPage(model);
		PageBean<EtlKettleRepositoryModel> page = new PageBean<EtlKettleRepositoryModel>(list);
  
 		 return page;
 	}  

	public int insertSingle(EtlKettleRepositoryModel model) throws CoBusinessException {
	    if(StringUtils.isBlank(model.getKtlJobType())
            || StringUtils.isBlank(model.getRepType())
                || StringUtils.isBlank(model.getRepPath())) {
            throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
        }

	    if( !model.getKtlJobType().equalsIgnoreCase("KTR") && !model.getKtlJobType().equalsIgnoreCase("KJB") ) {
            throw new CoBusinessException("");
        }

        if(model.getUuid()==null) {
            model.setUuid(UuidUtils.get32UUID());
        }
        model.setModifyTime(TimeUtils.getCurrentFullTime());
        int maxId = mDao.insertAutoKey(model);
        return maxId;
 	}

    /**
     * 查询kettle任务及参数信息
     * @throws CoBusinessException
     */
    public List<com.khsh.etl.vo.bus.KettleJobParamVO> queryKettleJobParam(com.khsh.etl.vo.bus.KettleJobParamVO vo) throws CoBusinessException {
        return mDao.queryKettleJobParam(vo);
    }


}