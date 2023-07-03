package com.devsuperior.apigithub.dto;

import java.util.ArrayList;
import java.util.List;

public class GitHubUserPageDTO {

    private List<GitHubUserDTO> content = new ArrayList<>();

    private String next;

    public List<GitHubUserDTO> getContent() {
        return content;
    }

    public void setContent(List<GitHubUserDTO> content) {
        this.content = content;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
