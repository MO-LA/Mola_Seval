package com.mo.enums.school;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 설립 유형
@RequiredArgsConstructor
@Getter
public enum FondType {
    INDEPENDENCE("단설"),
    ACCESSORIES("부속"),
    ESTABLISH("병설");

    private final String value;
}
