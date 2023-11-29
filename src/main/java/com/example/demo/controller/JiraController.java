package com.example.demo.controller;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.example.demo.dto.IssueResponseDTO;
import com.example.demo.service.JiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jira")
public class JiraController {

    private final JiraService service;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssueResponseDTO>> getAllIssues() {
        List<IssueResponseDTO> response = service.getAllIssues();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueResponseDTO> updateIssue() {
        IssueResponseDTO response = service.updateIssue();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
