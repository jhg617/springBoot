package com.sist.ex0905_emp.mybatis.mapper;

import com.sist.ex0905_emp.mybatis.vo.EmpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
    // SQL문을 가진 mapper 파일(emp.xml)과 연동된다.
    // 그래서 여기에 정의하는 함술들은 emp.xml에 존재하는
    // id명과 동일해야 한다.
    List<EmpVO> all(); // all이라는 id를 의미한다.
                        // all은 여러개를 반환할 가능성이 있기 때문에 제네릭타입은 List이며,
                        // 반환형은 EmpVO이다!
}
