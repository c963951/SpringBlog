package com.raysmond.blog.services;

import com.raysmond.blog.models.Post;
import com.raysmond.blog.models.SeoKeyword;
import com.raysmond.blog.repositories.SeoKeywordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeoKeywordService {

    private SeoKeywordRepository seoKeywordRepository;

    private static final Logger logger = LoggerFactory.getLogger(SeoKeywordService.class);

    @Autowired
    public SeoKeywordService(SeoKeywordRepository repository) {
        this.seoKeywordRepository = repository;
    }

    public SeoKeyword findOrCreateByNameForPost(Post post, String name) {
        SeoKeyword seoKeyword = seoKeywordRepository.findByPostAndKeyword(post, name);
        if (seoKeyword == null) {
            seoKeywordRepository.save(new SeoKeyword(post, name));
        }
        return seoKeyword;
    }
}
