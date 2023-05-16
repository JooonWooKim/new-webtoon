package com.naver.webtoon.domain.webtoon.repository;

import com.naver.webtoon.domain.webtoon.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
    boolean existsByTitle(String title);
}
