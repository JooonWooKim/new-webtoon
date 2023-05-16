package com.naver.webtoon.domain.webtoon.service;

import com.naver.webtoon.domain.webtoon.dto.request.WebtoonCreateRequest;
import com.naver.webtoon.domain.webtoon.entity.*;
import com.naver.webtoon.domain.webtoon.enums.DayOfTheWeek;
import com.naver.webtoon.domain.webtoon.repository.*;
import com.naver.webtoon.global.common.exception.WebtoonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.naver.webtoon.global.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;
    private final AuthorRepository authorRepository;
    private final PublishingDayRepository publishingDayRepository;
    private final WebtoonPublishingDayRepository webtoonPublishingDayRepository;
    private final HashTagRepository hashTagRepository;
    private final WebtoonHashTagRepository webtoonHashTagRepository;

    @Transactional
    public void registerWebtoon(WebtoonCreateRequest request){
        Author author = authorRepository.findByName(request.getAuthor()).orElseThrow(
                () -> new WebtoonException(NOT_FOUND_AUTHOR));
        Webtoon webtoon = request.toWebtoon(author);

        webtoonRepository.save(webtoon);

        for(String dayOfTheWeek : request.getPublishingDay()){
            DayOfTheWeek publishingDayEnum = DayOfTheWeek.toEnum(dayOfTheWeek);
            PublishingDay publishingDay = publishingDayRepository.findByDayOfTheWeek(publishingDayEnum).orElseThrow(
                    () -> new WebtoonException(NOT_FOUND_PUBLISHING_DAY));
            WebtoonPublishingDay webtoonPublishingDay = request.toWebtoonPublishingDay(webtoon, publishingDay);

            webtoonPublishingDayRepository.save(webtoonPublishingDay);
        }

        for(String hashTagName : request.getHashTag()){
            HashTag hashTag = hashTagRepository.findByName(hashTagName).orElseThrow(
                    () -> new WebtoonException(NOT_FOUND_HASH_TAG));
            WebtoonHashTag webtoonHashTag = request.toWebtoonHashTag(webtoon, hashTag);

            webtoonHashTagRepository.save(webtoonHashTag);
        }
    }
}
