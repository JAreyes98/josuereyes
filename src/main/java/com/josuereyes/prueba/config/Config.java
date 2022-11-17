package com.josuereyes.prueba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {


    @Value("${usr}")
    private String user;

    @Value("${cls}")
    private String pass;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder =DataSourceBuilder.create();
        builder.url("jdbc:sqlserver://192.168.10.136;databaseName=Northwind;trustServerCertificate=true");
//        builder.username("UCEM_IRENE");
//        builder.password("1234");
        builder.username(user);
        builder.password(pass);
        return builder.build();
    }

}
