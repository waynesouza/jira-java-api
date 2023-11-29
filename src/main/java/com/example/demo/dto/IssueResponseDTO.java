package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IssueResponseDTO implements Serializable {

    private String id;
    private String key;
    private String status;
    private String type;
    private String description;
}
