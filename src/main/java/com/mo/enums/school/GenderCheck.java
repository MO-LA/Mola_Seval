package com.mo.enums.school;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GenderCheck {
    M("남"),
    W("녀"),
    MW("남녀공학");

    private final String value;
}
