package com.mo.domain.dto.school.res;

import com.mo.domain.entity.MiddleSchool;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchMiddleSchoolRes {
    private String name;
    private String roadAddressName;

    public SearchMiddleSchoolRes(MiddleSchool middleSchool) {
        this.name = middleSchool.getName();
        this.roadAddressName = middleSchool.getRoadAddressName();
    }
}
