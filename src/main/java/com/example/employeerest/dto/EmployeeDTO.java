package com.example.employeerest.dto;
import lombok.Data;

import java.util.Date;
@Data
public class EmployeeDTO {
    private Long id;
    private int eno;
    private String ename;
    private double salary;
    private Date dob;
    private Date doj;
    private String manager;
    private String dept;
    
}