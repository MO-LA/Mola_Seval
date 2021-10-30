package com.mo.domain.entity;

import javax.persistence.*;

@Entity
public class Comment {

    @Id @GeneratedValue
    private Long idx;

    @Column
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;
}
