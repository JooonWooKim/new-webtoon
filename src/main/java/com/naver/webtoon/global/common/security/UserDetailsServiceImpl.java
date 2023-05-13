package com.naver.webtoon.global.common.security;

import com.naver.webtoon.global.common.exception.WebtoonException;
import com.naver.webtoon.domain.member.entity.Member;
import com.naver.webtoon.domain.member.repository.MemberRepository;
import com.naver.webtoon.global.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new WebtoonException(ErrorCode.NOT_FOUND_MEMBER)
        );
        return new UserDetailsImpl(member, member.getUsername());
    }
}
