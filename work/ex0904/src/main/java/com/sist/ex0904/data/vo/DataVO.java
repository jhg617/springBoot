package com.sist.ex0904.data.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok 지정하면 Getter, Setter 생성해줌
@NoArgsConstructor // 기본생성자 생성해줌
public class DataVO {
    private String str;
}
