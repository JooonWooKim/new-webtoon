package com.naver.webtoon.domain.webtoon.controller;

import com.naver.webtoon.domain.webtoon.dto.request.WebtoonCreateRequest;
import com.naver.webtoon.domain.webtoon.service.WebtoonService;
import com.naver.webtoon.global.common.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/webtoon")
public class WebtoonController {
    private final WebtoonService webtoonService;

    //웹툰등록
    @PostMapping("")
    public ResponseEntity<SuccessMessage<Void>> 웹툰등록(@RequestBody WebtoonCreateRequest request){
        webtoonService.registerWebtoon(request);
        return new ResponseEntity<>(new SuccessMessage<>("웹툰등록성공",null), HttpStatus.OK);
    }
}
