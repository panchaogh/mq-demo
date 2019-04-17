package com.pcgrw.sbrabbit.config.database;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//MybatisDataSourceConfig类加载之后才能加载MybatisMapperScanerConfig类
@AutoConfigureAfter(MybatisDataSourceConfig.class)
public class MybatisMapperScanerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.pcgrw.sbrabbit.dao");
        return mapperScannerConfigurer;
    }
}
