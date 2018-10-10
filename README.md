# etl-kettle  
springboot工程，基于kettle的ETL抽取封装。   
包含几个模块：   
1、基础类模块、任务调度模块。 (comm-web.jar、comm-web-quartz.jar)  
2、数据库构建模块。 (etl-data-builder模块)   
3、kettle封装模块。 (etl-kettle-kit模块)   
4、日志监控模块。  

gradle命令打包： 
gradle clean build -x test  

优化启动springboot包。
java -server -Xms512m -Xmx4096m  -jar springboot-1.0.jar