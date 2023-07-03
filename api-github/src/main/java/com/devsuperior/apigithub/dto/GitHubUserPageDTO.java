package com.devsuperior.apigithub.dto;

import java.util.ArrayList;
import java.util.List;

public class GitHubUserPageDTO {

    private String nextPage;

    private List<GitHubUserDTO> content = new ArrayList<>();

    public GitHubUserPageDTO() {
    }

    public GitHubUserPageDTO(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public List<GitHubUserDTO> getContent() {
        return content;
    }
}
