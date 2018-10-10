package com.khsh.etl.databuilder.db.dialect;

/**
 * 表字段类型
 * 
 * sqlserver注意事项：
 * 		从 decimal 或 numeric 向 float 或 real 转换会导致精度损失。
 *      从 int、smallint、tinyint、float、real、money 或 smallmoney 向 decimal 或 numeric 转换会导致溢出。
 *      浮点数据为近似值float型变量在存入值时，有时值得大小会发生改变。
 *      float 和 real 数据类型被称为近似的数据类型。
 *      numeric 和 decimal 数据类型的默认最大精度值是 38
 *
 *      varchar(n) 长度为 n 个字节的可变长度且非 Unicode 的字符数据。n 必须是一个介于 1 和 8,000 之间的数值。存储大小为输入数据的字节的实际长度，而不是 n 个字节。
 *      nvarchar(n) 包含 n 个字符的可变长度 Unicode 字符数据。n 的值必须介于 1 与 4,000 之间。字节的存储大小是所输入字符个数的两倍。
 *
 *      bit类型，只有三个有效值：0，1 和 null，字符串true或false能够隐式转换为bit类型，true转换为1，false转换为0；任何非0的整数值转换成bit类型时，值都是1。
 *      sql_variant 是变长的数据类型，包含两部分信息：基础类型和Value，最多存储8000Byte的数据。
 *      sysname 与 nvarchar(128) 作用相同。SQL Server 6.5 或早期版本仅支持较小的标识符；因此，在早期版本中，sysname 被定义为 varchar(30)。
 *      sysname 是一个系统数据类型，用于定义表列、变量以及存储过程的参数，是nvarchar(128) 的同义词，当该类型用于定义table column时，SQL Server 会自动添加 not null ，等价于nvarchar(128) not null。
 *
 * mysql注意事项：
 * 		MySQL中没有指定的bool类型，一般都使用tinyint来代替,
 *      从 int、smallint、tinyint、float、real、money 或 smallmoney 向 decimal 或 numeric 转换会导致溢出。
 *      浮点数据为近似值float型变量在存入值时，有时值得大小会发生改变。
 *      float 和 real 数据类型被称为近似的数据类型。
 *      numeric 和 decimal 数据类型的默认最大精度值是 38
 *            
 *      
 * 
 */
public enum DbColumnTypeEnum {
    VARCHAR("VARCHAR","可变长度字符类型"),
    NVARCHAR("NVARCHAR","可变长度NVARCHAR类型"),
    CHAR("CHAR","字符类型"),
    BIGINT("BIGINT","长整形"),
    INT("INT","整形"),
    BINARY("BINARY","二进制字符串"),
    SMALLINT("SMALLINT","SMALLINT类型"),
    VARBINARY("VARBINARY","VARBINARY类型"), //
    SYSNAME("SYSNAME","sqlserver SYSNAME类型"), //

    BIT("BIT","二进制字符串"),
    TINYINT("TINYINT","MySql代替boolean"), //SQL SERVER的bit类型，对于零，识别为False，非零值识别为True。

    
    FLOAT("FLOAT","float浮点型"),
    REAL("REAL","REAL类型"), //float
    
    DOUBLE("DOUBLE","双精度double型"),

    MONEY("MONEY","精确数值型"), //float 默认转换为decimal(19,4)
    NCHAR("NCHAR","NCHAR字符类型"), //char SQL SERVER转MySQL按正常字节数转就可以
    NTEXT("NTEXT","NTEXT类型"), //text
    //numeric 和 decimal 数据类型的默认最大精度值是 38
    NUMERIC("NUMERIC","NUMERIC类型"), //decimal
    DECIMAL("DECIMAL","精确数值decimal型"),
    
    SMALLMONEY("SMALLMONEY","SMALLMONEY类型"), //float
    UNIQUEIDENTIFIER("UNIQUEIDENTIFIER","UNIQUEIDENTIFIER类型"), //对应mysql的UUID(),设置为文本类型即可。
    XML("XML","XML类型"), //对应mysql的text

