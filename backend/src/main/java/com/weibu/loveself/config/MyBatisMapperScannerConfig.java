package com.weibu.loveself.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer，
 */
@Configuration
@AutoConfigureAfter(MyBatisBaseConfig.class)
public class MyBatisMapperScannerConfig {
    /** BaseMapper路径 */
    private final static String baseMapperInterface = "com.weibu.loveself.common.IBaseMybatisMapper";
    /** mapper类的包路径 */
    private final static String userMapperPackage = "com.weibu.loveself.mapper";

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(userMapperPackage);
        Properties properties = new Properties();
        properties.setProperty("mappers", baseMapperInterface);
        properties.setProperty("notEmpty", "false");
        properties.setProperty("identity", "mysql");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
