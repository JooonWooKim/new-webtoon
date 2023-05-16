package com.naver.webtoon.domain.webtoon.repository;

import com.naver.webtoon.domain.webtoon.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String authorName);
}
