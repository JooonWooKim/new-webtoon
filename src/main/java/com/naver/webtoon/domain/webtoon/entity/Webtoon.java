package com.naver.webtoon.domain.webtoon.entity;

import com.naver.webtoon.domain.webtoon.enums.SerializedStatus;
import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private SerializedStatus serializedStatus;

    //작가(Author)는 다수의 웹툰을 보유가능(oneToMany)
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

//    @OneToMany(mappedBy = "webtoon")
//    private List<WebtoonHashTag> webtoonHashTagList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "webtoon")
//    private List<WebtoonPublishingDay> webtoonPublishingDayList = new ArrayList<>();

    @Builder
    public Webtoon(String title,
                   Author author,
                   String thumbnail,
                   String description,
                   SerializedStatus serializedStatus){
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
        this.description = description;
        this.serializedStatus = serializedStatus;
    }

    public void update(String title,
                              Author author,
                              String thumbnail,
                              String description,
                              SerializedStatus serializedStatus){
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
        this.description = description;
        this.serializedStatus = serializedStatus;
    }
}
