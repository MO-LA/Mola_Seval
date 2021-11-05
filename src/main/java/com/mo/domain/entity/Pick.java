package com.mo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Pick {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_idx")
    private School school;

    public Pick(Boolean state, School school, User user) {
        this.state = state;
        this.school = school;
        this.user = user;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
