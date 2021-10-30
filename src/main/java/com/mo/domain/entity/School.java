package com.mo.domain.entity;

import com.mo.enums.school.Fond;
import com.mo.enums.school.FondType;
import com.mo.enums.school.GenderCheck;
import com.mo.enums.school.SchoolKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
public class School {
    @Id @GeneratedValue
    private Long idx;

    // 학교 이름
    @Column
    private String name;

    // 행정실 전화번호
    @Column
    private String aOfficePhoneNumber;

    // 교무실 전화번호
    @Column
    private String tOfficePhoneNumber;

    // 홈페이지 주소
    @Column
    private String homePage;

    // 남여공학 구분
    @Enumerated(EnumType.STRING)
    @Column
    private GenderCheck genderCheck;

    // 도로명주소
    @Column
    private String roadNameAddress;

    // 학교 특성
    @Column
    @Enumerated(EnumType.STRING)
    private SchoolKind schoolKind;

    // 설립유형
    @Column
    @Enumerated(EnumType.STRING)
    private FondType fondType;

    // 설립 구분
    @Column
    @Enumerated(EnumType.STRING)
    private Fond fond;

    // 남학생 수
    @Column
    private int maleSum;

    // 여학생 수
    @Column
    private int femaleSum;

    @Column(unique = true)
    private String schoolCode;

    public void setMaleSum(int maleSum) {
        this.maleSum = maleSum;
    }

    public void setFemaleSum(int femaleSum) {
        this.femaleSum = femaleSum;
    }
}
