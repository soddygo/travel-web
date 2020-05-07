package com.travel.dao.config.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author soddy
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DataSourceArgs {

    private String dataSourceClassName;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

}
