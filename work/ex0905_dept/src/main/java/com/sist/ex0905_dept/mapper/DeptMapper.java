package com.sist.ex0905_dept.mapper;

import com.sist.ex0905_dept.vo.DeptVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    // dept.xml과 연동되는 빈객체다.
    // 연동되는 방법은 dept.xml의 namespace로 연동됨
    // 추상메서드들은 dept.xml에 있는 mapper들의 id와
    // 동일한 이름으로 메서드를 정의해야 함!
    List<DeptVO> total();
}
