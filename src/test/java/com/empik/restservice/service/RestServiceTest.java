package com.empik.restservice.service;

import com.empik.restservice.config.ConnectionConfig;
import com.empik.restservice.model.GithubUser;
import com.empik.restservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RestServiceTest {

    @MockBean
    ConnectionConfig connectionConfig;

    @MockBean
    SQLExecutionService sqlService;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    RestService restService;

    ResponseEntity<GithubUser> entity;
    Integer id;
    String login;
    String url;
    String type;
    String avatarUrl;
    Timestamp createdAt;
    Integer followers;
    Integer repos;
    GithubUser githubUser;

    @BeforeEach
    public void before() {
        id = 1;
        login = "test";
        url = "https://api.github.com/users/" + login;
        type = "User";
        avatarUrl = "url";
        createdAt = new Timestamp(System.currentTimeMillis());
        followers = 2;
        repos = 2;

        githubUser = new GithubUser(1, login, login, type, avatarUrl, createdAt, followers, repos);
        entity = ResponseEntity.status(HttpStatus.OK).body(githubUser);
    }

    @Test
    void getUserFromApi() {
        when(connectionConfig.getApi()).thenReturn("https://api.github.com/users");
        when(sqlService.updateRequestCount(login)).thenReturn(1);
        when(restTemplate.getForEntity(url, GithubUser.class)).thenReturn(entity);

        User user = User.builder()
                .id(id)
                .login(login)
                .name(login)
                .type(type)
                .avatarUrl(avatarUrl)
                .createdAt(createdAt)
                .calculations(12.0)
                .build();

        User user2 = restService.getUserFromApi(login);

        assertEquals(user.getId(), user2.getId());
        assertEquals(user.getLogin(), user2.getLogin());
        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getType(), user2.getType());
        assertEquals(user.getAvatarUrl(), user2.getAvatarUrl());
        assertEquals(user.getCreatedAt(), user2.getCreatedAt());
        assertEquals(user.getCalculations(), user2.getCalculations());
    }

    @Test
    void calculate() {
        assertEquals(3, restService.calculate(6, 1));
        assertNull(restService.calculate(null, null));
        assertNull(restService.calculate(1, null));
        assertNull(restService.calculate(0, 1));
        assertEquals(4.5, restService.calculate(4, 1));
    }
}