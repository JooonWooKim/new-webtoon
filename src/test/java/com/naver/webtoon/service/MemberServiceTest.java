package com.naver.webtoon.service;

import com.naver.webtoon.domain.member.dto.request.MemberLoginRequest;
import com.naver.webtoon.domain.member.dto.request.MemberSignUpRequest;
import com.naver.webtoon.domain.member.entity.Member;
import com.naver.webtoon.domain.member.fixture.MemberFixture;
import com.naver.webtoon.domain.member.repository.MemberRepository;
import com.naver.webtoon.domain.member.service.MemberService;
import com.naver.webtoon.global.common.exception.WebtoonException;
import com.naver.webtoon.global.common.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.naver.webtoon.domain.member.fixture.MemberFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @InjectMocks
    MemberService memberService;
    @Mock
    MemberRepository memberRepository;
    @Mock
    PasswordEncoder encoder;
    @Mock
    JwtUtil jwtUtil;
    final String ENCRYPTED_PASSWORD = "encryptedPassword";

    @Test
    @DisplayName("성공_회원가입")
    public void 성공_회원가입(){
        //given
        MemberSignUpRequest request = new MemberSignUpRequest(USERNAME, PASSWORD);

        when(memberRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(encoder.encode(request.getPassword())).thenReturn(ENCRYPTED_PASSWORD);

        //when
        memberService.signUp(request);

        //then
        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        verify(memberRepository, times(1)).save(memberArgumentCaptor.capture());

        Member saveMember = memberArgumentCaptor.getValue();
        assertThat(saveMember.getUsername()).isEqualTo(request.getUsername());
        assertThat(saveMember.getPassword()).isEqualTo(ENCRYPTED_PASSWORD);
    }
    
    @Test
    @DisplayName("성공_로그인")
    public void 성공_로그인(){
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);
        Member member = buildMember(USERNAME, PASSWORD, COOKIE_COUNT);

        when(memberRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(member));
        when(encoder.matches(request.getPassword(), member.getPassword())).thenReturn(true);
        when(jwtUtil.createAccessToken(request.getUsername())).thenReturn("token");

        //when
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        memberService.login(request, response);

        //then
        verify(response).addHeader(Mockito.eq("AccessToken"), Mockito.anyString());
    }

    @Test
    @DisplayName("에러_로그인시_아이디를_찾지못함")
    public void 에러_로그인시_아이디를_찾지못함(){
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);

        Mockito.when(memberRepository.findByUsername(request.getUsername())).thenReturn(Optional.empty());

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        //when,then
        assertThatThrownBy(() -> memberService.login(request, response))
                .isInstanceOf(WebtoonException.class);
    }
    @Test
    @DisplayName("에러_로그인_비밀번호가_불일치")
    public void 에러_로그인_비밀번호가_불일치(){
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);
        Member member = MemberFixture.buildMember(USERNAME, PASSWORD, COOKIE_COUNT);

        when(memberRepository.findByUsername(request.getUsername())).thenReturn(Optional.of(member));
        when(encoder.matches(request.getPassword(), member.getPassword())).thenReturn(false);

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        //when,then
        assertThatThrownBy(()-> memberService.login(request, response))
                .isInstanceOf(WebtoonException.class);
    }

    @Test
    @DisplayName("에러_회원가입시_중복된_아이디_존재")
    public void 에러_회원가입시_중복된_아이디_존재(){
        //given
        MemberSignUpRequest request = new MemberSignUpRequest(USERNAME, PASSWORD);

        when(memberRepository.existsByUsername(request.getUsername())).thenReturn(true);

        //when,then
        assertThatThrownBy(()->memberService.signUp(request))
                .isInstanceOf(WebtoonException.class);
    }
}
