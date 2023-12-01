package com.example.demo.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiErrorDTO {

    private Integer status;
    private String code;
    private String message;

}
