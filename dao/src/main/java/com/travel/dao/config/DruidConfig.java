package com.travel.dao.config;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import com.travel.dao.config.druid.DataSourceArgs;
import com.travel.dao.config.druid.DataSourceArgsAware;
import com.travel.dao.config.druid.DruidArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {

    @Autowired
    private DataSourceArgs dataSourceArgs;

    @Autowired
    private DruidArgs druidArgs;

    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() throws ReflectiveOperationException {
        DataSource dataSource = DataSourceArgsAware.makeDataSource(dataSourceArgs, druidArgs);
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean<Servlet> druidServlet() {
        // 进行 druid 监控的配置处理
        ServletRegistrationBean<Servlet> srb = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/**");
        // 白名单
        srb.addInitParameter("allow", "127.0.0.1");
        // 黑名单
        srb.addInitParameter("deny", "192.168.31.253");
        // 用户名
        srb.addInitParameter("loginUsername", "root");
        // 密码
        srb.addInitParameter("loginPassword", "root");
        // 是否可以重置数据源
        srb.addInitParameter("resetEnable", "false");
        return srb;
    }

//    @Bean
//    public FilterRegistrationBean<Filter> filterRegistrationBean() {
//        FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
//        frb.setFilter(new WebStatFilter());
//        // 所有请求进行监控处理
//        frb.addUrlPatterns("/*");
//        // 排除名单
//        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
//        return frb;
//    }
}
