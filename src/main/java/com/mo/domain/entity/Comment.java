package com.mo.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
