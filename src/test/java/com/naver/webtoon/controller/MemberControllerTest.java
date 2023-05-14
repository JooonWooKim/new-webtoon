package com.naver.webtoon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.webtoon.domain.member.contorller.MemberController;
import com.naver.webtoon.domain.member.dto.request.MemberLoginRequest;
import com.naver.webtoon.domain.member.dto.request.MemberSignUpRequest;
import com.naver.webtoon.domain.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.naver.webtoon.domain.member.fixture.MemberFixture.PASSWORD;
import static com.naver.webtoon.domain.member.fixture.MemberFixture.USERNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
    @Mock
    MemberService memberService;
    @InjectMocks
    MemberController memberController;
    MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }
    
    @Test
    @DisplayName("성공_회원가입")
    public void 성공_회원가입() throws Exception {
        //given
        MemberSignUpRequest request = new MemberSignUpRequest(USERNAME, PASSWORD);

        //when, then
        mockMvc.perform(post("/api/auth/sign-up")
                .contentType("application/JSON")
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("회원가입성공"));
    }

    @Test
    @DisplayName("성공_로그인")
    public void 성공_로그인() throws Exception {
        //given
        MemberLoginRequest request = new MemberLoginRequest(USERNAME, PASSWORD);

        //when, then
        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/JSON")
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value("로그인성공"));
    }
}
