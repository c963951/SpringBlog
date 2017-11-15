package com.raysmond.blog.controllers;

import com.raysmond.blog.forms.LikeForm;
import com.raysmond.blog.models.Post;
import com.raysmond.blog.services.*;
import com.raysmond.blog.support.web.ViewHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value = "/sympathy")
public class SympathyController {

    @Autowired
    private AppSetting appSetting;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestProcessorService requestProcessorService;


    @Data
    public static class SympathyRequestData {
        @NotNull
        private String postId;
    }


    @PostMapping(value = "/like")
    public @ResponseBody LikeForm likeIt(@RequestBody SympathyRequestData data, HttpServletRequest request) {
        Post post = this.postService.findPostByPermalink(data.getPostId());
        this.likeService.likePost(post, this.requestProcessorService.getRealIp(request));
        ViewHelper viewHelper = new ViewHelper(this.appSetting);
        LikeForm result = new LikeForm();
        result.setSympathy(viewHelper.formatNumberByThousands(this.likeService.getTotalLikesByPost(post)));
        return result;
    }

    @PostMapping(value = "/dislike")
    public @ResponseBody LikeForm dislikeIt(@RequestBody SympathyRequestData data, HttpServletRequest request) {
        Post post = this.postService.findPostByPermalink(data.getPostId());
        this.likeService.dislikePost(post, this.requestProcessorService.getRealIp(request));
        ViewHelper viewHelper = new ViewHelper(this.appSetting);
        LikeForm result = new LikeForm();
        result.setSympathy(viewHelper.formatNumberByThousands(this.likeService.getTotalLikesByPost(post)));
        return result;
    }



}
