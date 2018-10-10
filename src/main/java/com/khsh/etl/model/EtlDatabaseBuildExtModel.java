package com.khsh.etl.model;

import com.ejet.comm.base.CoBaseVO;
public class EtlDatabaseBuildExtModel extends CoBaseVO { 

	/**    */ 
 	private Integer id;
	/**  表etl_database_build标识ID  */ 
 	private String buildUuid;
	/**  新增表字段名  */ 
 	private String columnName;
	/**  字段类型  */ 
 	private String columnType;
	/**  字段长度  */ 
 	private Integer columnLength;
	/**  字段小数点位数  */ 
 	private Integer columnScale;
	/**  状态, 1: 正常，0：禁用  */ 
 	private Integer status;
	/**  备注,描述  */ 
 	private String remark;
	/**  修改时间  */ 
 	private String modifyTime;
	/**  修改人  */ 
 	private String modifyUser;
	/**  预留字段  */ 
 	private String ext;
	/**  预留字段  */ 
 	private String ext1;
	/**  预留字段  */ 
 	private String ext2;

	public void setId(Integer id) {
		this.id=id;
	} 

	public Integer getId(){
		return id;
	}

	public void setBuildUuid(String buildUuid) {
		this.buildUuid=buildUuid;
	} 

	public String getBuildUuid(){
		return buildUuid;
	}

	public void setColumnName(String columnName) {
		this.columnName=columnName;
	} 

	public String getColumnName(){
		return columnName;
	}

	public void setColumnType(String columnType) {
		this.columnType=columnType;
	} 

	public String getColumnType(){
		return columnType;
	}

	public void setColumnLength(Integer columnLength) {
		this.columnLength=columnLength;
	} 

	public Integer getColumnLength(){
		return columnLength;
	}

	public void setColumnScale(Integer columnScale) {
		this.columnScale=columnScale;
	} 

	public Integer getColumnScale(){
		return columnScale;
	}

	public void setStatus(Integer status) {
		this.status=status;
	} 

	public Integer getStatus(){
		return status;
	}

	public void setRemark(String remark) {
		this.remark=remark;
	} 

	public String getRemark(){
		return remark;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime=modifyTime;
	} 

	public String getModifyTime(){
		return modifyTime;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser=modifyUser;
	} 

	public String getModifyUser(){
		return modifyUser;
	}

	public void setExt(String ext) {
		this.ext=ext;
	} 

	public String getExt(){
		return ext;
	}

	public void setExt1(String ext1) {
		this.ext1=ext1;
	} 

	public String getExt1(){
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2=ext2;
	} 

	public String getExt2(){
		return ext2;
	}


}