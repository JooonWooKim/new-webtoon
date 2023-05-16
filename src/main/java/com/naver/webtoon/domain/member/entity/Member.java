package com.naver.webtoon.domain.member.entity;

import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "cookie_count", nullable = false)
    private Integer cookieCount;

    @Builder
    public Member(Long id, String username, String password, Integer cookieCount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cookieCount = cookieCount;
    }

}
