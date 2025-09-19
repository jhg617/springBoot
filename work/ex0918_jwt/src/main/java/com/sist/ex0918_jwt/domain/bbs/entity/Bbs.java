package com.sist.ex0918_jwt.domain.bbs.entity;

import com.sist.ex0918_jwt.global.jpa.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder // 현재클래스와 부모클래스의 필드값을 저장하기 위한 메스드들을 포함시켜주는 어노테이션
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) // 부모가 가지는 함수를 사용가능하게 해줌(필드도 포함)
public class Bbs extends BaseEntity{
    @Column(columnDefinition = "bigint default 0")
    private Long hit;

    @Column
    private Long state = 0L;

    @NonNull
    private String title, content, writer;

    @PrePersist
    public void initStatus(){ // save함수로 데이터가 저장되기 전에 수행함
        if (state == null) {
            state = 0L;
            hit = 0L;
        }
    }
}
