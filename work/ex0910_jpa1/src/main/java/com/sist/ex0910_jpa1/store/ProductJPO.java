package com.sist.ex0910_jpa1.store;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product_t") //테이블 이름 지정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductJPO { //ProductJPO : 테이블 객체
    @Id //기본키(PK) 지정
    @GeneratedValue //기본키 자동생성 (=AUTO_INCREMENT)
    private long pNum;
    private String pName;
    private String pCompany;
    private LocalDate regDate; //실제 DB 컬럼은 reg_date로 저장됨(대문자가 언더바로 인식됨)
    
    @Column(name = "category1")
    private int category1;
    
    private int category2;
    private int category3;

    @ManyToOne //다 대 1 관계 설정
    @JoinColumn(name = "category1", insertable = false, updatable = false)
    private Category1JPO cvo1;

}
