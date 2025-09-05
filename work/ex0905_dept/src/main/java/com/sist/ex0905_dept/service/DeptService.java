package com.sist.ex0905_dept.service;

import com.sist.ex0905_dept.mapper.DeptMapper;
import com.sist.ex0905_dept.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    // 필요한 매퍼들 지정
    @Autowired
    private DeptMapper deptMapper;

    //컨트롤러에서 호출하는 비지니스 메서드를 정의
    public DeptVO[] getTotal(){
        DeptVO[] ar = null;
        List<DeptVO> list = deptMapper.total();
        if(list!=null && list.size()>0){
            ar = new DeptVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
}
