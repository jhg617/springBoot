package com.sist.ex0905_bbs.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BbsVO {
    private String b_idx, subject, writer, content, file_name,
            ori_name, pwd, write_date, ip, hit, bname, status;

    private List<CommVO> c_list;
}
