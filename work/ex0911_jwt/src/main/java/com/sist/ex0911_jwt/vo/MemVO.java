package com.sist.ex0911_jwt.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "member_t")
@Getter
@Setter
@AllArgsConstructor //인자를 모두 받는 생성자 자동생성
@NoArgsConstructor //인자를 받지 않는 생성자 자동생성
public class MemVO {
    @Id
    private Long mIdx;
    private String mName, mId, mPw;
}
