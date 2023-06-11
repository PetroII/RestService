package com.empik.restservice.service;

import com.empik.restservice.config.ConnectionConfig;
import com.empik.restservice.model.GithubUser;
import com.empik.restservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestService {

    @Autowired
    private ConnectionConfig connectionConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SQLExecutionService sqlService;

    public User getUserFromApi(String login) {
        String url = connectionConfig.getApi() + "/" + login;

        if (sqlService.updateRequestCount(login) == 0)
            sqlService.insertRequestCount(login);

        try {
            log.debug("Getting info about user {} from the API", login);
            ResponseEntity<GithubUser> responseEntity = restTemplate.getForEntity(url, GithubUser.class);

            GithubUser githubUser = responseEntity.getBody();
            if (githubUser != null) {
                log.debug("Creating response");
                return User.builder()
                        .id(githubUser.getId())
                        .login(githubUser.getLogin())
                        .name(githubUser.getName())
                        .type(githubUser.getType())
                        .avatarUrl(githubUser.getAvatarUrl())
                        .createdAt(githubUser.getCreatedAt())
                        .calculations(calculate(githubUser.getFollowers(), githubUser.getPublicRepos()))
                        .build();
            }
        } catch (HttpClientErrorException e) {
            log.error("Http error while downloading user from the API", e);
        }
        return null;
    }

    public Double calculate(Integer followers, Integer publicRepos) {
        log.debug("Calculating followers and public repos");
        try {
            if (followers != 0) {
                return 6.00 / followers * (2 + publicRepos);
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }
}
