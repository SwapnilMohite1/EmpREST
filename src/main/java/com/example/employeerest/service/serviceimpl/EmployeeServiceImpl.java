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
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }
    
    @Override
    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public Employee update(Employee updatedEmployee, Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        
        if (existingEmployee.isPresent()) {
            // Update only non-null fields from the updatedEmployee
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(updatedEmployee, existingEmployee.get());
            
            // Save changes to the existing employee
            return employeeRepository.save(existingEmployee.get());
        } else {
            // Handle not found scenario, throw exception or return null as needed
            throw new EntityNotFoundException("Employee with id " + id + " not found");
        }
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}