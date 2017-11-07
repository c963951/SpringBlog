package com.raysmond.blog.models;

import com.raysmond.blog.repositories.PostRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seo_keywords")
@Getter
@Setter
public class SeoKeyword extends BaseModel {

    @ManyToOne
    private Post post;

    @Column(nullable = false)
    private String keyword;

    public SeoKeyword() {}

    public SeoKeyword(Post post, String keyword) {
        this.setPost(post);
        this.setKeyword(keyword);
    }
}
