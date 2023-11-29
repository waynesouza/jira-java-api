package com.example.demo.service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.example.demo.dto.IssueResponseDTO;
import com.example.demo.mapper.CustomConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class JiraService implements CustomConverter {

    private final JiraRestClient jiraRestClient;

    public List<IssueResponseDTO> getAllIssues() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(converterToIssueDto);
        String query = "project = CFPS";
        SearchResult result = jiraRestClient.getSearchClient().searchJql(query).claim();
        List<Issue> issues = StreamSupport.stream(result.getIssues().spliterator(), false).toList();
        return issues.stream().map(i -> mapper.map(i, IssueResponseDTO.class)).collect(Collectors.toList());
    }

    public IssueResponseDTO updateIssue() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(converterToIssueDto);
        Issue issue = jiraRestClient.getIssueClient().getIssue("CFPS-12").claim();
        jiraRestClient.getIssueClient().transition(issue, new TransitionInput(71));
        return mapper.map(jiraRestClient.getIssueClient().getIssue("CFPS-12").claim(), IssueResponseDTO.class);
    }
}
