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
public class Estimate {
    @Id @GeneratedValue
    private Long idx;

    @Column
    private int estimateScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_idx")
    private School school;

    public Estimate(User user, School school) {
        this.user = user;
        this.school = school;
    }

    public void setEstimateScore(int estimateScore) {
        this.estimateScore = estimateScore;
    }
}
