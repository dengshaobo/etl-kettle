package com.khsh.etl.controller;

import com.alibaba.fastjson.TypeReference;
import com.ejet.comm.PageBean;
import com.ejet.comm.Param;
import com.ejet.comm.Result;
import com.ejet.comm.base.CoBaseController;
import com.ejet.comm.exception.CoBusinessException;
import com.khsh.etl.model.SysJobScheduleModel;
import com.khsh.etl.service.impl.SysJobScheduleServiceImpl;
import com.khsh.etl.vo.SysJobScheduleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ejet.comm.exception.ExceptionCode.SYS_ERROR;
@RestController
@RequestMapping(value="/sys-job-schedule")
public class SysJobScheduleController extends CoBaseController { 

	private final Logger log = LoggerFactory.getLogger(SysJobScheduleController.class);
	@Autowired
	private SysJobScheduleServiceImpl mService;


	@ResponseBody
	@RequestMapping(value="/query")
	public Result query(@RequestBody(required=false)SysJobScheduleVO model) {
		Result rs = new Result();
		try {
			List<SysJobScheduleModel> page = mService.queryByCond(model);
			rs = new Result(page);
		}catch (CoBusinessException e) {
			log.error("", e);
			rs = new Result(e.getCode(), e);
		}
		return rs;
	}


	@ResponseBody
	@RequestMapping(value="/delete")
	public Result delete(@RequestBody(required=true)SysJobScheduleVO model) {
		Result rs = new Result();
		try{
			mService.delete(model);
		}catch (CoBusinessException e) {
			log.error("", e);
			rs = new Result(e.getCode(), e);
		}
		return rs;
	}


	@ResponseBody
	@RequestMapping(value="/add")
	public Result add(@RequestBody(required=true)SysJobScheduleVO model) {
		Result rs = new Result();
		try{
			mService.insertSingle(model);
		}catch (CoBusinessException e) {
			log.error("", e);
			rs = new Result(e.getCode(), e);
		}
		return rs;
	}


	@ResponseBody
	@RequestMapping(value="/update")
	public Result update(@RequestBody(required=true)SysJobScheduleVO model) {
		Result rs = new Result();
		try{
			mService.update(model);
		}catch (CoBusinessException e) {
			log.error("", e);
			rs = new Result(e.getCode(), e);
		}
		return rs;
	}


	@ResponseBody
	@RequestMapping(value="/query-by-page")
	public Result queryByPage(@RequestBody(required=true)Param param, BindingResult bindResult) {
		Result rs = new Result();
		try{
			checkBindResult(bindResult);
			SysJobScheduleVO model = toBean(param, new TypeReference<SysJobScheduleVO>(){});
			if(param.getPage()==null) {
			    throw new CoBusinessException(SYS_ERROR, "page对象为空!");
            }
			PageBean<SysJobScheduleModel> pageBean = mService.queryByPage(model, param.getPage().getPageNum(), param.getPage().getPageSize());
			rs = new Result(pageBean.getPage(), pageBean.getResult());
		}catch (CoBusinessException e) {
			log.error("", e);
			rs = new Result(e.getCode(), e);
		}catch (Exception e) {
			log.error("", e);
			rs = new Result(SYS_ERROR, e);
		}
		return rs;
	}


}