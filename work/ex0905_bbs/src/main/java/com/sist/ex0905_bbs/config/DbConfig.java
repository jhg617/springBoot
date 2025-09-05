package com.sist.ex0905_bbs.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.ex0905_bbs.mapper")
public class DbConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource ds) throws Exception {

        SqlSessionFactoryBean sBean = new SqlSessionFactoryBean();
        sBean.setDataSource(ds);

        // SQL문장들(mapper)을 인식하기 위해 mapper들이 있는 위치를 지정하자!
        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        sBean.setMapperLocations(resolver.getResources(
                "classpath:mybatis/mapper/**/*.xml"));

        return sBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
