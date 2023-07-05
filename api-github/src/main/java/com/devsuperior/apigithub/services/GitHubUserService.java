package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.*;
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

        ResponseEntity<List<GitHubUserDTO>> result = restTemplate
                .exchange("https://api.github.com/users?since=" + sinceId, HttpMethod.GET, null, responseType); //ex1 GET

        GitHubUserPageDTO dto = new GitHubUserPageDTO();

        dto.getContent().addAll(result.getBody());

        long newSince = result.getBody().get(result.getBody().size() - 1).getId();
        dto.setNext("http://localhost:8080/api/users?since=" + newSince);
        return dto;
    }

    public GitHubUserDetailsDTO getGitHubUserDetails(String username) {
        ResponseEntity<GitHubUserDetailsDTO> result = restTemplate
                .getForEntity("https://api.github.com/users/" + username, GitHubUserDetailsDTO.class); //ex2 GET
        return result.getBody();
    }

    public GitHubUserRepositoryPageDTO getGitHubUserRepositoriesPage(String username) {
        ParameterizedTypeReference<List<GitHubUserRepositoryDTO>> responseType = new ParameterizedTypeReference<List<GitHubUserRepositoryDTO>>() {
        };

        ResponseEntity<List<GitHubUserRepositoryDTO>> result = restTemplate
                .exchange("https://api.github.com/users/" + username + "/repos", HttpMethod.GET, null, responseType); //ex1 GET

        GitHubUserRepositoryPageDTO dto = new GitHubUserRepositoryPageDTO();
        dto.getContent().addAll(result.getBody());
        return dto;
    }
}
