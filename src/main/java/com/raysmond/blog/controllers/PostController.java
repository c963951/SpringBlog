package com.raysmond.blog.controllers;

import com.raysmond.blog.Constants;
import com.raysmond.blog.error.NotFoundException;
import com.raysmond.blog.models.Post;
import com.raysmond.blog.services.PostService;
import com.raysmond.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * @author Raysmond<i@raysmond.com>
 */
@Controller
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "archive", method = GET)
    public String archive(Model model){
        model.addAttribute("posts", postService.getArchivePosts());

        return "posts/archive";
    }

    @RequestMapping(value = "{permalink}", method = GET)
    public String show(@PathVariable String permalink, Model model){
        Post post = null;

        try{
            post = postService.getPublishedPostByPermalink(permalink);
        } catch (NotFoundException ex){
            if (permalink.matches("\\d+")) {
                post = postService.getPost(Long.valueOf(permalink));
            } else if (permalink.toLowerCase().trim().equals(Constants.PROJECTS_PAGE_PERMALINK)) {
                post = postService.createProjectsPage();
            }
        }

        if (post == null) {
            throw new NotFoundException("Post with permalink " + permalink + " is not found");
        }

        model.addAttribute("post", post);
        model.addAttribute("tags", postService.getPostTags(post));
        model.addAttribute("seoKeywords", postService.getSeoKeywordsAsString(post));
        model.addAttribute("seoDescription", post.getSeoDescription());

        return "posts/show";
    }


}
