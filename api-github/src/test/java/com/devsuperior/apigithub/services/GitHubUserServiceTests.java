package com.devsuperior.apigithub.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
public class GitHubUserServiceTests {

    @InjectMocks
    private GitHubUserService service;

    @Mock
    private RestTemplate restTemplate;




}

