##  kettle同步数据

kettle测试  
1、查询任务  
http://127.0.0.1:8080/etl/sys_job_schedule/query_by_page  

2、停止任务  
http://127.0.0.1:8080/etl/sys_job_schedule/pause_job  

3、启动任务  
http://127.0.0.1:8080/etl/sys_job_schedule/start_job

4、监控运行任务  
http://127.0.0.1:8080/etl/sys_job_schedule/get_running_job  

linux获取本机内网IP：
/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:"

windows获取本机内网IP：
