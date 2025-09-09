package com.sist.ex0909_jpa1.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity(name = "product_t")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductJPO {
    @Id
    @GeneratedValue
    private long pNum; //기본키
    private String pName;
    private String pCompany;
    private LocalDate regDate;
    private int category1;
    private int category2;
    private int category3;

}
