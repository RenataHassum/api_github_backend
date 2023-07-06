package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.*;
import com.devsuperior.apigithub.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GitHubUserServiceTests {

    @InjectMocks
    private GitHubUserService service;

    @Mock
    private RestTemplate restTemplate;

    private Long sinceId;
    private String apiUrlExternal;
    private String apiUrlLocal;
    private List<GitHubUserDTO> mockUserList;
    private GitHubUserDetailsDTO userDetails;
    private ResponseEntity<GitHubUserDetailsDTO> mockResponseEntityUserDetails;
    private List<GitHubUserRepositoryDTO> repositories;
    private ResponseEntity<List<GitHubUserRepositoryDTO>> mockResponseEntityUserRepositories;

    @BeforeEach
    void setUp() throws Exception {
        sinceId = 46L;
        apiUrlExternal = "https://api.github.com/users?since=" + sinceId;
        apiUrlLocal = "http://localhost:8080/api/users?since=2";
        mockUserList = Factory.createMockUserList();
        userDetails = Factory.createMockGitHubUserDetails();
        mockResponseEntityUserDetails = ResponseEntity.ok(userDetails);
        repositories = Factory.createMockRepositoryList();
        mockResponseEntityUserRepositories = ResponseEntity.ok(repositories);

        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.isNull(),
                Mockito.any(ParameterizedTypeReference.class)
        ))
                .thenReturn(ResponseEntity.ok(mockUserList));

        when(restTemplate.getForEntity(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(GitHubUserDetailsDTO.class)))
                .thenReturn(mockResponseEntityUserDetails);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.isNull(),
                ArgumentMatchers.<ParameterizedTypeReference<List<GitHubUserRepositoryDTO>>>any()))
                .thenReturn(mockResponseEntityUserRepositories);
    }

    @Test
    public void testGetGitHubUsersPage_ReturnsPageWithUsersAndNextLink() {
        when(restTemplate.exchange(
                eq(apiUrlExternal),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        ))
                .thenReturn(ResponseEntity.ok(mockUserList));

        GitHubUserPageDTO result = service.getGitHubUsersPage(sinceId);

        List<GitHubUserDTO> users = result.getContent();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(mockUserList, users);
        assertEquals(apiUrlLocal, result.getNext());
    }

    @Test
    public void testGetGitHubUserDetails_ShouldReturnUserDetails() {
        GitHubUserDetailsDTO result = service.getGitHubUserDetails("john");

        Assertions.assertEquals(userDetails.getId(), result.getId());
        Assertions.assertEquals(userDetails.getLogin(), result.getLogin());
    }

    @Test
    public void testGetGitHubUserRepositoriesPage_ShouldReturnRepositoryPage() {
        GitHubUserRepositoryPageDTO result = service.getGitHubUserRepositoriesPage("john");

        Assertions.assertEquals(repositories.size(), result.getContent().size());
    }
}

