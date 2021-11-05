package com.mo.domain.entity;

import com.mo.enums.user.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column
    private Sex sex;

    @Column
    private Integer age;

    // 거주지역
    @Column
    private String residentialArea;

    @Column
    private String school;

    @OneToMany(mappedBy = "user")
    private List<Pick> picks;
}
