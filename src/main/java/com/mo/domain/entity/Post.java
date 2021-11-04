package com.mo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    private User author;

    @CreatedDate
    @Column
    private LocalDateTime date;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
