package com.naver.webtoon.domain.webtoon.repository;

import com.naver.webtoon.domain.webtoon.entity.WebtoonPublishingDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonPublishingDayRepository extends JpaRepository<WebtoonPublishingDay, Long> {
}
