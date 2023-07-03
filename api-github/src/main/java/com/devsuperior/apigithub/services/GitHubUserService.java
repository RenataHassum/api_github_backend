package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.GitHubUserDTO;
import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitHubUserService {

    @Autowired
    private RestTemplate restTemplate;

    public GitHubUserPageDTO getGitHubUsersPage(Long sinceId) {
        ParameterizedTypeReference<List<GitHubUserDTO>> responseType = new ParameterizedTypeReference<List<GitHubUserDTO>>() {
        };

        ResponseEntity<List<GitHubUserDTO>> result = restTemplate.exchange("https://api.github.com/users?since=" + sinceId, HttpMethod.GET, null, responseType);

        GitHubUserPageDTO dto = new GitHubUserPageDTO();

        dto.setContent(result.getBody());

        long newSince = result.getBody().get(result.getBody().size() - 1).getId();
        dto.setNext("http://localhost:8080/api/users?since=" + newSince);
        return dto;
    }
}
