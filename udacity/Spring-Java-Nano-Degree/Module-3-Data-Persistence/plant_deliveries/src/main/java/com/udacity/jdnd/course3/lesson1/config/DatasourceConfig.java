package com.udacity.jdnd.course3.lesson1.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    // load properties from application.properties found with prefix "com.udacity.datasource" into this bean
    @ConfigurationProperties(prefix="com.udacity.datasource")
    public DataSource getDatasource() {
        DataSourceBuilder dsb = DataSourceBuilder.create();
        //dsb.username("sa2");
        //dsb.password(securePasswordService());
        dsb.url("jdbc:mysql://localhost:3306/plant");
        return dsb.build();
    }

    // private String securePasswordService() {
    //     return "sa1234";
    // }
}
