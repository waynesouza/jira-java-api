package com.example.demo.controller;

import com.example.demo.dto.IssueResponseDTO;
import com.example.demo.dto.UpdateIssueRequestDTO;
import com.example.demo.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jira")
public class JiraController {

    private final JiraService service;

    public JiraController (JiraService service) {
        this.service = service;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssueResponseDTO>> getAllIssues(@RequestParam("query") String query) {
        log.info("Request to list all issues of project");
        List<IssueResponseDTO> response = service.getAllIssues(query);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/issue/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateIssue(@RequestBody UpdateIssueRequestDTO requestDto) {
        log.info("Request to update issue transition");
        service.updateIssue(requestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
