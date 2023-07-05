package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.GitHubUserDetailsDTO;
import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import com.devsuperior.apigithub.dto.GitHubUserRepositoryPageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class GitHubUserServiceIT {

    @Autowired
    private GitHubUserService service;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetGitHubUsersPage_ReturnsUserPage() {
        // Arrange
        Long sinceId = 30L;

        // Act
        GitHubUserPageDTO result = service.getGitHubUsersPage(sinceId);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getContent());
        assertFalse(result.getContent().isEmpty());
        assertNotNull(result.getNext());
        assertEquals("http://localhost:8080/api/users?since=" + result.getContent().get(result.getContent().size() - 1).getId(), result.getNext());
    }

    @Test
    public void testGetGitHubUserDetails_ReturnsUserDetails() {
        // Arrange
        String username = "renatahassum";

        // Act
        GitHubUserDetailsDTO expectedDetails = restTemplate
                .getForEntity("https://api.github.com/users/" + username, GitHubUserDetailsDTO.class) //ex2 GET
                .getBody();

        GitHubUserDetailsDTO resultDto = service.getGitHubUserDetails(username);

        // Assert
        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getLogin()).isEqualTo(expectedDetails.getLogin());
        assertThat(resultDto.getName()).isEqualTo(expectedDetails.getName());
    }

}
