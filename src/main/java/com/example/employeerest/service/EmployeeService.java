package com.example.employeerest.service;
import com.example.employeerest.dto.EmployeeDTO;
import com.example.employeerest.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmployeeService {
    
    EmployeeDTO create(EmployeeDTO employeeDTO);
    
    List<EmployeeDTO> getAll();
    
    Employee update(Employee updatedEmployee, Long id);
    
    void delete(Long id);
    
}