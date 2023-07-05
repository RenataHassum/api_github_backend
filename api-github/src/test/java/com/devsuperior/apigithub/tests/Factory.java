package com.devsuperior.apigithub.tests;

import com.devsuperior.apigithub.dto.GitHubUserDTO;
import com.devsuperior.apigithub.dto.GitHubUserDetailsDTO;
import com.devsuperior.apigithub.dto.GitHubUserRepositoryDTO;
import com.devsuperior.apigithub.dto.GitHubUserRepositoryPageDTO;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static List<GitHubUserDTO> createMockUserList() {
        List<GitHubUserDTO> userList = new ArrayList<>();
        GitHubUserDTO user1 = new GitHubUserDTO();
        user1.setId(1);
        user1.setLogin("user1");

        GitHubUserDTO user2 = new GitHubUserDTO();
        user2.setId(2);
        user2.setLogin("user2");

        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    public static GitHubUserDetailsDTO createMockGitHubUserDetails() {
        GitHubUserDetailsDTO userDetails = new GitHubUserDetailsDTO();
        userDetails.setLogin("john");
        userDetails.setId(1);
        userDetails.setNodeId("123456");
        userDetails.setAvatarUrl("https://example.com/avatar");

        return userDetails;
    }

    public static GitHubUserRepositoryPageDTO createMockGitHubUserRepositoryPage() {
        GitHubUserRepositoryPageDTO repositoryPage = new GitHubUserRepositoryPageDTO();
        repositoryPage.getContent().addAll(createMockRepositoryList());
        return repositoryPage;
    }

    public static List<GitHubUserRepositoryDTO> createMockRepositoryList() {
        List<GitHubUserRepositoryDTO> repositories = new ArrayList<>();

        GitHubUserRepositoryDTO repository1 = new GitHubUserRepositoryDTO();
        repository1.setId(1);
        repository1.setNodeId("node1");
        repository1.setName("repository1");

        GitHubUserRepositoryDTO repository2 = new GitHubUserRepositoryDTO();
        repository2.setId(2);
        repository2.setNodeId("node2");
        repository2.setName("repository2");

        repositories.add(repository1);
        repositories.add(repository2);

        return repositories;
    }
}
