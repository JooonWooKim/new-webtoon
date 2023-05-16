package com.naver.webtoon.domain.webtoon.entity;

import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Webtoon extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webtoon_id")
    private Long id;

    private String title;
    private String thumbnail;
    private String description;
    private String serializedStatus;

    //작가(Author)는 다수의 웹툰을 보유가능(oneToMany)
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "webtoon")
    private List<WebtoonHashTag> webtoonHashTagList = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon")
    private List<WebtoonPublishingDay> webtoonPublishingDayList = new ArrayList<>();
}
