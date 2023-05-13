package com.naver.webtoon.domain.member.fixture;

import com.naver.webtoon.domain.member.entity.Member;

public class MemberFixture {
    public static final String USERNAME = "test";
    public static final String PASSWORD = "test";
    public static final Integer COOKIE_COUNT = 0;

    public static Member buildMember(String username, String password, Integer cookieCount){
        return Member.builder()
                .username(username)
                .password(password)
                .cookieCount(cookieCount)
                .build();
    }
}
