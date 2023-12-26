package com.example.employeerest.service;
import com.example.employeerest.dto.EmployeeDTO;
import com.example.employeerest.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAll();

    @Transactional
    EmployeeDTO update(EmployeeDTO updatedEmployeeDTO, Long id);

    void delete(Long id);

}