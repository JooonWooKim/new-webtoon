package com.naver.webtoon.domain.webtoon.entity;

import com.naver.webtoon.domain.webtoon.enums.DayOfTheWeek;
import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublishingDay extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publishing_day_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;

    @OneToMany(mappedBy = "publishingDay")
    private List<WebtoonPublishingDay>webtoonPublishingDayList = new ArrayList<>();

    @Builder
    public PublishingDay(long id, DayOfTheWeek dayOfTheWeek){
        this.id = id;
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
