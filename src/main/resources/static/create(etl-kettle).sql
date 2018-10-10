-- ----------------------------
-- kettle作业配置表 `etl_kettle_repository`
-- param_value字段：采用json，key：value键值对方式，可直接转map进行处理
-- 比如：{"tableName":"sys_user", "fetchNum": 1000}
-- ----------------------------
DROP TABLE IF EXISTS `etl_kettle_repository`;
CREATE TABLE `etl_kettle_repository` (
       `id`          		  int(11) 			  NOT NULL 					        AUTO_INCREMENT ,
       `ktl_job_uuid`  	  varchar(64)			NOT NULL  					      COMMENT 'kette作业id' ,
       `ktl_job_type`  		varchar(32)			NOT NULL  					      COMMENT 'kette作业类型: KJB\KTR' ,
       `ktl_job_name`  		varchar(64)			NOT NULL  					      COMMENT '作业名称',
       `rep_type`  		    varchar(32)			NOT NULL  					      COMMENT '资源类型: DB\FILE',
       `rep_path`  	      varchar(400)		NOT NULL  					      COMMENT '资源路径',
        `ktl_param_value` varchar(2000)		NULL DEFAULT NULL     		COMMENT 'kettle参数，采用json，key：value键值对' ,
       `status`  				  tinyint(1) 			NOT NULL  DEFAULT '1'   	COMMENT '状态, 1: 正常，0：禁用',
       `remark`  				  varchar(100) 	  NULL DEFAULT NULL  			  COMMENT '备注,描述' ,
       `modify_time`  		varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改时间',
       `modify_user`  		varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改人' ,
       `ext`  					  varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext1`  				    varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext2`  				    varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       PRIMARY KEY (`id`),
       UNIQUE INDEX `ktl_job_uuid` (`ktl_job_uuid`) USING BTREE
)  comment='kettle作业配置表'
 ENGINE=InnoDB
 DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;