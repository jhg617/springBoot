package com.sist.ex0930_emp.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.sist.ex0930_emp.mybatis.mapper") //인터페이스의 경로
public class DbConfig {
    // 현재 클래스는 스프링부트 환경이 알아서 호출하며, 아래의
    // @Bean 이라는 어노테이션 때문에
    // 강제로 함수들을 호출하여 한번씩 호출하여 스프링환경(Context)에
    // 반환되어 객체들을 등록한다.

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        // @Configuration으로 인해 application.properties에 있는 Datasource를 받아온다.
        // 반환객체인 SqlSessionFactory를 만드는 객체를 생성하자!
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 생성된 factoryBean은 비어있는 상태다. 이때
        // 인자로 받은 ds를 factoryBean에게 지정한다.
        factoryBean.setDataSource(ds);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //resolver.getResources("classpath:mybatis/mapper/**/*.xml"); // ** : mapper 하위의 서브폴더에 있는 xml도 포함시킨다.

        // SQL문장들을 가진 mapper들 지정
        factoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/**/*.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
