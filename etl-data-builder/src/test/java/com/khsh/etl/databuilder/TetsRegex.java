package com.khsh.etl.databuilder;

/**
 * Copyright (C), 2016-2018, 武汉康华数海有限公司
 * FileName: TetsRegex
 * Author:   Ejet
 * CreateDate:     2018-09-10 18:09
 * Description:
 * History:
 * Version: 1.0
 */
public class TetsRegex {


    public static void main(String args[]) {
        // 格式一: Oracle JDBC Thin using an SID:
        // jdbc:oracle:thin:@host:port:SID
        // Example: jdbc:oracle:thin:@localhost:1521:orcl
        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        // 格式二: Oracle JDBC Thin using a ServiceName:
        // jdbc:oracle:thin:@//host:port/service_name
        // Example:jdbc:oracle:thin:@//localhost:1521/orcl.city.com
        //String dbURL = "jdbc:oracle:thin:@//localhost:1521/orcl.city.com";
        //注意这里的格式，@后面有//, port后面:换成了/,这种格式是Oracle 推荐的格式，
        // 因为对于集群来说，每个节点的SID 是不一样的，但是SERVICE_NAME 确可以包含所有节点。

        // 格式三：Oracle JDBC Thin using a TNSName:
        // jdbc:oracle:thin:@TNSName
        // Example: jdbc:oracle:thin:@TNS_ALIAS_NAME
        //要实现这种连接方式首先要建立tnsnames.ora文件，然后通过System.setProperty指明这个文件路径。再通过上面URL中的@符号指定文件中的要使用到的资源。
        String schema = dbURL.split("@")[1];
        int index = schema.lastIndexOf("/");
        schema = index==-1 ? schema : schema.substring(index+1, schema.length());
        index = schema.lastIndexOf(":");
        schema = index==-1 ? schema : schema.substring(index+1, schema.length());
        index = schema.indexOf(".");
        schema = index==-1 ? schema : schema.substring(0, index);
        System.out.println(schema);



    }

}
