package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.GitHubUserDetailsDTO;
import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import com.devsuperior.apigithub.dto.GitHubUserRepositoryPageDTO;
import org.junit.jupiter.api.BeforeEach;
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

    private Long sinceId;
    private String username;
    private String apiUrlLocal;
    private String apiUrlExternal;
    private GitHubUserDetailsDTO expectedDetails;

    @BeforeEach
    void setUp() throws Exception {
        sinceId = 30L;
        username = "renatahassum";
        apiUrlLocal = "http://localhost:8080/api/users?since=";
        apiUrlExternal = "https://api.github.com/users/" + username;
        expectedDetails = restTemplate.getForEntity(apiUrlExternal, GitHubUserDetailsDTO.class).getBody();
    }

    @Test
    public void testGetGitHubUsersPage_ReturnsUserPage() {
        GitHubUserPageDTO result = service.getGitHubUsersPage(sinceId);

        assertNotNull(result);
        assertNotNull(result.getContent());
        assertFalse(result.getContent().isEmpty());
        assertNotNull(result.getNext());
        assertEquals(apiUrlLocal + result.getContent().get(result.getContent().size() - 1).getId(), result.getNext());
    }

    @Test
    public void testGetGitHubUserDetails_ReturnsUserDetails() {
        GitHubUserDetailsDTO resultDto = service.getGitHubUserDetails(username);

        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getLogin()).isEqualTo(expectedDetails.getLogin());
        assertThat(resultDto.getName()).isEqualTo(expectedDetails.getName());
    }

    @Test
    public void testGetGitHubUserRepositoriesPage_ReturnsRepositoryPage() {
        GitHubUserRepositoryPageDTO result = service.getGitHubUserRepositoriesPage(username);

        assertNotNull(result);
        assertNotNull(result.getContent());
        assertFalse(result.getContent().isEmpty());
    }
}
