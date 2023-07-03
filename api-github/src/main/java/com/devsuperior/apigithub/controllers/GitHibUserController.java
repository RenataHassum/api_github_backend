package com.devsuperior.apigithub.controllers;

import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import com.devsuperior.apigithub.services.GitHubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class GitHibUserController {

    @Autowired
    private GitHubUserService service;

    @GetMapping(value = "/users")
    public ResponseEntity<GitHubUserPageDTO> findAllPage(
            @RequestParam(name = "since", defaultValue = "0") Long sinceId) {
        GitHubUserPageDTO usersDto = service.getGitHubUsersPage(sinceId);
        return ResponseEntity.ok(usersDto);
    }

}
