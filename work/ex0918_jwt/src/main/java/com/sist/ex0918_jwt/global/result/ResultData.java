package com.sist.ex0918_jwt.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultData<T> {
    
    private int totalCount;// length
    private String msg; // 수행결과
    private T data; // 임의의 제네릭타입 변수

    public static <T> ResultData<T> of(int totalCount, String msg, T data){
        return new ResultData<>(totalCount, msg, data);
    }

    public static <T> ResultData<T> of(int totalCount, String msg){
        return of(totalCount, msg, null);
    }
}
