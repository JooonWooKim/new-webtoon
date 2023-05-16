package com.naver.webtoon.domain.webtoon.dto.request;

import com.naver.webtoon.domain.webtoon.entity.*;
import com.naver.webtoon.domain.webtoon.enums.SerializedStatus;
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
    private SerializedStatus serializedStatus;
    private List<HashTag> hashTagList = new ArrayList<>();
    private List<String> publishingDay;

    public Webtoon toWebtoon(Author author){
        return Webtoon.builder()
                .title(title)
                .author(author)
                .thumbnail(thumbnail)
                .description(description)
                .serializedStatus(serializedStatus).build();
    }

    public WebtoonPublishingDay toWebtoonPublishingDay(Webtoon webtoon, PublishingDay publishingDay){
        return WebtoonPublishingDay.builder()
                .webtoon(webtoon)
                .publishingDay(publishingDay)
                .build();
    }
}
