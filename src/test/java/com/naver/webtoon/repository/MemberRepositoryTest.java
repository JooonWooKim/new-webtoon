package com.naver.webtoon.repository;

import com.naver.webtoon.domain.member.entity.Member;
import com.naver.webtoon.domain.member.fixture.MemberFixture;
import com.naver.webtoon.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.naver.webtoon.domain.member.fixture.MemberFixture.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원저장 성공 테스트")
    public void 회원저장_성공_테스트() {
        //given
        Member member = MemberFixture.buildMember(USERNAME, PASSWORD, COOKIE_COUNT);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        Assertions.assertThat(saveMember.getId()).isNotNull();
        Assertions.assertThat(saveMember.getUsername()).isEqualTo(USERNAME);
        Assertions.assertThat(saveMember.getPassword()).isEqualTo(PASSWORD);
        Assertions.assertThat(saveMember.getCookieCount()).isEqualTo(COOKIE_COUNT);
    }

    @Test
    @DisplayName("회원저장시 아이디가 없음")
    public void 회원저장시_아이디가_없음() {
        //given
        Member member = MemberFixture.buildMember(null, PASSWORD, COOKIE_COUNT);

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    @DisplayName("회원저장시 비밀번호가 없음")
    public void 회원저장시_비밀번호가_없음(){
        //given
        Member member = MemberFixture.buildMember(USERNAME, null, COOKIE_COUNT);

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    @DisplayName("회원저장시 쿠키가 없음")
    public void 회원저장시_쿠키가_없음(){
        //given
        Member member = MemberFixture.buildMember(USERNAME, PASSWORD, null);

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    @DisplayName("회원저장시_중복된_아이디_존재")
    public void 회원저장시_중복된_아이디_존재(){
        //given
        Member member1 = MemberFixture.buildMember(USERNAME, PASSWORD, COOKIE_COUNT);
        Member member2 = MemberFixture.buildMember(USERNAME, "test1", 0);
        //when
        memberRepository.save(member1);
        //then
        assertThrows(Exception.class, () -> memberRepository.save(member2));
    }
}
