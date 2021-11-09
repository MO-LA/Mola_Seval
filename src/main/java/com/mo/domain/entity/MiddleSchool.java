package com.mo.domain.entity;

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
public class MiddleSchool {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String roadAddressName;


}
