package com.travel.dao.config.druid;

import com.alibaba.druid.filter.Filter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidArgs {
    public Integer initialSize;
    public Integer minIdle;
    public Integer maxActive;
    public Long maxWait;
    public Long timeBetweenEvictionRunsMillis;
    public Long minEvictableIdleTimeMillis;
    public String validationQuery;
    public Boolean testWhileIdle;
    public Boolean testOnBorrow;
    public Boolean testOnReturn;
    public Boolean poolPreparedStatements;
    public Integer maxPoolPreparedStatementPerConnectionSize;
    public String connectionProperties;
    public Boolean useGlobalDataSourceStat;
    public List<Filter> filters;

}
