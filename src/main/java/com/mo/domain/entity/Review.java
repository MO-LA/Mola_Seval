package com.mo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Review {

    @Id @GeneratedValue
    private Long idx;

    @Column
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private School school;
}
