package com.mo.domain.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id @GeneratedValue
    private Long idx;

    @Column
    private String content;

    @ManyToOne
    private User author;

    @CreatedDate
    @Column
    private LocalDateTime date;
}
