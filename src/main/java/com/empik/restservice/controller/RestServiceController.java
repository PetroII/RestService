package com.empik.restservice.controller;

import com.empik.restservice.model.User;
import com.empik.restservice.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestServiceController {

    @Autowired
    RestService restService;

    @GetMapping(value = "/users/{login}")
    public ResponseEntity<?> getUserByLogin(
            @PathVariable("login") String login) {

        User user = restService.getUserFromApi(login);
        log.debug("Sending response");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + login + " not found");
        } else {
            return ResponseEntity.ok(user);
        }
    }
}
