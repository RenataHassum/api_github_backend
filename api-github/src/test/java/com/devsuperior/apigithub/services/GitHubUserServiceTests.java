package com.devsuperior.apigithub.services;

import com.devsuperior.apigithub.dto.GitHubUserDTO;
import com.devsuperior.apigithub.dto.GitHubUserDetailsDTO;
import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
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
    private List<GitHubUserDTO> mockUserList;

    @BeforeEach
    void setUp() throws Exception {
        sinceId = 46L;
        mockUserList = Factory.createMockUserList();
        //  ResponseEntity<List<GitHubUserDTO>> mockResponseEntity = ResponseEntity.ok(mockUserList);

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

        // Execução do método a ser testado
        GitHubUserPageDTO result = service.getGitHubUsersPage(sinceId);

        List<GitHubUserDTO> users = result.getContent();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(mockUserList, users);
        assertEquals("http://localhost:8080/api/users?since=2", result.getNext());

    }

    @Test
    public void getGitHubUserDetails_ShouldReturnUserDetails() {
        GitHubUserDetailsDTO userDetails = new GitHubUserDetailsDTO();
        userDetails.setId(1);
        userDetails.setLogin("john");

        ResponseEntity<GitHubUserDetailsDTO> mockResponseEntity = ResponseEntity.ok(userDetails);
        Mockito.when(restTemplate.getForEntity(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.eq(GitHubUserDetailsDTO.class)))
                .thenReturn(mockResponseEntity);

        // Execução do método a ser testado
        GitHubUserDetailsDTO result = service.getGitHubUserDetails("john");

        // Verificação dos resultados
        Assertions.assertEquals(userDetails.getId(), result.getId());
        Assertions.assertEquals(userDetails.getLogin(), result.getLogin());
    }


}

