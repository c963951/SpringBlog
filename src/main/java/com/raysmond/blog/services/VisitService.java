package com.raysmond.blog.services;

import com.raysmond.blog.models.Post;
import com.raysmond.blog.models.User;
import com.raysmond.blog.models.Visit;
import com.raysmond.blog.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private UserService userService;

    public void saveVisit(Post post, String clientIp, String userAgent) {
//        if (this.userService.currentUser().isAdmin())
//            return;

        User user = this.userService.currentUser();

        Visit visit = new Visit();
        visit.setClientIp(clientIp);
        visit.setPost(post);
        visit.setUser(user);
        visit.setIsAdmin(user != null ? user.isAdmin() : false);
        visit.setUserAgent(userAgent);
        this.visitRepository.save(visit);
    }

    public Long getUniqueVisitsCount(Post post) {
        return this.visitRepository.getUniquePostVisitsCount(post);
    }

}
