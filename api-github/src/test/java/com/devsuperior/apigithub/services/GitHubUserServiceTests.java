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

import java.util.ArrayList;
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
    private List<GitHubUserDTO> mockUserList;

    @BeforeEach
    void setUp() throws Exception {
        sinceId = 46L;
        mockUserList = Factory.createMockUserList();

        when(restTemplate.exchange(
                Mockito.anyString(),
                Mockito.any(HttpMethod.class),
                Mockito.isNull(),
                Mockito.any(ParameterizedTypeReference.class)
        ))
                .thenReturn(ResponseEntity.ok(mockUserList));
    }

    @Test
    public void testGetGitHubUsersPage_ReturnsPageWithUsersAndNextLink() {
        when(restTemplate.exchange(
                eq("https://api.github.com/users?since=2"),
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
        assertEquals("http://localhost:8080/api/users?since=2", result.getNext());

    }

    @Test
    public void testGetGitHubUserDetails_ShouldReturnUserDetails() {
        GitHubUserDetailsDTO userDetails = new GitHubUserDetailsDTO();
        userDetails.setId(1);
        userDetails.setLogin("john");

        ResponseEntity<GitHubUserDetailsDTO> mockResponseEntity = ResponseEntity.ok(userDetails);
        Mockito.when(restTemplate.getForEntity(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.eq(GitHubUserDetailsDTO.class)))
                .thenReturn(mockResponseEntity);

        GitHubUserDetailsDTO result = service.getGitHubUserDetails("john");

        Assertions.assertEquals(userDetails.getId(), result.getId());
        Assertions.assertEquals(userDetails.getLogin(), result.getLogin());
    }

    @Test
    public void testGetGitHubUserRepositoriesPage_ShouldReturnRepositoryPage() {
        List<GitHubUserRepositoryDTO> repositories = new ArrayList<>();

        ResponseEntity<List<GitHubUserRepositoryDTO>> mockResponseEntity = ResponseEntity.ok(repositories);
        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.eq(HttpMethod.GET),
                        ArgumentMatchers.isNull(),
                        ArgumentMatchers.<ParameterizedTypeReference<List<GitHubUserRepositoryDTO>>>any()))
                .thenReturn(mockResponseEntity);

        GitHubUserRepositoryPageDTO result = service.getGitHubUserRepositoriesPage("john");

        Assertions.assertEquals(repositories.size(), result.getContent().size());
    }

}

