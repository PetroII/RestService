package com.empik.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class GithubUser {
    private Integer id;
    private String login;
    private String name;
    private String type;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    private Integer followers;

    @JsonProperty("public_repos")
    private Integer publicRepos;
}
