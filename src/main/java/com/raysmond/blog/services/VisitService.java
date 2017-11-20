package com.raysmond.blog.services;

import com.raysmond.blog.models.Post;
import com.raysmond.blog.models.SeoRobotAgent;
import com.raysmond.blog.models.User;
import com.raysmond.blog.models.Visit;
import com.raysmond.blog.repositories.SeoRobotAgentRepository;
import com.raysmond.blog.repositories.VisitRepository;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private SeoRobotAgentRepository seoRobotAgentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

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

        Session session = (Session) this.entityManager.getDelegate();
        SQLQuery query = session.createSQLQuery(
                "SELECT COUNT(DISTINCT v.clientIp) " +
                        "FROM visits AS v " +
                        "LEFT JOIN seo_robots_agents AS ra " +
                        "ON LOWER(v.userAgent) LIKE concat('%', LOWER(ra.userAgent), '%') " +
                        "WHERE v.post_id = :post_id AND v.isAdmin = FALSE " +
                        "AND ra.id IS NULL ");
        query.setLong("post_id", post.getId());
        List<Object> result = query.list();
        if (result.size() > 0L) {
            return ((BigInteger)result.get(0)).longValue();
        }

        return 0L;

    }

    public Long getUniqueVisitsCount_old(Post post) {
        //return this.visitRepository.getUniquePostVisitsCount(post);

        // exclude queries from robots if matches by UserAgent
        List<SeoRobotAgent> robotsAgents = this.seoRobotAgentRepository.findAll();

        final Long[] count = {0L};

        this.visitRepository.getVisitsByPostAndIsAdminIsFalse(post).forEach(vr -> {

            Object[] v = (Object[]) vr;

            if (robotsAgents.size() == 0 || v[1] == null) {
                count[0]++;
            } else {
                robotsAgents.forEach(ra -> {
                    Pattern p = Pattern.compile(".*("+ra.getUserAgent()+").*", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher((String) v[1]);
                    if (!m.matches()) {
                        count[0]++;
                    }
                });
            }
        });

        return count[0];
    }

}
