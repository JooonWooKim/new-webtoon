package com.naver.webtoon.domain.webtoon.controller;

import com.naver.webtoon.domain.webtoon.dto.request.WebtoonCreateRequest;
import com.naver.webtoon.domain.webtoon.dto.request.WebtoonUpdateRequest;
import com.naver.webtoon.domain.webtoon.service.WebtoonService;
import com.naver.webtoon.global.common.response.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/webtoon")
public class WebtoonController {
    private final WebtoonService webtoonService;

    //웹툰등록
    @PostMapping("")
    public ResponseEntity<SuccessMessage<Void>> registerWebtoon(@RequestBody WebtoonCreateRequest request){
        webtoonService.registerWebtoon(request);
        return new ResponseEntity<>(new SuccessMessage<>("웹툰등록성공",null), HttpStatus.OK);
    }
    //웹툰수정
    @PutMapping("/{webtoonId}")
    public ResponseEntity<SuccessMessage<Void>> updateWebtoon(@PathVariable("webtoonId") Long webtoonId, @RequestBody WebtoonUpdateRequest request){
        webtoonService.updateWebtoon(webtoonId, request);
        return new ResponseEntity<>(new SuccessMessage<>("웹툰수정성공", null), HttpStatus.OK);
    }
    //웹툰삭제
    @DeleteMapping("/{webtoonId}")
    public ResponseEntity<SuccessMessage<Void>> deleteWebtoon(@PathVariable("webtoonId") Long webtoonId){
        webtoonService.deleteWebtoon(webtoonId);
        return new ResponseEntity<>(new SuccessMessage<>("웹툰삭제성공", null), HttpStatus.OK);
    }
}
