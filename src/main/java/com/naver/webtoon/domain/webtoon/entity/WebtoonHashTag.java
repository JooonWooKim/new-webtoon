package com.naver.webtoon.domain.webtoon.entity;

import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonHashTag extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webtoon_hash_tag_id")
    private Long webtoonHashTagId;

    @ManyToOne
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;
}
