package com.mo.domain.dto.school.res;

import com.mo.domain.entity.School;
import com.mo.enums.school.FondType;
import com.mo.enums.school.GenderCheck;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SchoolListRes {
    private Long idx;
    private String schoolName;
    private Double estimate;
    private int estimatedPeople;
    private GenderCheck genderCheck;
    private FondType fondType;
    private String roadNameAddress;

    public SchoolListRes(School school) {
        this.idx = school.getIdx();
        this.schoolName = school.getName();
        this.genderCheck = school.getGenderCheck();
        this.fondType = school.getFondType();
        this.roadNameAddress = school.getRoadNameAddress();
    }
}
