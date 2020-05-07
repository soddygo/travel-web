package com.travel.dao.config.druid;


public final class YamlDbConstant {

    public static final String DS_MASTER = "master";
    public static final String DS_SLAVE = "slave";

    public static final String NORMAL_MASTER = "normal_master";
    public static final String NORMAL_SLAVE = "normal_slave";
    public static final String SHARDING = "sharding";

    public static final String ROUND_ROBIN = "ROUND_ROBIN";

    public static final String driverClassName = "driverClassName";

    //druid
    public static final String RANDOM = "RANDOM";
    public static final String url = "url";
    public static final String userName = "username";
    public static final String password = "password";
    public static final String initialSize = "initialSize";
    public static final String minIdle = "minIdle";
    public static final String maxActive = "maxActive";
    public static final String maxWait = "maxWait";
    public static final String timeBetweenEvictionRunsMillis = "timeBetweenEvictionRunsMillis";
    public static final String minEvictableIdleTimeMillis = "minEvictableIdleTimeMillis";
    public static final String validationQuery = "validationQuery";
    public static final String testWhileIdle = "testWhileIdle";
    public static final String testOnBorrow = "testOnBorrow";
    public static final String testOnReturn = "testOnReturn";
    public static final String poolPreparedStatements = "poolPreparedStatements";
    public static final String maxPoolPreparedStatementPerConnectionSize = "maxPoolPreparedStatementPerConnectionSize";
    public static final String connectionProperties = "connectionProperties";
    public static final String useGlobalDataSourceStat = "useGlobalDataSourceStat";
    public static final String filters = "filters";

}