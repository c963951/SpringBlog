package com.raysmond.blog.models;

import com.raysmond.blog.models.support.PostFormat;
import com.raysmond.blog.models.support.PostStatus;
import com.raysmond.blog.models.support.PostType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Raysmond<i@raysmond.com>
 */
@Entity
@Table(name = "posts")
@Getter @Setter
public class Post extends BaseModel {
    private static final SimpleDateFormat SLUG_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String title;

    @Type(type="text")
    private String content;

    @Type(type = "text")
    private String renderedContent;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus = PostStatus.PUBLISHED;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostFormat postFormat = PostFormat.MARKDOWN;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType = PostType.POST;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "posts_tags",
            joinColumns = {@JoinColumn(name = "post_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false, updatable = false)}
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(nullable = false)
    private String seoKeywords = "";

    @Column(nullable = false)
    private String seoDescription = "";

    @OneToOne
    private SeoPostData seoData;

    private String permalink;

    public String getRenderedContent() {
        //if (this.postFormat == PostFormat.MARKDOWN)
            return renderedContent;

        //return getContent();
    }

    public void setPermalink(String permalink){
        String token = permalink.toLowerCase().replace("\n", " ").replaceAll("[^a-z\\d\\s]", " ");
        this.permalink = StringUtils.arrayToDelimitedString(StringUtils.tokenizeToStringArray(token, " "), "-");
    }

    private Long visitsCount = 0L;
    public Long getVisitsCount() {
        if (this.visitsCount == null) return 0L;
        else return this.visitsCount;
    }

    private Integer sympathyCount = 0;
    public Integer getSympathyCount() {
        if (this.sympathyCount == null) return 0;
        else return this.sympathyCount;
    }
}
