// EmployeeServiceImpl.java
package com.example.employeerest.service.serviceimpl;

import com.example.employeerest.dto.EmployeeDTO;
import com.example.employeerest.entity.Employee;
import com.example.employeerest.repository.EmployeeRepository;
import com.example.employeerest.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new IllegalArgumentException("EmployeeDTO cannot be null");
        }

        Employee employee1 = employeeRepository.findByEno(employeeDTO.getEno());
        if (employee1 == null) {
            Employee employee = modelMapper.map(employeeDTO, Employee.class);
            Employee savedEmployee = employeeRepository.save(employee);
            return modelMapper.map(savedEmployee, EmployeeDTO.class);
        } else {
            throw new RuntimeException("Employee with the given employee number already exists.");
        }
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO update(EmployeeDTO updatedEmployeeDTO, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            // Update only non-null fields from the updatedEmployeeDTO
            modelMapper.map(updatedEmployeeDTO, existingEmployee.get());

            // Save changes to the existing employee
            return modelMapper.map(employeeRepository.save(existingEmployee.get()), EmployeeDTO.class);
        } else {
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        employeeRepository.deleteById(id);
    }
}