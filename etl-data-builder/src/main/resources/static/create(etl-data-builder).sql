
-- -----------------------------------------------
-- 数据库配置表 `etl_database`
-- -----------------------------------------------
DROP TABLE IF EXISTS `etl_database`;
CREATE TABLE `etl_database` (
       `id`          		  int(11) 			  NOT NULL 					        AUTO_INCREMENT ,
       `uuid`  			      varchar(64)			NOT NULL  					      COMMENT '库ID' ,
       `name`  			      varchar(200)		NOT NULL  					      COMMENT '库名称' ,
       `db_driver`  			varchar(64)			NOT NULL                  COMMENT '驱动名称',
       `db_url`  	        varchar(400) 		NULL DEFAULT NULL     		COMMENT '数据库url',
       `db_username`  	  varchar(64) 		NULL DEFAULT NULL     		COMMENT '用户名',
       `db_password`  	  varchar(64) 		NULL DEFAULT NULL     		COMMENT '密码',
       `status`  				  tinyint(1) 			NOT NULL  DEFAULT '1'   	COMMENT '状态, 1: 正常，0：禁用',
       `remark`  				   varchar(100) 	NULL DEFAULT NULL  			  COMMENT '备注,描述' ,
       `modify_time`  		 varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改时间',
       `modify_user`  		 varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改人' ,
       `ext`  					   varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext1`  				     varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext2`  				     varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       PRIMARY KEY (`id`),
       UNIQUE INDEX `uuid` (`uuid`) USING BTREE,
       INDEX `name` (`name`) USING BTREE
)  comment='数据库信息表'
 ENGINE=InnoDB
 DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- 同步信息表 `etl_database_build`
-- ----------------------------
DROP TABLE IF EXISTS `etl_database_build`;
CREATE TABLE `etl_database_build` (
       `id`          		  int(11) 			  NOT NULL 					        AUTO_INCREMENT ,
       `uuid`  			      varchar(64)			NOT NULL  					      COMMENT '标识ID' ,
        `name`  			    varchar(200)		NOT NULL  					      COMMENT '名称' ,
       `database_uuid_from` varchar(64)			NOT NULL  					      COMMENT '源库ID，引用etl_database' ,
       `database_uuid_to`  	varchar(64) 		NOT NULL  					      COMMENT '目标源库ID，引用etl_database',
       `table_prefix`  	  varchar(32) 		NULL  					          COMMENT '目标表前缀',
       `ignore_tables`  	text 		        NULL  					          COMMENT '忽略表，中间用逗号(,)隔开',
       `status`  				  tinyint(1) 			NOT NULL  DEFAULT '1'   	COMMENT '状态, 1: 正常，0：禁用',
       `remark`  				   varchar(100) 	NULL DEFAULT NULL  			  COMMENT '备注,描述' ,
       `modify_time`  		 varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改时间',
       `modify_user`  		 varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改人' ,
       `ext`  					   varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext1`  				     varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext2`  				     varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       PRIMARY KEY (`id`),
       UNIQUE INDEX `database_uuid_from_to` (`database_uuid_from`, `database_uuid_to`, `status`) USING BTREE
)  comment='同步信息表'
 ENGINE=InnoDB
 DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;


-- ----------------------------
-- 同步信息扩展数据表 `etl_database_build_ext`
-- ----------------------------
DROP TABLE IF EXISTS `etl_database_build_ext`;
CREATE TABLE `etl_database_build_ext` (
       `id`          		  int(11) 			  NOT NULL 					        AUTO_INCREMENT ,
       `build_uuid`  			  varchar(64)			NOT NULL  					      COMMENT '表etl_database_build标识ID' ,
       `column_name`  		varchar(32)			NOT NULL  					      COMMENT '新增表字段名',
       `column_type`  		varchar(32)			NOT NULL  					      COMMENT '字段类型',
       `column_length`  	int(32)			    NULL  					          COMMENT '字段长度',
       `column_scale`  	  int(32)			    NULL  					          COMMENT '字段小数点位数',
       `status`  				  tinyint(1) 			NOT NULL  DEFAULT '1'   	COMMENT '状态, 1: 正常，0：禁用',
       `remark`  				  varchar(100) 	  NULL DEFAULT NULL  			  COMMENT '备注,描述' ,
       `modify_time`  		varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改时间',
       `modify_user`  		varchar(32) 		NULL DEFAULT NULL  			  COMMENT '修改人' ,
       `ext`  					  varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext1`  				    varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       `ext2`  				    varchar(100)		NULL DEFAULT NULL     		COMMENT '预留字段',
       PRIMARY KEY (`id`),
       UNIQUE INDEX `build_uuid_column_name` (`build_uuid`, `column_name`) USING BTREE
)  comment='同步信息扩展数据表'
 ENGINE=InnoDB
 DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;


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