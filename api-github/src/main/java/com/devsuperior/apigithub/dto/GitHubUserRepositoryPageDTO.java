package com.devsuperior.apigithub.dto;

import java.util.ArrayList;
import java.util.List;

public class GitHubUserRepositoryPageDTO {

    private List<GitHubUserRepositoryDTO> content = new ArrayList<>();

    public List<GitHubUserRepositoryDTO> getContent() {
        return content;
    }

}
