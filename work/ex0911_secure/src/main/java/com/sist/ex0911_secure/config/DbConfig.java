package com.sist.ex0911_secure.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.sist.ex0911_secure.mapper")
public class DbConfig {
    
    @Bean
    public SqlSessionFactory sessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean factoryBean =
            new SqlSessionFactoryBean(); //기본생성자로 생성

        factoryBean.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver = 
            new PathMatchingResourcePatternResolver();

        Resource[] resource = resolver.getResources("classpath:mapper/**/*.xml"); //xml이 여러개이기 때문에 사용
        factoryBean.setMapperLocations(resource);

        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
