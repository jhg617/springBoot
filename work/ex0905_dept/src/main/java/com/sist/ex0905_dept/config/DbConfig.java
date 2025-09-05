package com.sist.ex0905_dept.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.ex0905_dept.mapper") //DeptMapper의 패키지경로
public class DbConfig {
    // 자동으로 스프링환경이 한번 호출한다.

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource ds) throws Exception{
        //위의 @Bean이라고 명시했기 때문에 빈객체를 만들기 위해 한번 호출함!
        // SqlSessionFactory를 생성하는 객체를 만들자
        SqlSessionFactoryBean sBean = new SqlSessionFactoryBean();

        sBean.setDataSource(ds);

        // SQL문장들(mapper)을 인식하기 위해 mapper들이 있는 위치를 지정하자!
        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        //getResource가 아니고 getResources를 선택해야함
        sBean.setMapperLocations(resolver.getResources(
                "classpath:mybatis/mapper/**/*.xml")); //mapper 하위 모든 파일들을 인식함

        return sBean.getObject(); //SqlSessionFactory 반환
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }
}
