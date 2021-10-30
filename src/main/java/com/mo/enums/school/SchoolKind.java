package com.mo.enums.school;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 학교 특성
@RequiredArgsConstructor
@Getter
public enum SchoolKind {
    GENERAL("일반고등학교"),
    SPECIAL_PURPOSE("특수목적고등학교"),
    AUTONOMOUS("자율고등학교"),
    SPECIALIZED("특성화고등학교");

    private final String value;
}
