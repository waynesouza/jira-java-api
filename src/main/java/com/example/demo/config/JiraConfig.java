package com.example.demo.config;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class JiraConfig {

    @Value("${jira.uri}")
    private String uri;

    @Value("${jira.user}")
    private String user;

    @Value("${jira.token}")
    private String token;

    @Bean
    public JiraRestClient jiraRestClient() throws URISyntaxException {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI jiraServer = new URI(uri);
        return factory.createWithBasicHttpAuthentication(jiraServer, user, token);
    }

}
