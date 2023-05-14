package com.naver.webtoon.domain.webtoon.entity;

import com.naver.webtoon.global.common.time.Timestamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hash_tag_id")
    private Long hashTagId;

    private String name;

    @OneToMany(mappedBy = "hashTag")
    private List<WebtoonHashTag> webtoonHashTagList = new ArrayList<>();
}
