package com.example.demo.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class UpdateIssueRequestDTO implements Serializable {

    private String idOrKey;
    private Integer idTransition;

}
