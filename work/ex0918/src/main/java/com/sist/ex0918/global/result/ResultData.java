package com.sist.ex0918.global.result;

import com.sist.ex0918.domain.bbs.entity.Bbs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultData<T> {
    private int totalCount; //length
    private String msg; //메세지
    private T data; //제네릭타입을 T로 줌으로써 아무객체가 와도 그 객체로 변환되서 반환된다.

    public static <T> ResultData<T> of(int totalCount, String msg, T data){
        return new ResultData<>(totalCount, msg, data);
    }

    public static <T> ResultData<T> of(int totalCount, String msg){
        return of(totalCount, msg, null);
    }
}
