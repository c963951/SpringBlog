package com.raysmond.blog.repositories;

import com.raysmond.blog.models.Post;
import com.raysmond.blog.models.SeoKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeoKeywordRepository extends JpaRepository<SeoKeyword, Long> {
    SeoKeyword findByPostAndKeyword(Post post, String keyword);
}
