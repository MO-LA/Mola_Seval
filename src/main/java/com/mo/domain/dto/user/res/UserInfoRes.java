package com.mo.domain.dto.user.res;

import com.mo.domain.entity.User;
import com.mo.enums.user.Sex;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserInfoRes {
    private Long idx;
    private String id;
    private Sex sex;
    private Integer age;
    private String residentialArea;
    private String school;

    public UserInfoRes(User user) {
        this.idx = user.getIdx();
        this.id = user.getId();
        this.sex = user.getSex();
        this.age = user.getAge();
        this.residentialArea = user.getResidentialArea();
        this.school = user.getSchool();
    }
}
