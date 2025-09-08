package com.sist.ex0908_bbs.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.sist.ex0908_bbs.mapper")
public class DbConfig {
    
    @Bean
    public SqlSessionFactory getFactory(DataSource ds) throws Exception {
        //SqlSessionFactory를 생성하는 객체 준비
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);

        //mapper폴더에 있는 xml파일들을 읽어들인다.
        PathMatchingResourcePatternResolver resolver = 
                        new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(
            resolver.getResources("classpath:mapper/**/*.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate getTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
