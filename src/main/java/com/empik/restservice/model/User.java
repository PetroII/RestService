package com.empik.restservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private Timestamp createdAt;
    private Double calculations;
}
