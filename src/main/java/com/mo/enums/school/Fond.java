package com.mo.enums.school;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 설립 구분
@RequiredArgsConstructor
@Getter
public enum Fond {
    PRIVATE("사립"),
    NATIONAL("국립"),
    PUBLIC("공립");

    private final String value;

}
