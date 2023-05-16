package com.naver.webtoon.domain.webtoon.service;

import com.naver.webtoon.domain.webtoon.dto.request.WebtoonCreateRequest;
import com.naver.webtoon.domain.webtoon.entity.Author;
import com.naver.webtoon.domain.webtoon.entity.Webtoon;
import com.naver.webtoon.domain.webtoon.repository.AuthorRepository;
import com.naver.webtoon.domain.webtoon.repository.WebtoonRepository;
import com.naver.webtoon.global.common.exception.WebtoonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.naver.webtoon.global.common.exception.ErrorCode.NOT_FOUND_AUTHOR;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void registerWebtoon(WebtoonCreateRequest request){
        Author author = authorRepository.findByName(request.getAuthor()).orElseThrow(
                () -> new WebtoonException(NOT_FOUND_AUTHOR));
        Webtoon webtoon = request.toWebtoon(author);

        webtoonRepository.save(webtoon);
    }
}
