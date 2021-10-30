package com.mo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
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
