package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
}
