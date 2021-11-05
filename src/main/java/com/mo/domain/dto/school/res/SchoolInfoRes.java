package com.mo.domain.dto.school.res;

import com.mo.domain.entity.School;
import com.mo.enums.school.Fond;
import com.mo.enums.school.FondType;
import com.mo.enums.school.GenderCheck;
import com.mo.enums.school.SchoolKind;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SchoolInfoRes {
    private Long idx;
    private String schoolName;
    // 행정실 전화번호
    private String administrativeOfficeTel;
    // 교무실 전화번호
    private String staffroomTel;
    private String homePage;
    private GenderCheck genderCheck;
    private String roadNameAddress;
    private SchoolKind schoolKind;
    private FondType fondType;
    private Fond fond;
    private int maleSum;
    private int femaleSum;

    public SchoolInfoRes(School school) {
        this.idx = school.getIdx();
        this.schoolName = school.getName();
        this.administrativeOfficeTel = school.getAOfficePhoneNumber();
        this.staffroomTel = school.getTOfficePhoneNumber();
        this.homePage = school.getHomePage();
        this.genderCheck = school.getGenderCheck();
        this.roadNameAddress = school.getRoadNameAddress();
        this.schoolKind = school.getSchoolKind();
        this.fondType = school.getFondType();
        this.fond = school.getFond();
        this.maleSum = school.getMaleSum();
        this.femaleSum = school.getFemaleSum();
    }

}
