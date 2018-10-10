package com.khsh.etl.model;

import com.ejet.comm.base.CoBaseVO;
public class EtlDatabaseBuildModel extends CoBaseVO { 

	/**    */ 
 	private Integer id;
	/**  标识ID  */ 
 	private String uuid;
	/**  名称  */ 
 	private String name;
	/**  源库ID，引用etl_database  */ 
 	private String databaseUuidFrom;
	/**  目标源库ID，引用etl_database  */ 
 	private String databaseUuidTo;
	/**  目标表前缀  */ 
 	private String tablePrefix;
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
	/**  忽略表，中间用逗号(,)隔开  */ 
 	private String ignoreTables;

	public void setId(Integer id) {
		this.id=id;
	} 

	public Integer getId(){
		return id;
	}

	public void setUuid(String uuid) {
		this.uuid=uuid;
	} 

	public String getUuid(){
		return uuid;
	}

	public void setName(String name) {
		this.name=name;
	} 

	public String getName(){
		return name;
	}

	public void setDatabaseUuidFrom(String databaseUuidFrom) {
		this.databaseUuidFrom=databaseUuidFrom;
	} 

	public String getDatabaseUuidFrom(){
		return databaseUuidFrom;
	}

	public void setDatabaseUuidTo(String databaseUuidTo) {
		this.databaseUuidTo=databaseUuidTo;
	} 

	public String getDatabaseUuidTo(){
		return databaseUuidTo;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix=tablePrefix;
	} 

	public String getTablePrefix(){
		return tablePrefix;
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

	public void setIgnoreTables(String ignoreTables) {
		this.ignoreTables=ignoreTables;
	} 

	public String getIgnoreTables(){
		return ignoreTables;
	}


}