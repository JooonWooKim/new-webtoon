package com.naver.webtoon.domain.webtoon.entity;

import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonPublishingDay extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webtoon_publishing_day_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @ManyToOne
    @JoinColumn(name = "publishing_day_id")
    private PublishingDay publishingDay;

    @Builder
    public WebtoonPublishingDay(Long id, Webtoon webtoon, PublishingDay publishingDay){
        this.id = id;
        this.webtoon = webtoon;
        this.publishingDay = publishingDay;
    }
}
