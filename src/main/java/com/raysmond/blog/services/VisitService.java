package com.raysmond.blog.services;

import com.raysmond.blog.models.Post;
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

    public void saveVisit(String clientIp, Post post) {
//        if (this.userService.currentUser().isAdmin())
//            return;

        Visit visit = new Visit();
        visit.setClientIp(clientIp);
        visit.setPost(post);
        visit.setUser(this.userService.currentUser());
        visit.setIsAdmin(this.userService.currentUser().isAdmin());
        this.visitRepository.save(visit);
    }

    public Long getUniqueVisitsCount(Post post) {
        return this.visitRepository.getUniquePostVisitsCount(post);
    }

}
