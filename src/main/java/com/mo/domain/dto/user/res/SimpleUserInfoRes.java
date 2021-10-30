package com.mo.domain.dto.user.res;

import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SimpleUserInfoRes {

    private String id;

    private String school;

    public SimpleUserInfoRes() {}

    public SimpleUserInfoRes(User user) {
        this.id = user.getId();
        this.school = user.getSchool();
    }
}
