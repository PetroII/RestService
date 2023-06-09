package com.empik.restservice.controller;

import com.empik.restservice.model.User;
import com.empik.restservice.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServiceController {

    @Autowired
    RestService restService;

    @GetMapping(value = "/users/{login}")
    public ResponseEntity<User> getUserByLogin(
            @PathVariable("login") String login) {
        return ResponseEntity.ok(restService.getUserFromApi(login));
    }
}
