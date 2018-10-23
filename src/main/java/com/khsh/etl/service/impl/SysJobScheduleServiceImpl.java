package com.khsh.etl.service.impl;

import java.sql.SQLException;

import com.ejet.comm.quartz.JobSchedulerManager;
import com.ejet.comm.utils.time.TimeUtils;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.ejet.comm.exception.ExceptionCode;
import com.ejet.comm.PageBean;
import com.github.pagehelper.PageHelper;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobScheduleModel;
import com.khsh.etl.mapper.SysJobScheduleDao;
import com.khsh.etl.service.ISysJobScheduleService;
@Service("sysJobScheduleService")
public class SysJobScheduleServiceImpl implements ISysJobScheduleService { 


	private final Logger log = LoggerFactory.getLogger(SysJobScheduleServiceImpl.class);

	@Autowired
	private SysJobScheduleDao mDao;

	public void insertAutoKey(SysJobScheduleModel model) throws CoBusinessException { 
 		 mDao.insertAutoKey(model);
 	}  

	public void update(SysJobScheduleModel model) throws CoBusinessException { 
 		if(model.getId()==null && model.getJobId()==null) {
 			throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
 		}
 		 mDao.update(model);
 	}  

	public void delete(SysJobScheduleModel model) throws CoBusinessException { 
 		 mDao.delete(model);
 	}  

	public SysJobScheduleModel  findByPK(SysJobScheduleModel model) throws CoBusinessException {
        if(model.getId()==null && model.getJobId()==null) {
            throw new CoBusinessException(ExceptionCode.PARAM_MISSING_ID);
        }
 		 return mDao.findByPK(model);
 	}  

	public List<SysJobScheduleModel>  queryByCond(SysJobScheduleModel model) throws CoBusinessException { 
 		 return mDao.queryByCond(model);
 	}  

	public PageBean<SysJobScheduleModel>  queryByPage(SysJobScheduleModel model, Integer pageNum, Integer pageSize) throws CoBusinessException { 
		PageHelper.startPage(pageNum, pageSize);
		List<SysJobScheduleModel> list = mDao.queryByPage(model);
		PageBean<SysJobScheduleModel> page = new PageBean<SysJobScheduleModel>(list);
  
 		 return page;
 	}  

	public int insertSingle(SysJobScheduleModel model) throws CoBusinessException { 
 		// 获取最大id。保证连续性
 		Integer maxId = mDao.findMaxId(null);
 		maxId = maxId==null? 1 : maxId+1;
 		model.setId(maxId);
 		mDao.insertSingle(model);
 		return maxId;
 	}



    /**
     * 暂停、停止任务
     *
     * @param model
     * @throws CoBusinessException
     */
    public void pauseJob(SysJobScheduleModel model) throws CoBusinessException {
        // TODO Auto-generated method stub
        try {
            SysJobScheduleModel result = findByPK(model);
            if(result==null || result.getStatus()==null || result.getIsTask()==null) {
                return;
            }
            if(result.getIsTask()== JobSchedulerManager.IS_TASK_PERIOD) {
                Trigger.TriggerState state = JobSchedulerManager.getInstance().pauseScheduleJob(result);
                result.setJobState(state.toString());
            } else {
                JobSchedulerManager.getInstance().pauseJob(result);
                result.setJobState("PAUSED");
            }
            String time = TimeUtils.getCurrentShortTime();
            result.setLastDo(time);
            result.setModifyTime(time);
            mDao.update(result);
        } catch (SchedulerException e) {
            throw new CoBusinessException(ExceptionCode.SYS_ERROR, e);
        }
    }


    /**
     * 恢复、启动任务
     *
     * @param model
     * @throws CoBusinessException
     */
    public void startJob(SysJobScheduleModel model) throws CoBusinessException {
        // TODO Auto-generated method stub
        SysJobScheduleModel result = findByPK(model);
        try {
            if(result==null || result.getStatus()==null || result.getIsTask()==null) {
                return;
            }
            if(result.getIsTask()==JobSchedulerManager.IS_TASK_PERIOD) {
                Trigger.TriggerState state = JobSchedulerManager.getInstance().resumeScheduleJob(result);
                result.setJobState(state.toString());
            } else {
                result.setJobState("NORMAL");
                JobSchedulerManager.getInstance().resumeJob(result);
            }
            String time = TimeUtils.getCurrentShortTime();
            result.setLastDo(time);
            result.setModifyTime(time);
            mDao.update(result);
        } catch (SchedulerException e) {
            throw new CoBusinessException(ExceptionCode.SYS_ERROR, e);
        }
    }




}