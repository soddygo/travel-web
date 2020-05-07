package com.travel.dao.config.druid;

import com.google.common.collect.Maps;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by linzhou.lei on 2019/2/27.
 */
public class DataSourceArgsAware {

    public static DataSource makeDataSource(DataSourceArgs dsArgs, DruidArgs druidArgs) throws ReflectiveOperationException {
        if (dsArgs == null) {
            return null;
        }
        Map<String, Object> argsMap = getDataSourceInitialParamMap(dsArgs, druidArgs);
        return DataSourceUtil.getDataSource(dsArgs.getDataSourceClassName(), argsMap);
    }

    public static Map<String, Object> getDataSourceInitialParamMap(DataSourceArgs dsArgs, DruidArgs druidArgs) {
        Map<String, Object> map = Maps.newConcurrentMap();
        map.put(YamlDbConstant.driverClassName, dsArgs.getDriverClassName());
        map.put(YamlDbConstant.url, dsArgs.getUrl());
        map.put(YamlDbConstant.userName, dsArgs.getUsername());
        map.put(YamlDbConstant.password, dsArgs.getPassword());
        wrapDruidArgs(map, druidArgs);
        return map;
    }

    private static void wrapDruidArgs(Map<String, Object> map, DruidArgs druidArgs) {
        if (druidArgs == null) {
            return;
        }
        if (druidArgs.getInitialSize() != null) {
            map.put(YamlDbConstant.initialSize, druidArgs.getInitialSize());
        }
        if (druidArgs.getMinIdle() != null) {
            map.put(YamlDbConstant.minIdle, druidArgs.getMinIdle());
        }
        if (druidArgs.getMaxActive() != null) {
            map.put(YamlDbConstant.maxActive, druidArgs.getMaxActive());
        }
        if (druidArgs.getMaxWait() != null) {
            map.put(YamlDbConstant.maxWait, druidArgs.getMaxWait());
        }
        if (druidArgs.getTimeBetweenEvictionRunsMillis() != null) {
            map.put(YamlDbConstant.timeBetweenEvictionRunsMillis, druidArgs.getTimeBetweenEvictionRunsMillis());
        }
        if (druidArgs.getMinEvictableIdleTimeMillis() != null) {
            map.put(YamlDbConstant.minEvictableIdleTimeMillis, druidArgs.getMinEvictableIdleTimeMillis());
        }
        if (druidArgs.getValidationQuery() != null) {
            map.put(YamlDbConstant.validationQuery, druidArgs.getValidationQuery());
        }
        if (druidArgs.getTestWhileIdle() != null) {
            map.put(YamlDbConstant.testWhileIdle, druidArgs.getTestWhileIdle());
        }
        if (druidArgs.getTestOnBorrow() != null) {
            map.put(YamlDbConstant.testOnBorrow, druidArgs.getTestOnBorrow());
        }
        if (druidArgs.getTestOnReturn() != null) {
            map.put(YamlDbConstant.testOnReturn, druidArgs.getTestOnReturn());
        }
        if (druidArgs.getPoolPreparedStatements() != null) {
            map.put(YamlDbConstant.poolPreparedStatements, druidArgs.getPoolPreparedStatements());
        }
        if (druidArgs.getMaxPoolPreparedStatementPerConnectionSize() != null) {
            map.put(YamlDbConstant.maxPoolPreparedStatementPerConnectionSize, druidArgs.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (druidArgs.getConnectionProperties() != null) {
            map.put(YamlDbConstant.connectionProperties, druidArgs.getConnectionProperties());
        }
        if (druidArgs.getUseGlobalDataSourceStat() != null) {
            map.put(YamlDbConstant.useGlobalDataSourceStat, druidArgs.getUseGlobalDataSourceStat());
        }
        if (druidArgs.getFilters() != null) {
            map.put(YamlDbConstant.filters, druidArgs.getFilters());
        }
    }
}
