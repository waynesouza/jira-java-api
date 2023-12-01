package com.example.demo.service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.example.demo.dto.IssueResponseDTO;
import com.example.demo.dto.UpdateIssueRequestDTO;
import com.example.demo.mapper.CustomConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JiraService implements CustomConverter {

    private final JiraRestClient jiraRestClient;
    private final ModelMapper mapper;

    public JiraService(JiraRestClient jiraRestClient, ModelMapper mapper) {
        this.jiraRestClient = jiraRestClient;
        this.mapper = mapper;
        this.mapper.addConverter(converterToIssueDto);
    }

    public List<IssueResponseDTO> getAllIssues(String query) {
        List<Issue> issues = StreamSupport.stream(jiraRestClient.getSearchClient()
                .searchJql(query).claim().getIssues().spliterator(), false).toList();
        return issues.stream().map(i -> mapper.map(i, IssueResponseDTO.class)).collect(Collectors.toList());
    }

    public void updateIssue(UpdateIssueRequestDTO requestDto) {
        jiraRestClient.getIssueClient()
                .transition(jiraRestClient.getIssueClient().getIssue(requestDto.getIdOrKey()).claim(),
                        new TransitionInput(requestDto.getIdTransition()));
    }
}
