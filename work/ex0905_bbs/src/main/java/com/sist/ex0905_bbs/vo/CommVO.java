package com.sist.ex0905_bbs.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommVO {
    private String c_idx, writer,
            content, pwd, write_date, ip, b_idx;
}
