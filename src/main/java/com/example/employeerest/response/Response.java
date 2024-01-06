package com.example.employeerest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private HttpStatus code;        // HTTP status code
    private String status;   // "success" or "failed"
    private String message;  // Message corresponding to the status
    private List<Object> data; // List or array of JSON objects

}