   // ( 'bigint','bigint'),('binary','binary'),('binary','binary'),('bit','tinyint'),('char','char'),('date','date'),('datetime','datetime'),('datetime2','datetime'),('datetimeoffset','datetime'),('decimal','decimal'),('float','float'),('int','int'),('money','decimal'),('nchar','char'),('ntext','text'),('numeric','decimal'),('nvarchar','varchar'),('real','float'),('smalldatetime','datetime'),('smallint','smallint'),('smallmoney','decimal'),('text','text'),('time','time'),('timestamp','timestamp'),('tinyint','tinyint'),('uniqueidentifier','varchar(40)'),('varbinary','varbinary'),('varchar','varchar'),('xml','text')

    /**
     * MySQL数据类型
     * date	3字节，日期，格式：2014-09-18
     * time	3字节，时间，格式：08:42:30
     * datetime	8字节，日期时间，格式：2014-09-18 08:42:30
     * timestamp	4字节，自动存储记录修改的时间
     * year	1字节，年份
     */
    DATE("DATE","时间类型"),
    DATETIME("DATETIME","时间类型"),
    DATETIME2("DATETIME2","时间类型"),
    DATETIMEOFFSET("DATETIMEOFFSET","时间类型"),
    SMALLDATETIME("SMALLDATETIME","时间类型"),
    TIME("TIME","时间类型"),
    TIMESTAMP("TIMESTAMP","时间类型"),
 
    TIMESTAMP_WITH_TIME_ZONE("TIMESTAMP (9) WITH TIME ZONE","oracle时区类型"),
    
//    INTERVAL_DAY2_TOSECOND6("INTERVAL DAY(2) TO SECOND(6)","时间类型"),
//    INTERVAL_DAY2_TOSECOND6("INTERVAL DAY(2) TO SECOND(6)","时间类型"),

    /**
     * MySQL数据类型
        tinytext	可变长度，最多255个字符
        text	可变长度，最多65535个字符
        mediumtext	可变长度，最多2的24次方-1个字符
        longtext	可变长度，最多2的32次方-1个字符
     SQLServer中
     text：字符型，存储大量的非统一编码型字符数据
     nchar：统一编码字符型，存储定长统一编码字符型数据，能存储4000种字符，统一编码用双字节结构来存储每个字符
     nvarchar：统一编码字符型，用作变长的统一编码字符型数据
     ntext：统一编码字符型，用来存储大量的统一编码字符型数据
     binary：二进制数据类型，存储可达8000 字节长的定长的二进制数据
     varbinary：二进制数据类型，用来存储可达8000 字节长的变长的二进制数据
    */
    TEXT("TEXT", "可变长度Text字符文本"),
    MEDIUMTEXT("MEDIUMTEXT", "可变长度MEDIUMTEXT字符文本"),//最大长度 16777215 个字元(2^24-1)

    /**
     * Sqlserver唯一标示
     */
    GUID("GUID", "特殊数据型，存储一个全局唯一标识符，即GUID"),
    IMAGE("IMAGE", "image类型"),
    GEOGRAPHY("GEOGRAPHY", "geography类型"),
    GEOMETRY("GEOMETRY", "geometry类型"),
    HIERARCHYID("HIERARCHYID", "hierarchyid类型"),
    SQL_VARIANT("SQL_VARIANT", "sql_variant类型"),

    // oracle
    NUMBER("NUMBER", "Oracle中NUMBER类型"),
    NVARCHAR2("NVARCHAR2", "Oracle中NVARCHAR2类型"),
    VARCHAR2("VARCHAR2", "Oracle中VARCHAR2类型"),
    XMLTYPE("XMLTYPE", "Oracle中XMLTYPE类型"),

    CLOB("CLOB", "oracle CLOB类型"),
    NCLOB("NCLOB", "oracle NCLOB类型"),
    RAW("RAW", "oracle RAW类型"),
    LONGBLOB("LONGBLOB", "Mysql LONGBLOB类型"),
    TINYBLOB("TINYBLOB", "Mysql TinyBlob类型"),
    BLOB("BLOB", "Mysql BLOB类型");


    private String name;
    private String desc;

    private DbColumnTypeEnum(String name,String desc){
        this.name = name;
        this.desc = desc;
    }


    /**
     * 根据名字获得对应的数据类型
     *
     * @param name
     * @return
     */
    public static DbColumnTypeEnum getColTypeByname(String name) {
        if(name == null || "".equals(name.trim())){
            return null;
        }
        for (DbColumnTypeEnum c : DbColumnTypeEnum.values()) {
            if(c.getName().toLowerCase().equals(name.trim().toLowerCase())){
                return c;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }



}
