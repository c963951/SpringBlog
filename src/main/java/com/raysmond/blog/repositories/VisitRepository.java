package com.raysmond.blog.repositories;

import com.raysmond.blog.models.Post;
import com.raysmond.blog.models.User;
import com.raysmond.blog.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query( "SELECT COUNT(DISTINCT v.clientIp) " +
            "FROM Visit AS v " +
            "WHERE v.post = :post AND v.isAdmin = FALSE "
    )
    Long getUniquePostVisitsCount(@Param("post") Post post);
}
