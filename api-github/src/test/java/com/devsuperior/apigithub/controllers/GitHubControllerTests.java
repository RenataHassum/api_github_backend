package com.devsuperior.apigithub.controllers;

import com.devsuperior.apigithub.dto.GitHubUserDTO;
import com.devsuperior.apigithub.dto.GitHubUserDetailsDTO;
import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import com.devsuperior.apigithub.dto.GitHubUserRepositoryPageDTO;
import com.devsuperior.apigithub.services.GitHubUserService;
import com.devsuperior.apigithub.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GitHubControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubUserService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Long sinceId;
    private List<GitHubUserDTO> mockUserList;
    private GitHubUserPageDTO userPage;
    private GitHubUserDetailsDTO userDetails;
    private GitHubUserRepositoryPageDTO userRepositories;

    @BeforeEach
    void setUp() throws Exception {
        sinceId = 46L;
        mockUserList = Factory.createMockUserList();
        userPage = new GitHubUserPageDTO();
        userDetails = Factory.createMockGitHubUserDetails();
        userRepositories = Factory.createMockGitHubUserRepositoryPage();

        when(service.getGitHubUsersPage(Mockito.eq(sinceId))).thenReturn(userPage);
    }

    @Test
    public void testFindAllPage_ReturnsUserPageWithNextLink() throws Exception {
        userPage.getContent().addAll(mockUserList);
        userPage.setNext("http://localhost:8080/api/users?since=2");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .param("since", String.valueOf(sinceId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(mockUserList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.next").value(userPage.getNext()));
    }
}
