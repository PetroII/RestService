package com.empik.restservice.service;

import com.empik.restservice.config.ConnectionConfig;
import com.empik.restservice.model.GithubUser;
import com.empik.restservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    private ConnectionConfig connectionConfig;

    @Autowired
    private RestTemplate restTemplate;

    public User getUserFromApi(String login) {
        String url = connectionConfig.getApi() + "/" + login;
        ResponseEntity<GithubUser> responseEntity = restTemplate.getForEntity(url, GithubUser.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            GithubUser githubUser = responseEntity.getBody();
            if (githubUser != null) {
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
        }
        return new User();
    }

    private Double calculate(Integer followers, Integer publicRepos) {
        return 6.00 / followers * (2 + publicRepos);
    }
}
