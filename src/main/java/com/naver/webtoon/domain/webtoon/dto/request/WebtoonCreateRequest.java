package com.naver.webtoon.domain.webtoon.dto.request;

import com.naver.webtoon.domain.webtoon.entity.Author;
import com.naver.webtoon.domain.webtoon.entity.HashTag;
import com.naver.webtoon.domain.webtoon.entity.Webtoon;
import com.naver.webtoon.domain.webtoon.enums.PublishingDays;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class WebtoonCreateRequest {
    private String title;
    private String author;
    private String thumbnail;
    private String description;
    private String serializedStatus;
    private List<HashTag> hashTagList = new ArrayList<>();
    private PublishingDays publishingDay;

    public Webtoon toWebtoon(Author author){
        return Webtoon.builder()
                .title(title)
                .author(author)
                .thumbnail(thumbnail)
                .description(description)
                .serializedStatus(serializedStatus).build();
    }
}
