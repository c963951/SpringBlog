package com.raysmond.blog.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seo_robots_agents")
@Getter
@Setter
public class SeoRobotAgent extends BaseModel {

    @Column(nullable = false)
    private String userAgent;

    public SeoRobotAgent() {

    }

    public SeoRobotAgent(String userAgent) {
        this.userAgent = userAgent;
    }

}
