package com.naver.webtoon.domain.member.dto.request;

import com.naver.webtoon.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSignUpRequest {

    private String username;
    private String password;

    /* password 변수는 MemberService에서 암호화 후 매개변수를 통해 DB에 저장한다. */
    public Member toMember(String encryptedPassword){
        return Member.builder()
                .username(username)
                .password(encryptedPassword)
                .cookieCount(0)
                .build();
    }

    public MemberSignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
