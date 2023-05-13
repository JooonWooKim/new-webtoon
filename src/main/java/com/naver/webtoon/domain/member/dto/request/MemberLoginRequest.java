package com.naver.webtoon.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLoginRequest {

    private String username;
    private String password;

    public MemberLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
