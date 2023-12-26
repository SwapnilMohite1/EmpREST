package com.example.employeerest.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class Response {
    private HttpStatus code;        // HTTP status code
    private String status;   // "success" or "failed"
    private String message;  // Message corresponding to the status
    private List<Object> data; // List or array of JSON objects

}